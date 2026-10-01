package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.models.Corrente;

@Service
public class CorrenteService {
    public void deposito(Corrente usuario, double valor) {
        if (valor <= 0) {
            throw new IllegalArgumentException("O valor do deposito tem que ser maior que zero");
        }

        usuario.depositar(valor);
        
    }
}
