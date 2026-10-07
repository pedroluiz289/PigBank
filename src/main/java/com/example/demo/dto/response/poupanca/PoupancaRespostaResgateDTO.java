package com.example.demo.dto.response.poupanca;

public record PoupancaRespostaResgateDTO(
    boolean sucesso,
    String mensagem,
    Double novoSaldo
) {}
