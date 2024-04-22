package br.com.diegoleandro.api.controller;

import br.com.diegoleandro.api.assembler.ContatoConverter;
import br.com.diegoleandro.api.entity.Contato;
import br.com.diegoleandro.api.entity.input.ContatoInput;
import br.com.diegoleandro.api.exception.EntidadeNaoEncontradaException;
import br.com.diegoleandro.api.exception.NegocioException;
import br.com.diegoleandro.api.records.ContatoRecordDto;
import br.com.diegoleandro.api.service.ContatoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contatos")
public class ContatoController {

    private final ContatoService contatoService;

    @Autowired
    private ContatoConverter contatoConverter;

    public ContatoController(ContatoService contatoService) {
        this.contatoService = contatoService;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ContatoRecordDto adicionar(@RequestBody @Valid ContatoInput contatoInput) {
        try{
           Contato contato = contatoConverter.toDomainObject(contatoInput);

           return contatoConverter.toDto(contatoService.salvar(contato));
        } catch (EntidadeNaoEncontradaException e){
            throw new NegocioException((e.getMessage()));
        }
    }


}
