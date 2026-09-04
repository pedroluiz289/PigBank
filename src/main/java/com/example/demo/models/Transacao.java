package com.example.demo.models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transacao {
    private String tipo;
    private String data;
    private double valor;

    public Transacao() {

    }

    public Transacao(String tipo, double valor) {
        this.tipo = tipo;
        this.valor = valor;
        this.data = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
    }

    public String getTipo() {
        return this.tipo;
    }

    public String getData() {
        return this.data;
    }

    public double getValor() {
        return this.valor;
    }
}
