package com.overwatch.api.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "equipes")
public class Equipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome da equipe é obrigatório")
    @Column(nullable = false)
    private String nome;

    @NotBlank(message = "A sigla da equipe é obrigatória")
    private String sigla;

    @NotBlank(message = "A região é obrigatória")
    private String regiao;

    @NotBlank(message = "O nome do treinador é obrigatório")
    private String treinador;

    public Equipe() {
    }

    public Equipe(Long id, String nome, String sigla, String regiao, String treinador) {
        this.id = id;
        this.nome = nome;
        this.sigla = sigla;
        this.regiao = regiao;
        this.treinador = treinador;
    }

    public Equipe(String nome, String sigla, String regiao, String treinador) {
        this.nome = nome;
        this.sigla = sigla;
        this.regiao = regiao;
        this.treinador = treinador;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getSigla() { return sigla; }
    public void setSigla(String sigla) { this.sigla = sigla; }

    public String getRegiao() { return regiao; }
    public void setRegiao(String regiao) { this.regiao = regiao; }

    public String getTreinador() { return treinador; }
    public void setTreinador(String treinador) { this.treinador = treinador; }
}
