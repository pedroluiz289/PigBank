package com.example.demo.dto.response.corrente;

public record CorrenteRespostaSacarDTO(
    boolean sucesso,
    String mensagem,
    Double novoSaldo
) {}
