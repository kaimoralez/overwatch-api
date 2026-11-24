package com.overwatch.api.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "mapas")
public class Mapa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome do mapa é obrigatório")
    @Column(nullable = false)
    private String nome;

    @NotBlank(message = "O modo de jogo é obrigatório")
    private String modoJogo;

    @NotBlank(message = "O país/localização é obrigatório")
    private String pais;

    public Mapa() {
    }

    public Mapa(Long id, String nome, String modoJogo, String pais) {
        this.id = id;
        this.nome = nome;
        this.modoJogo = modoJogo;
        this.pais = pais;
    }

    public Mapa(String nome, String modoJogo, String pais) {
        this.nome = nome;
        this.modoJogo = modoJogo;
        this.pais = pais;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getModoJogo() { return modoJogo; }
    public void setModoJogo(String modoJogo) { this.modoJogo = modoJogo; }

    public String getPais() { return pais; }
    public void setPais(String pais) { this.pais = pais; }
}
