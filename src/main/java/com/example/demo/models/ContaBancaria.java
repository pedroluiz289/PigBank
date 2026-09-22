package com.example.demo.models;

import org.springframework.format.annotation.NumberFormat;

abstract class ContaBancaria {
    private String nome;
    private double entradas;
    private double saidas;
    
    @NumberFormat(pattern = "#,##0.00")
    private double saldo;

    public ContaBancaria() {
        
    }

    public ContaBancaria(String nome, double saldo, double entradas, double saidas) {
        this.nome = nome;
        this.saldo = saldo;
        this.entradas = entradas;
        this.saidas = saidas;
    }

    public abstract boolean depositar(double valor);

    public abstract boolean sacar(double valor);

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public void setEntradas(double valor) {
        this.entradas = valor;
    }

    public void setSaidas(double valor) {
        this.saidas = valor;
    }

    public double getEntradas() {
        return this.entradas;
    }

    public double getSaidas() {
        return this.saidas;
    }

    public String getNome() {
        return this.nome;
    }

    public double getSaldo() {
        return this.saldo;
    }
}
