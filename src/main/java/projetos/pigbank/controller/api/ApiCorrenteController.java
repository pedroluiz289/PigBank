package projetos.pigbank.controller.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import projetos.pigbank.dto.request.corrente.CorrenteDepositoDTO;
import projetos.pigbank.dto.request.corrente.CorrenteSacarDTO;
import projetos.pigbank.dto.request.corrente.CorrenteSalvarDTO;
import projetos.pigbank.dto.response.corrente.CorrenteRespostaDepositoDTO;
import projetos.pigbank.dto.response.corrente.CorrenteRespostaSacarDTO;
import projetos.pigbank.dto.response.corrente.CorrenteRespostaSalvarDTO;
import projetos.pigbank.exception.RespostaApi;
import projetos.pigbank.models.Corrente;
import projetos.pigbank.service.CorrenteService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping({"/corrente", "/corrente/"})
public class ApiCorrenteController {
    private final CorrenteService correnteService;

    public ApiCorrenteController(CorrenteService correnteService) {
        this.correnteService = correnteService;
    }

    @PostMapping("/depositar")
    public ResponseEntity<?> depositar(@RequestBody CorrenteDepositoDTO dto, HttpSession session) {
        Corrente usuario = (Corrente) session.getAttribute("contaCorrente");

        if (usuario == null) {
            return ResponseEntity.status(401).body(new RespostaApi(false, "Sessão expirada."));
        }

        try {
            CorrenteRespostaDepositoDTO resposta = correnteService.deposito(usuario, dto.saldo());

            return ResponseEntity.ok(resposta);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new RespostaApi(false, e.getMessage()));
        }
    }

    @PostMapping("/sacar")
    public ResponseEntity<?> sacar(@RequestBody CorrenteSacarDTO dto, HttpSession session) {
        Corrente usuario = (Corrente) session.getAttribute("contaCorrente");

        if (usuario == null) {
            return ResponseEntity.status(401).body(new RespostaApi(false, "Sessão expirada."));
        }

        try {
            CorrenteRespostaSacarDTO resposta = correnteService.sacar(usuario, dto.saldo());

            return ResponseEntity.ok(resposta);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new RespostaApi(false, e.getMessage()));
        }
    }

    @PatchMapping("/salvar")
    public ResponseEntity<?> salvar(@RequestBody CorrenteSalvarDTO dto, HttpSession session) {
        Corrente usuario = (Corrente) session.getAttribute("contaCorrente");

        if (usuario == null) {
            return ResponseEntity.status(401).body(new RespostaApi(false, "Sessão expirada."));
        }

        try {
            CorrenteRespostaSalvarDTO resposta = correnteService.salvar(usuario, dto.nome());

            return ResponseEntity.ok(resposta);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new RespostaApi(false, e.getMessage()));
        }
    }
}
