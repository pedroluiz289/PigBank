package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

import com.example.demo.models.Usuario;
import jakarta.servlet.http.HttpSession;

@Controller
public class BancoController {
    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/entrar-corrente")
    public String correnteEntrar(Model model, HttpSession session) {
        Usuario usuario = new Usuario("Usuario(a)", 0.00);

        session.setAttribute("usuario", usuario);
        session.setAttribute("tipo", "corrente");

        return "redirect:/conta-corrente/dashboard";
    }

    @GetMapping("/conta-corrente/dashboard")
    public String correnteDashboard(HttpSession session, Model model) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");

        model.addAttribute("usuario", usuario);
        return "corrente/corrente";
    }

    @GetMapping("/conta-corrente/editar")
    public String correnteEditarPerfil(Model model) {
       
        return "corrente/editarperfil";
    }

    /*@GetMapping("/entrar-poupanca")
    public String entrarPoupanca(Model model, HttpSession session) {
        usuario = new Usuario("Usuario(a)", 0.00);

        model.addAttribute("usuario", usuario);
        session.setAttribute("tipo", "poupanca");
        
        return "redirect:/conta-poupanca/dashboard";
    }

    @GetMapping("/conta-poupanca/dashboard")
    public String contaPoupancaWeb() {
        return "poupanca";
    }*/

    @GetMapping("/sair")
    public String sair(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
