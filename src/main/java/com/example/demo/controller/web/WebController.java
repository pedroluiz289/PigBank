package com.example.demo.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpSession;

import com.example.demo.models.Corrente;
import com.example.demo.models.Poupanca;

@Controller
public class WebController {
    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/entrar-corrente")
    public String entrarContaCorrente(HttpSession session) {
        Corrente corrente = new Corrente("Usuario(a)", 0, 500);

        session.setAttribute("contaCorrente", corrente);
        session.setAttribute("tipo", "corrente");

        return "redirect:/corrente";
    }

    @PostMapping("/entrar-poupanca")
    public String entrarContaPoupanca(HttpSession session) {
        Poupanca poupanca = new Poupanca("Usuario(a)", 0);

        session.setAttribute("contaPoupanca", poupanca);
        session.setAttribute("tipo", "poupanca");

        return "redirect:/poupanca";
    }

    @GetMapping("/sair")
    public String sair(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
