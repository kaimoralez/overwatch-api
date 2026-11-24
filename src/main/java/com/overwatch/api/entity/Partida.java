package com.overwatch.api.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "partidas")
public class Partida {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome da equipe A é obrigatório")
    private String equipeA;

    @NotBlank(message = "O nome da equipe B é obrigatório")
    private String equipeB;

    @NotBlank(message = "O mapa jogado é obrigatório")
    private String mapaJogado;

    @NotBlank(message = "O placar é obrigatório")
    private String placar;

    @NotBlank(message = "A data da partida é obrigatória")
    private String dataPartida;

    public Partida() {
    }

    public Partida(Long id, String equipeA, String equipeB, String mapaJogado, String placar, String dataPartida) {
        this.id = id;
        this.equipeA = equipeA;
        this.equipeB = equipeB;
        this.mapaJogado = mapaJogado;
        this.placar = placar;
        this.dataPartida = dataPartida;
    }

    public Partida(String equipeA, String equipeB, String mapaJogado, String placar, String dataPartida) {
        this.equipeA = equipeA;
        this.equipeB = equipeB;
        this.mapaJogado = mapaJogado;
        this.placar = placar;
        this.dataPartida = dataPartida;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getEquipeA() { return equipeA; }
    public void setEquipeA(String equipeA) { this.equipeA = equipeA; }

    public String getEquipeB() { return equipeB; }
    public void setEquipeB(String equipeB) { this.equipeB = equipeB; }

    public String getMapaJogado() { return mapaJogado; }
    public void setMapaJogado(String mapaJogado) { this.mapaJogado = mapaJogado; }

    public String getPlacar() { return placar; }
    public void setPlacar(String placar) { this.placar = placar; }

    public String getDataPartida() { return dataPartida; }
    public void setDataPartida(String dataPartida) { this.dataPartida = dataPartida; }
}
