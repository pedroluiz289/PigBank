package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;

import com.example.demo.models.Corrente;
import jakarta.servlet.http.HttpSession;

@Controller
public class BancoController {
    private Corrente corrente;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    /*
       ================
        Conta corrente
       ================
    */

    @GetMapping("/entrar-corrente")
    public String correnteEntrar(Model model, HttpSession session) {
        if (corrente == null) {
            corrente = new Corrente("Usuario(a)", 0, 500);
        }

        session.setAttribute("usuario", corrente);
        session.setAttribute("tipo", "corrente");

        return "redirect:/corrente/dashboard";
    }

    @GetMapping("/corrente/dashboard")
    public String correnteDashboard(Model model) {
        model.addAttribute("usuario", corrente);
        return "corrente/corrente";
    }

    @GetMapping("/corrente/editar")
    public String correnteEditarPerfil(Model model) {
        model.addAttribute("usuario", corrente);
        return "corrente/editarperfil";
    }

    @PostMapping("/corrente/salvar")
    public String salvarInformacoes(@ModelAttribute("usuario") Corrente form) {
        corrente.setNome(form.getNome());
        return "redirect:/corrente/dashboard";
    }

    @GetMapping("/corrente/deposito")
    public String correnteDeposito(Model model) {
        model.addAttribute("usuario", corrente);
        return "corrente/deposito";
    }

    @PostMapping("/corrente/salvardeposito")
    public String correnteSalvarDeposito(@ModelAttribute("usuario") Corrente form) {
        corrente.depositar(form.getSaldo());
        return "redirect:/corrente/dashboard";
    }

    @GetMapping("/corrente/saque")
    public String correnteSaque(Model model) {
        model.addAttribute("usuario", corrente);
        return "corrente/saque";
    }

    @PostMapping("/corrente/sacar")
    public String correnteSacar(@ModelAttribute("usuario") Corrente form) {
        corrente.sacar(form.getSaldo());
        return "redirect:/corrente/dashboard";
    }

    @GetMapping("/corrente/extrato")
    public String correnteExtrato(Model model) {
        model.addAttribute("usuario", corrente);
        return "corrente/extrato";
    }

    /*
       ================
        Conta poupanca
       ================
    */

    /*@GetMapping("/entrar-poupanca")
    public String entrarPoupanca(Model model, HttpSession session) {
        usuario = new Usuario("Usuario(a)", 0.00);

        model.addAttribute("usuario", usuario);
        session.setAttribute("tipo", "poupanca");
        
        return "redirect:/poupanca/dashboard";
    }

    @GetMapping("/poupanca/dashboard")
    public String contaPoupancaWeb(HttpSession session, Model model) {
        usuario = (Usuario) session.getAttribute("usuario");

        model.addAttribute("usuario", usuario);
        return "poupanca/poupanca";
    }*/

    @GetMapping("/sair")
    public String sair(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}