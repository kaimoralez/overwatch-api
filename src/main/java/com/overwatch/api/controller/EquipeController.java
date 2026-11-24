package com.overwatch.api.controller;

import com.overwatch.api.entity.Equipe;
import com.overwatch.api.repository.EquipeRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/equipes")

public class EquipeController {


    private final EquipeRepository equipeRepository;

    public EquipeController(EquipeRepository equipeRepository){
        this.equipeRepository = equipeRepository;
    }

    @GetMapping
    public ResponseEntity<List<Equipe>> listarTodos() {
        return ResponseEntity.ok(equipeRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Equipe> buscarPorId(@PathVariable Long id) {
        Optional<Equipe> equipe = equipeRepository.findById(id);
        return equipe.map(ResponseEntity::ok)
                     .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Equipe> cadastrar(@Valid @RequestBody Equipe equipe) {
        Equipe novaEquipe = equipeRepository.save(equipe);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaEquipe);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Equipe> atualizar(@PathVariable Long id, @Valid @RequestBody Equipe dadosAtualizados) {
        if (!equipeRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        dadosAtualizados.setId(id);
        return ResponseEntity.ok(equipeRepository.save(dadosAtualizados));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        if (!equipeRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        equipeRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
