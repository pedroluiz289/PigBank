package projetos.pigbank.dto.response.poupanca;

public record PoupancaRespostaResgateDTO(
    boolean sucesso,
    String mensagem,
    Double novoSaldo
) {}
