package com.overwatch.api.controller;

import com.overwatch.api.entity.Mapa;
import com.overwatch.api.repository.MapaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/mapas")
@CrossOrigin(origins = "*")
public class MapaController {


    private final MapaRepository mapaRepository;

    public MapaController(MapaRepository mapaRepository){
        this.mapaRepository = mapaRepository;
    }

    @GetMapping
    public ResponseEntity<List<Mapa>> listarTodos() {
        return ResponseEntity.ok(mapaRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mapa> buscarPorId(@PathVariable Long id) {
        Optional<Mapa> mapa = mapaRepository.findById(id);
        return mapa.map(ResponseEntity::ok)
                   .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Mapa> cadastrar(@Valid @RequestBody Mapa mapa) {
        Mapa novoMapa = mapaRepository.save(mapa);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoMapa);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Mapa> atualizar(@PathVariable Long id, @Valid @RequestBody Mapa dadosAtualizados) {
        if (!mapaRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        dadosAtualizados.setId(id);
        return ResponseEntity.ok(mapaRepository.save(dadosAtualizados));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        if (!mapaRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        mapaRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
