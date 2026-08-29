package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

import com.example.demo.models.Usuario;

@Controller
public class BancoController {
    private Usuario usuario;

    @GetMapping("/")
    public String index() {
        if (usuario != null) {
            System.out.println("existe");
        }

        return "index";
    }

    @GetMapping("/conta-corrente")
    public String contaCorrenteWeb(Model model) {
        usuario = new Usuario("Usuario(a)", 0.00);

        model.addAttribute("usuario", usuario);
        return "corrente/corrente";
    }

    @GetMapping("/conta-corrente/editar")
    public String editarPerfil() {
        return "corrente/editarperfil";
    }

    @GetMapping("/conta-poupanca")
    public String contaPoupancaWeb() {
        return "poupanca";
    }
}
