package com.example.demo.models;

import java.util.ArrayList;
import java.util.List;

public class Corrente extends ContaBancaria {
    private double limiteTotal;
    private List<Transacao> transacoes;

    public Corrente() {
        super();
    }

    public Corrente(String nome, double saldo, double limiteTotal) {
        super(nome, saldo);
        this.limiteTotal = limiteTotal;
        this.transacoes = new ArrayList<>();
    }

    private void adicionarTransacao(String tipo, double valor) {
        transacoes.add(new Transacao(tipo, valor));
    }

    @Override
    public void depositar(double valor) {
        double saldo = getSaldo();

        setSaldo(saldo + valor);
        adicionarTransacao("Deposito", valor);
    }

    @Override
    public void sacar(double valor) {
        double saldo = getSaldo();

        if (saldo + this.limiteTotal >= valor) {
            setSaldo(saldo - valor);
            adicionarTransacao("Saque", valor);
        }
    }

    public double getLimiteDisponivel() {
        if (getSaldo() < 0) {
            return this.limiteTotal + getSaldo();
        }

        return this.limiteTotal;
    }

    public List<Transacao> getTransacao() {
        return this.transacoes;
    }
}
