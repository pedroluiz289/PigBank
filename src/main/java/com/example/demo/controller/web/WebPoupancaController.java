package com.example.demo.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.models.Poupanca;
import com.example.demo.service.PoupancaService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/poupanca")
public class WebPoupancaController {
    private final PoupancaService poupancaService;

    public WebPoupancaController(PoupancaService poupancaService) {
        this.poupancaService = poupancaService;
    }

    @GetMapping({"", "/"})
    public String dashboard(Model model, HttpSession session) {
        Poupanca usuario = (Poupanca) session.getAttribute("contaPoupanca");

        if (usuario == null) {
            return "redirect:/";
        }

        poupancaService.atualizarRendimento(usuario);

        model.addAttribute("usuario", usuario);
        return "poupanca/dashboard";
    }

    @GetMapping("/deposito")
    public String deposito(Model model, HttpSession session) {
        Poupanca usuario = (Poupanca) session.getAttribute("contaPoupanca");

        if (usuario == null) {
            return "redirect:/";
        }

        model.addAttribute("usuario", usuario);
        return "poupanca/deposito";
    }

    @GetMapping("/resgate")
    public String resgate(Model model, HttpSession session) {
        Poupanca usuario = (Poupanca) session.getAttribute("contaPoupanca");

        if (usuario == null) {
            return "redirect:/";
        }

        model.addAttribute("usuario", usuario);
        return "poupanca/resgate";
    }

    @GetMapping("/extrato")
    public String extrato(Model model, HttpSession session) {
        Poupanca usuario = (Poupanca) session.getAttribute("contaPoupanca");

        if (usuario == null) {
            return "redirect:/";
        }

        model.addAttribute("usuario", usuario);
        return "poupanca/extrato";
    }

    @GetMapping("/simulacao")
    public String simulacao(Model model, HttpSession session) {
        Poupanca usuario = (Poupanca) session.getAttribute("contaPoupanca");

        if (usuario == null) {
            return "redirect:/";
        }

        model.addAttribute("usuario", usuario);
        return "poupanca/simulacao";
    }

    @GetMapping("/editar")
    public String editar(Model model, HttpSession session) {
        Poupanca usuario = (Poupanca) session.getAttribute("contaPoupanca");

        if (usuario == null) {
            return "redirect:/";
        }

        model.addAttribute("usuario", usuario);
        return "poupanca/editarperfil";
    }
}