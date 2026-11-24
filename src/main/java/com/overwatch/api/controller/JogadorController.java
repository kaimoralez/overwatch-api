package com.overwatch.api.controller;

import com.overwatch.api.entity.Jogador;
import com.overwatch.api.repository.JogadorRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/jogadores")
@CrossOrigin(origins = "*")
public class JogadorController {


    private final JogadorRepository jogadorRepository;

    public JogadorController(JogadorRepository jogadorRepository){
        this.jogadorRepository = jogadorRepository;
    }

    @GetMapping
    public ResponseEntity<List<Jogador>> listarTodos() {
        return ResponseEntity.ok(jogadorRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Jogador> buscarPorId(@PathVariable Long id) {
        Optional<Jogador> jogador = jogadorRepository.findById(id);
        return jogador.map(ResponseEntity::ok)
                      .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Jogador> cadastrar(@Valid @RequestBody Jogador jogador) {
        Jogador novoJogador = jogadorRepository.save(jogador);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoJogador);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Jogador> atualizar(@PathVariable Long id, @Valid @RequestBody Jogador dadosAtualizados) {
        if (!jogadorRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        dadosAtualizados.setId(id);
        return ResponseEntity.ok(jogadorRepository.save(dadosAtualizados));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        if (!jogadorRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        jogadorRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
