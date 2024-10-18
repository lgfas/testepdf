package com.lgfas.testepdf.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<HistoricoConsumo> historicoConsumo;

    // Getters e Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<HistoricoConsumo> getHistoricoConsumo() {
        return historicoConsumo;
    }

    public void setHistoricoConsumo(List<HistoricoConsumo> historicoConsumo) {
        this.historicoConsumo = historicoConsumo;
    }
}
