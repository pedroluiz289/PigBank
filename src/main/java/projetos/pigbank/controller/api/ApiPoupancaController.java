package projetos.pigbank.controller.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import projetos.pigbank.dto.request.poupanca.PoupancaDepositoDTO;
import projetos.pigbank.dto.request.poupanca.PoupancaSalvarDTO;
import projetos.pigbank.dto.response.poupanca.PoupancaRespostaDepositoDTO;
import projetos.pigbank.dto.response.poupanca.PoupancaRespostaResgateDTO;
import projetos.pigbank.dto.response.poupanca.PoupancaRespostaSalvarDTO;
import projetos.pigbank.exception.RespostaApi;
import projetos.pigbank.models.Poupanca;
import projetos.pigbank.service.PoupancaService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping({"/poupanca", "/poupanca/"})
public class ApiPoupancaController {
    private final PoupancaService poupancaService;

    public ApiPoupancaController(PoupancaService poupancaService) {
        this.poupancaService = poupancaService;
    }

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
