package com.lgfas.testepdf.model;

import jakarta.persistence.*;

@Entity
public class Pdf {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private Double consumoMedioDiario;
    private Double consumoPonta;
    private Double consumoForaPonta;
    private Double demandaPonta;
    private Double demandaForaPonta;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getConsumoMedioDiario() {
        return consumoMedioDiario;
    }

    public void setConsumoMedioDiario(Double consumoMedioDiario) {
        this.consumoMedioDiario = consumoMedioDiario;
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
}
