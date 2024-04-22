package br.com.diegoleandro.api.exception;

public class ContatoNaoEncontradoException extends EntidadeNaoEncontradaException{

    public ContatoNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
}
