package br.com.diegoleandro.api.exceptionhandler;

import lombok.Getter;

@Getter
public enum ProblemType {

    DADOS_INVALIDOS("/dados-inválidos", "Dados inválidos"),
    ERRO_DE_SISTEMA("/erro-de-sistema", "Erro de sistema"),
    MENSAGEM_INCOMPREENSIVEL("/mensagem-incompreensivel", "Mensagem incompreensível"),
    RECURSO_NAO_ENCONTRADO("/recurso-nao-encontrado", "Recurso não encontrado"),
    PARAMETRO_INVALIDO("/parametro-invalido", "Parâmetro inválido"),
    ERRO_NEGOCIO("/erro-negocio", "Violação de regra de negócio");


    private String title;
    private String uri;

    ProblemType(String path, String title) {
        this.uri = "https://simplesdental.com.br" + path;
        this.title = title;
    }
}
