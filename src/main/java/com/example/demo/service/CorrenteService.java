package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.dto.response.corrente.CorrenteRespostaDepositoDTO;
import com.example.demo.dto.response.corrente.CorrenteRespostaSacarDTO;
import com.example.demo.dto.response.corrente.CorrenteRespostaSalvarDTO;
import com.example.demo.exception.SaldoInsuficienteException;
import com.example.demo.models.Corrente;

@Service
public class CorrenteService {
    public CorrenteRespostaDepositoDTO deposito(Corrente usuario, double valor) {
        if (valor <= 0) {
            throw new IllegalArgumentException("O valor do deposito tem que ser maior que zero");
        }

        usuario.depositar(valor);
        return new CorrenteRespostaDepositoDTO(true, "Depósito de R$" + String.format("%.2f", valor) + " foi realizado com sucesso!", usuario.getSaldo());
    }

    public CorrenteRespostaSacarDTO sacar(Corrente usuario, double valor) {
        if (valor <= 0) {
            throw new IllegalArgumentException("O valor do saque tem que ser maior que zero");
        }

        double totalDisponivel = usuario.getSaldo() + usuario.getLimiteTotal();

        if (valor > totalDisponivel) {
            throw new SaldoInsuficienteException("Saldo e limite de crédito insuficientes.");
        }

        usuario.sacar(valor);
        return new CorrenteRespostaSacarDTO(true, "Saque de R$" + String.format("%.2f", valor) + " foi realizado com sucesso!", usuario.getSaldo(), usuario.getLimiteTotalDisponivel());
    }

    public CorrenteRespostaSalvarDTO salvar(Corrente usuario, String nome) {
        if (nome.isEmpty() || nome.isBlank()) {
            throw new IllegalArgumentException("O campo do nome não pode estar vazio ou conter somente espaço.");
        }

        usuario.setNome(nome);
        return new CorrenteRespostaSalvarDTO(true, "O nome foi alterado com sucesso");
    }
}
