package com.ads.loja;

import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;

@Component
public class Retorno {
    private String mensagem;
    private HttpStatusCode status;
    public String getMensagem() {
        return mensagem;
    }
    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
    public HttpStatusCode getStatus() {
        return status;
    }
    public void setStatus(HttpStatusCode status) {
        this.status = status;
    }
}
