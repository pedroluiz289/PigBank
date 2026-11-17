package projetos.pigbank.dto.response.corrente;

public record CorrenteRespostaDepositoDTO(
    boolean sucesso,
    String mensagem,
    Double novoSaldo
) {}
