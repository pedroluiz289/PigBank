package com.example.demo.models;

import java.util.Random;

import org.springframework.format.annotation.NumberFormat;

abstract class ContaBancaria {
    private Random random = new Random();

    private String nome;
    private String conta;
    private double entradas;
    private double saidas;
    private int agencia;
    
    @NumberFormat(pattern = "#,##0.00")
    private double saldo;

    public ContaBancaria() {
        
    }

    public ContaBancaria(String nome, double saldo, double entradas, double saidas) {
        this.nome = nome;
        this.saldo = saldo;
        this.entradas = entradas;
        this.saidas = saidas;
        this.agencia = 0001;
        this.conta = String.format("%07d-%d", random.nextInt(0, 10000000), random.nextInt(0, 10));
    }

    public abstract void depositar(double valor);

    public abstract void sacar(double valor);

    public void adicionarEntrada(double valor) {
        this.entradas += valor;
    }

    public void adicionarSaida(double valor) {
        this.saidas += valor;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
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

    public String getConta() {
        return this.conta;
    }

    public int getAgencia() {
        return this.agencia;
    }
}
