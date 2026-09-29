package com.example.demo.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.models.Poupanca;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping({"/poupanca", "/poupanca/"})
public class WebPoupancaController {

    @GetMapping
    public String dashboard(Model model, HttpSession session) {
        Poupanca usuario = (Poupanca) session.getAttribute("contaPoupanca");

        if (usuario == null) {
            return "redirect:/";
        }

        model.addAttribute("usuario", usuario);
        return "poupanca/dashboard";
    }
}