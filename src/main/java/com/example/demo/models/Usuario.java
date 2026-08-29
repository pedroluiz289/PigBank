package com.example.demo.models;

public class Usuario {
    private String nome;
    private double saldo;

    public Usuario() {
        
    }

    public Usuario(String nome, double saldo) {
        this.nome = nome;
        this.saldo = saldo;
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
