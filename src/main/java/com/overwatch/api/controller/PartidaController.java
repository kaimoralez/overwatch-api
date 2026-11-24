package com.overwatch.api.controller;

import com.overwatch.api.entity.Partida;
import com.overwatch.api.repository.PartidaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/partidas")
@CrossOrigin(origins = "*")
public class PartidaController {


    private final PartidaRepository partidaRepository;

    public PartidaController(PartidaRepository partidaRepository){
        this.partidaRepository = partidaRepository;
    }

    @GetMapping
    public ResponseEntity<List<Partida>> listarTodos() {
        return ResponseEntity.ok(partidaRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Partida> buscarPorId(@PathVariable Long id) {
        Optional<Partida> partida = partidaRepository.findById(id);
        return partida.map(ResponseEntity::ok)
                      .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Partida> cadastrar(@Valid @RequestBody Partida partida) {
        Partida novaPartida = partidaRepository.save(partida);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaPartida);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Partida> atualizar(@PathVariable Long id, @Valid @RequestBody Partida dadosAtualizados) {
        if (!partidaRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        dadosAtualizados.setId(id);
        return ResponseEntity.ok(partidaRepository.save(dadosAtualizados));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        if (!partidaRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        partidaRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
