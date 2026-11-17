package projetos.pigbank.dto.response.corrente;

public record CorrenteRespostaSacarDTO(
    boolean sucesso,
    String mensagem,
    Double novoSaldo,
    Double novoLimite
) {}
