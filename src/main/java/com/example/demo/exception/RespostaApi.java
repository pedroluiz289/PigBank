package com.example.demo.exception;

public class RespostaApi {
    private boolean sucesso;
    private String mensagem;

    public RespostaApi(boolean sucesso, String mensagem) {
        this.sucesso = sucesso;
        this.mensagem = mensagem;
    }

    public boolean getSucesso() {
        return this.sucesso;
    }

    public String getMensagem() {
        return this.mensagem;
    }
}
