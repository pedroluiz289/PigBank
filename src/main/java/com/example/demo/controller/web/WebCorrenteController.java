package com.example.demo.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.models.Corrente;

import jakarta.servlet.http.HttpSession;

import org.springframework.ui.Model;

@Controller
@RequestMapping({"/corrente", "/corrente/"})
public class WebCorrenteController {

    @GetMapping
    public String dashboard(Model model, HttpSession session) {
        Corrente usuario = (Corrente) session.getAttribute("contaCorrente");

        if (usuario == null) {
            return "redirect:/";
        }

        model.addAttribute("usuario", usuario);
        return "corrente/dashboard";
    }

    @GetMapping("/deposito")
    public String deposito(Model model, HttpSession session) {
        Corrente usuario = (Corrente) session.getAttribute("contaCorrente");

        model.addAttribute("usuario", usuario);
        return "corrente/deposito";
    }
}
