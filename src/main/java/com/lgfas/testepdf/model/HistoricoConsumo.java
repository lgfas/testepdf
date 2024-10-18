package com.lgfas.testepdf.model;

import jakarta.persistence.*;

@Entity
public class HistoricoConsumo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double demandaPonta; // PONTA
    private Double demandaForaPonta; // FORA PONTA
    private Double consumoPonta; // PONTA/TOT
    private Double consumoForaPonta; // FORA PONTA (Consumo)

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    // Getters e Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getDemandaPonta() {
        return demandaPonta;
    }

    public void setDemandaPonta(Double demandaPonta) {
        this.demandaPonta = demandaPonta;
    }

    public Double getDemandaForaPonta() {
        return demandaForaPonta;
    }

    public void setDemandaForaPonta(Double demandaForaPonta) {
        this.demandaForaPonta = demandaForaPonta;
    }

    public Double getConsumoPonta() {
        return consumoPonta;
    }

    public void setConsumoPonta(Double consumoPonta) {
        this.consumoPonta = consumoPonta;
    }

    public Double getConsumoForaPonta() {
        return consumoForaPonta;
    }

    public void setConsumoForaPonta(Double consumoForaPonta) {
        this.consumoForaPonta = consumoForaPonta;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
