package com.example.demo.models;

import java.time.LocalDateTime;

public class Transacao {
    private String tipo;
    private double valor;
    private LocalDateTime data;

    public Transacao() {

    }

    public Transacao(String tipo, double valor) {
        this.tipo = tipo;
        this.valor = valor;
        this.data = LocalDateTime.now();
    }

    public String getTipo() {
        return this.tipo;
    }

    public LocalDateTime getData() {
        return this.data;
    }

    public double getValor() {
        return this.valor;
    }
}
