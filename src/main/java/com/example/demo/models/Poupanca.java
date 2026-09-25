package com.example.demo.models;

import java.util.List;
import java.util.ArrayList;

public class Poupanca extends ContaBancaria {
    private double rendimento;
    private List<Transacao> transacoes;

    public Poupanca(String nome, double saldo) {
        super(nome, saldo, 0, 0);
        this.rendimento = 0;
        this.transacoes = new ArrayList<>();
    }

    @Override
    public boolean depositar(double valor) {
        if (valor > 0) {
            double saldoAtual = super.getSaldo();
            double entradaAtual = super.getEntradas();

            super.setSaldo(saldoAtual + valor);
            super.setEntradas(entradaAtual + valor);
            adicionarTransacao("Deposito", valor);

            return true;
        }

        return false;
    }

    @Override
    public boolean sacar(double valor) {
        if (valor > 0 && super.getSaldo() >= valor) {
            double saldoAtual = super.getSaldo();
            double saidaAtual = super.getSaidas();

            super.setSaldo(saldoAtual - valor);
            super.setSaidas(saidaAtual + valor);

            adicionarTransacao("Saque", valor);

            return true;
        }

        return false;
    }

    private void adicionarTransacao(String tipo, double valor) {
        this.transacoes.add(new Transacao(tipo, valor));
    }

    public List<Transacao> getTransacoes() {
        return this.transacoes;
    }

    public double getRendimento() {
        return this.rendimento;
    }
}
