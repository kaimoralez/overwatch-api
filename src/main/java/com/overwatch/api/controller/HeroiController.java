package com.overwatch.api.controller;

import com.overwatch.api.entity.Heroi;
import com.overwatch.api.repository.HeroiRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/herois")
@CrossOrigin(origins = "*")
public class HeroiController {


    private final HeroiRepository heroiRepository;

    public HeroiController(HeroiRepository heroiRepository){
        this.heroiRepository = heroiRepository;
    }

    @GetMapping
    public ResponseEntity<List<Heroi>> listarTodos() {
        return ResponseEntity.ok(heroiRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Heroi> buscarPorId(@PathVariable Long id) {
        Optional<Heroi> heroi = heroiRepository.findById(id);
        return heroi.map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Heroi> cadastrar(@Valid @RequestBody Heroi heroi) {
        Heroi novoHeroi = heroiRepository.save(heroi);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoHeroi);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Heroi> atualizar(@PathVariable Long id, @Valid @RequestBody Heroi dadosAtualizados) {
        if (!heroiRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        dadosAtualizados.setId(id);
        Heroi atualizado = heroiRepository.save(dadosAtualizados);
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        if (!heroiRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        heroiRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
