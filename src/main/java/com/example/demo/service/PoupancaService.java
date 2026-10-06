package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.dto.response.poupanca.PoupancaRespostaDepositoDTO;
import com.example.demo.models.Poupanca;

@Service 
public class PoupancaService {
    public PoupancaRespostaDepositoDTO deposito(Poupanca usuario, double valor) {
        if (valor <= 0) {
            throw new IllegalArgumentException("O valor do deposito tem que ser maior que zero");
        }

        usuario.depositar(valor);
        return new PoupancaRespostaDepositoDTO(true, "Depósito de R$" + String.format("%.2f", valor) + " foi realizado com sucesso!", usuario.getSaldo());
    }
}
