package com.example.demo.models;

import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;

public class Poupanca extends ContaBancaria {
    private double rendimento;
    private List<Transacao> transacoes;

    public Poupanca() {
        super();
        this.transacoes = new ArrayList<>();
    }

    public Poupanca(String nome, double saldo) {
        super(nome, saldo, 0, 0);
        this.rendimento = 0;
        this.transacoes = new ArrayList<>();
    }

    @Override
    public void depositar(double valor) {
        double saldoAtual = super.getSaldo();

        super.setSaldo(saldoAtual + valor);
        super.adicionarEntrada(valor);
        adicionarTransacao("Deposito", valor);
    }

    @Override
    public void sacar(double valor) {
        if (valor > 0 && super.getSaldo() >= valor) {
            double saldoAtual = super.getSaldo();
            double saidaAtual = super.getSaidas();

            super.setSaldo(saldoAtual - valor);
            super.setSaidas(saidaAtual + valor);

            adicionarTransacao("Saque", valor);
        }
    }

    private void adicionarTransacao(String tipo, double valor) {
        this.transacoes.add(new Transacao(tipo, valor));
    }

    public List<Transacao> getTransacoes() {
        this.transacoes.sort(Comparator.comparing(Transacao::getData).reversed());
        return this.transacoes;
    }

    public double getRendimento() {
        return this.rendimento;
    }
}
