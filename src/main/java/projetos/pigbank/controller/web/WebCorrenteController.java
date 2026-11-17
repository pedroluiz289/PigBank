package projetos.pigbank.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

import projetos.pigbank.models.Corrente;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/corrente")
public class WebCorrenteController {

    @GetMapping({"", "/"})
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

        if (usuario == null) {
            return "redirect:/";
        }

        model.addAttribute("usuario", usuario);
        return "corrente/deposito";
    }

    @GetMapping("/saque")
    public String saque(Model model, HttpSession session) {
        Corrente usuario = (Corrente) session.getAttribute("contaCorrente");

        if (usuario == null) {
            return "redirect:/";
        }

        model.addAttribute("usuario", usuario);
        return "corrente/saque";
    }

    @GetMapping("/extrato")
    public String extrato(Model model, HttpSession session) {
        Corrente usuario = (Corrente) session.getAttribute("contaCorrente");

        if (usuario == null) {
            return "redirect:/";
        }

        model.addAttribute("usuario", usuario);
        return "corrente/extrato";
    }

    @GetMapping("/editar")
    public String editar(Model model, HttpSession session) {
        Corrente usuario = (Corrente) session.getAttribute("contaCorrente");

        if (usuario == null) {
            return "redirect:/";
        }

        model.addAttribute("usuario", usuario);
        return "corrente/editarperfil";
    }
}
