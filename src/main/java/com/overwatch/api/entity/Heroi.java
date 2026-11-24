package com.overwatch.api.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "herois")
public class Heroi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome do herói é obrigatório")
    @Column(nullable = false)
    private String nome;

    @NotBlank(message = "A função (Tanque, Dano, Suporte) é obrigatória")
    @Column(nullable = false)
    private String funcao;

    @NotNull(message = "A vida base é obrigatória")
    @Min(value = 100, message = "A vida base deve ser no mínimo 100")
    private Integer vidaBase;

    @NotBlank(message = "A habilidade suprema (Ultimate) é obrigatória")
    private String habilidadeSuprema;

    public Heroi() {
    }

    public Heroi(Long id, String nome, String funcao, Integer vidaBase, String habilidadeSuprema) {
        this.id = id;
        this.nome = nome;
        this.funcao = funcao;
        this.vidaBase = vidaBase;
        this.habilidadeSuprema = habilidadeSuprema;
    }

    public Heroi(String nome, String funcao, Integer vidaBase, String habilidadeSuprema) {
        this.nome = nome;
        this.funcao = funcao;
        this.vidaBase = vidaBase;
        this.habilidadeSuprema = habilidadeSuprema;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getFuncao() { return funcao; }
    public void setFuncao(String funcao) { this.funcao = funcao; }

    public Integer getVidaBase() { return vidaBase; }
    public void setVidaBase(Integer vidaBase) { this.vidaBase = vidaBase; }

    public String getHabilidadeSuprema() { return habilidadeSuprema; }
    public void setHabilidadeSuprema(String habilidadeSuprema) { this.habilidadeSuprema = habilidadeSuprema; }
}
