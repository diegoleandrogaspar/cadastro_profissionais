package br.com.diegoleandro.api.exception;

public class ProfissionalNaoEncontradoException extends EntidadeNaoEncontradaException{

    public ProfissionalNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
}
