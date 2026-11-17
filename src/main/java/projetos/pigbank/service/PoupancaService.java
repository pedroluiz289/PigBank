package projetos.pigbank.service;

import java.time.Duration;
import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import projetos.pigbank.dto.response.poupanca.PoupancaRespostaDepositoDTO;
import projetos.pigbank.dto.response.poupanca.PoupancaRespostaResgateDTO;
import projetos.pigbank.dto.response.poupanca.PoupancaRespostaSalvarDTO;
import projetos.pigbank.exception.SaldoInsuficienteException;
import projetos.pigbank.models.Poupanca;

@Service 
public class PoupancaService {
    public PoupancaRespostaDepositoDTO deposito(Poupanca usuario, double valor) {
        if (valor <= 0) {
            throw new IllegalArgumentException("O valor do deposito tem que ser maior que zero");
        }

        usuario.depositar(valor);
        return new PoupancaRespostaDepositoDTO(true, "Depósito de R$" + String.format("%.2f", valor) + " foi realizado com sucesso!", usuario.getSaldo());
    }

    public PoupancaRespostaResgateDTO resgate(Poupanca usuario, double valor) {
        if (valor <= 0) {
            throw new IllegalArgumentException("O valor do resgate tem que ser maior que zero");
        }

        if (valor > usuario.getSaldo()) {
            throw new SaldoInsuficienteException("Saldo insuficiente para o resgate");
        }

        usuario.sacar(valor);
        return new PoupancaRespostaResgateDTO(true, "Resgate de R$" + String.format("%.2f", valor) + " foi realizado com sucesso!", usuario.getSaldo());
    }

    public PoupancaRespostaSalvarDTO salvar(Poupanca usuario, String nome) {
        if (nome.isEmpty() || nome.isBlank()) {
            throw new IllegalArgumentException("O campo do nome não pode estar vazio ou conter somente espaço.");
        }

        usuario.setNome(nome);
        return new PoupancaRespostaSalvarDTO(true, "O nome foi alterado com sucesso");
    }

    public void atualizarRendimento(Poupanca usuario) {
        if (usuario.getSaldo() <= 0) {
            return;
        }

        LocalDateTime agora = LocalDateTime.now();

        long tempoDecorridos = Duration.between(usuario.getUltimaAtualizacao(), agora).toMinutes();

        if (tempoDecorridos > 0) {
            double rendimento = usuario.getSaldo() * usuario.getTaxaRendimento();

            usuario.aplicarRendimento(rendimento);
            usuario.setUltimaAtualizacao(usuario.getUltimaAtualizacao().plusMinutes(tempoDecorridos));
        }
    }
}
