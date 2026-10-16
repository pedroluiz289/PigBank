package com.example.demo.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.request.poupanca.PoupancaDepositoDTO;
import com.example.demo.dto.request.poupanca.PoupancaSalvarDTO;
import com.example.demo.dto.response.poupanca.PoupancaRespostaDepositoDTO;
import com.example.demo.dto.response.poupanca.PoupancaRespostaResgateDTO;
import com.example.demo.dto.response.poupanca.PoupancaRespostaSalvarDTO;
import com.example.demo.exception.RespostaApi;
import com.example.demo.models.Poupanca;
import com.example.demo.service.PoupancaService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping({"/poupanca", "/poupanca/"})
public class ApiPoupancaController {
    @Autowired
    private PoupancaService poupancaService;

    @PostMapping("/depositar")
    public ResponseEntity<?> depositar(@RequestBody PoupancaDepositoDTO dto, HttpSession session) {
        Poupanca usuario = (Poupanca) session.getAttribute("contaPoupanca");

        if (usuario == null) {
            return ResponseEntity.status(401).body(new RespostaApi(false, "Sessão expirada."));
        }

        try {
            PoupancaRespostaDepositoDTO resposta = poupancaService.deposito(usuario, dto.saldo());

            return ResponseEntity.ok(resposta);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new RespostaApi(false, e.getMessage()));
        }
    }

    @PostMapping("/resgatar")
    public ResponseEntity<?> resgatar(@RequestBody PoupancaDepositoDTO dto, HttpSession session) {
        Poupanca usuario = (Poupanca) session.getAttribute("contaPoupanca");

        if (usuario == null) {
            return ResponseEntity.status(401).body(new RespostaApi(false, "Sessão expirada."));
        }

        try {
            PoupancaRespostaResgateDTO resposta = poupancaService.resgate(usuario, dto.saldo());

            return ResponseEntity.ok(resposta);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new RespostaApi(false, e.getMessage()));
        }
    }

    @PatchMapping("/salvar")
    public ResponseEntity<?> salvar(@RequestBody PoupancaSalvarDTO dto, HttpSession session) {
        Poupanca usuario = (Poupanca) session.getAttribute("contaPoupanca");

        if (usuario == null) {
            return ResponseEntity.status(401).body(new RespostaApi(false, "Sessão expirada."));
        }

        try {
            PoupancaRespostaSalvarDTO resposta = poupancaService.salvar(usuario, dto.nome());

            return ResponseEntity.ok(resposta);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new RespostaApi(false, e.getMessage()));
        }
    }
}
