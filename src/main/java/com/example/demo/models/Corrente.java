package com.example.demo.models;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Corrente extends ContaBancaria {
    private double limiteTotal;
    private List<Transacao> transacoes;

    public Corrente() {
        super();
    }

    public Corrente(String nome, double saldo, double limiteTotal) {
        super(nome, saldo, 0, 0);
        this.limiteTotal = limiteTotal;
        this.transacoes = new ArrayList<>();
    }

    private void adicionarTransacao(String tipo, double valor) {
        transacoes.add(new Transacao(tipo, valor));
    }

    @Override
    public boolean depositar(double valor) {
        double saldo = getSaldo();
        double entradas = getEntradas();

        if (valor <= 0) {
            return false;
        }
 
        setSaldo(saldo + valor);
        setEntradas(entradas + valor);
        adicionarTransacao("Deposito", valor);

        return true;
    }

    @Override
    public boolean sacar(double valor) {
        double saldo = getSaldo();
        double saidas = getSaidas();

        if (saldo + this.limiteTotal >= valor) {
            setSaldo(saldo - valor);
            setSaidas(saidas + valor);
            adicionarTransacao("Saque", valor);

            return true;
        }

        return false;
    }

    public double getLimiteDisponivel() {
        if (getSaldo() < 0) {
            return this.limiteTotal + getSaldo();
        }

        return this.limiteTotal;
    }

    public List<Transacao> getTransacao() {
        this.transacoes.sort(Comparator.comparing(Transacao::getData).reversed());
        return this.transacoes;
    }
}
