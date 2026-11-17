package projetos.pigbank.dto.response.poupanca;

public record PoupancaRespostaDepositoDTO(
    boolean sucesso,
    String mensagem,
    Double novoSaldo
) {}
