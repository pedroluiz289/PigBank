package projetos.pigbank.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PaginaNaoEncontradaException extends RuntimeException {
    public PaginaNaoEncontradaException(String mensagem) {
        super(mensagem);
    }
}
