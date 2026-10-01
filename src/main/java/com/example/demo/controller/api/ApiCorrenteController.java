package com.example.demo.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.request.corrente.CorrenteDepositoDTO;
import com.example.demo.exception.RespostaApi;
import com.example.demo.models.Corrente;
import com.example.demo.service.CorrenteService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping({"/corrente", "/corrente/"})
public class ApiCorrenteController {

    @Autowired
    private CorrenteService correnteService;

    @PostMapping("/depositar")
    public ResponseEntity<RespostaApi> depositar(@RequestBody CorrenteDepositoDTO dto, HttpSession session) {
        Corrente usuario = (Corrente) session.getAttribute("contaCorrente");

        if (usuario == null) {
            return ResponseEntity.status(401).body(new RespostaApi(false, "Sessão expirada."));
        }

        try {
            correnteService.deposito(usuario, dto.saldo());

            return ResponseEntity.ok(new RespostaApi(true, "Depósito de R$" + String.format("%.2f", dto.saldo()) + " foi realizado com sucesso!"));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new RespostaApi(false, e.getMessage()));
        }
    }
}
