package projetos.pigbank.models;

import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;
import java.time.LocalDateTime;

public class Poupanca extends ContaBancaria {
    private double taxaRendimento;
    private LocalDateTime ultimaAtualizacao;
    private List<Transacao> transacoes;

    public Poupanca() {
        super();
        this.taxaRendimento = 0.01;
        this.transacoes = new ArrayList<>();
        this.ultimaAtualizacao = LocalDateTime.now();
    }

    public Poupanca(String nome, double saldo) {
        super(nome, saldo, 0, 0);
        this.taxaRendimento = 0.01;
        this.transacoes = new ArrayList<>();
        this.ultimaAtualizacao = LocalDateTime.now();
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
        double saldoAtual = super.getSaldo();

        super.setSaldo(saldoAtual - valor);
        super.adicionarSaida(valor);

        adicionarTransacao("Resgate", valor); 
    }

    public void aplicarRendimento(double valor) {
        super.setSaldo(super.getSaldo() + valor);

        adicionarTransacao("Rendimento", valor);
        adicionarEntrada(valor);
    }

    private void adicionarTransacao(String tipo, double valor) {
        this.transacoes.add(new Transacao(tipo, valor));
    }

    public void setTaxaRendimento(double valor) {
        this.taxaRendimento = valor;
    }

    public void setUltimaAtualizacao(LocalDateTime ultimaAtualizacao) {
        this.ultimaAtualizacao = ultimaAtualizacao;
    }

    public List<Transacao> getTransacoes() {
        this.transacoes.sort(Comparator.comparing(Transacao::getData).reversed());
        return this.transacoes;
    }

    public double getTaxaRendimento() {
        return this.taxaRendimento;
    }

    public LocalDateTime getUltimaAtualizacao() {
        return this.ultimaAtualizacao;
    }
}
