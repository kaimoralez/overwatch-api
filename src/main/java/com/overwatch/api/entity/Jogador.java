package com.overwatch.api.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "jogadores")
public class Jogador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "A BattleTag é obrigatória")
    @Column(nullable = false)
    private String battleTag;

    @NotBlank(message = "O nome real é obrigatório")
    private String nomeReal;

    @NotBlank(message = "A função principal é obrigatória")
    private String funcaoPrincipal;

    @NotBlank(message = "O elo competitivo é obrigatório")
    private String elo;

    public Jogador() {
    }

    public Jogador(Long id, String battleTag, String nomeReal, String funcaoPrincipal, String elo) {
        this.id = id;
        this.battleTag = battleTag;
        this.nomeReal = nomeReal;
        this.funcaoPrincipal = funcaoPrincipal;
        this.elo = elo;
    }

    public Jogador(String battleTag, String nomeReal, String funcaoPrincipal, String elo) {
        this.battleTag = battleTag;
        this.nomeReal = nomeReal;
        this.funcaoPrincipal = funcaoPrincipal;
        this.elo = elo;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getBattleTag() { return battleTag; }
    public void setBattleTag(String battleTag) { this.battleTag = battleTag; }

    public String getNomeReal() { return nomeReal; }
    public void setNomeReal(String nomeReal) { this.nomeReal = nomeReal; }

    public String getFuncaoPrincipal() { return funcaoPrincipal; }
    public void setFuncaoPrincipal(String funcaoPrincipal) { this.funcaoPrincipal = funcaoPrincipal; }

    public String getElo() { return elo; }
    public void setElo(String elo) { this.elo = elo; }
}
