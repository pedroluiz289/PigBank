package com.example.demo.models;

public class Poupanca extends ContaBancaria {
    public Poupanca(String nome, double saldo) {
        super(nome, saldo, 0, 0);
    }

    @Override
    public void depositar(double valor) {

    }

    @Override
    public void sacar(double valor) {

    }
}
