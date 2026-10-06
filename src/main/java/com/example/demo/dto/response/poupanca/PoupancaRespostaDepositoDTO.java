package com.example.demo.dto.response.poupanca;

public record PoupancaRespostaDepositoDTO(
    boolean sucesso,
    String mensagem,
    Double novoSaldo
) {}
