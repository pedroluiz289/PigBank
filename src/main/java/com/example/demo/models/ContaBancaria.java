package com.example.demo.models;

import org.springframework.format.annotation.NumberFormat;

public class ContaBancaria {
    private String nome;
    
    @NumberFormat(pattern = "#,##0.00")
    private double saldo;

    public ContaBancaria() {
        
    }

    public ContaBancaria(String nome, double saldo) {
        this.nome = nome;
        this.saldo = saldo;
    }

    public void depositar(double valor) {
        this.saldo += valor;
    }

    public double sacar(double valor) {
        return 0;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public String getNome() {
        return this.nome;
    }

    public double getSaldo() {
        return this.saldo;
    }
}
