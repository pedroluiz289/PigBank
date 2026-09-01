package com.example.demo.models;

public class Corrente extends ContaBancaria {
    private double limiteTotal;
    private double limiteUsado;

    public Corrente() {

    }

    public Corrente(String nome, double saldo, double limiteTotal, double limiteUsado) {
        super(nome, saldo);
        this.limiteTotal = limiteTotal;
        this.limiteUsado = limiteUsado;
    }

    public double getLimiteTotal() {
        return this.limiteTotal;
    }

    public double getLimiteUsado() {
        return this.limiteUsado;
    }
}
