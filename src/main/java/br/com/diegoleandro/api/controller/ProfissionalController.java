package br.com.diegoleandro.api.controller;

import br.com.diegoleandro.api.assembler.ProfissionalConverter;
import br.com.diegoleandro.api.entity.Profissional;
import br.com.diegoleandro.api.entity.input.ProfissionalInput;
import br.com.diegoleandro.api.exception.EntidadeNaoEncontradaException;
import br.com.diegoleandro.api.exception.NegocioException;
import br.com.diegoleandro.api.records.ProfissionalRecordDto;
import br.com.diegoleandro.api.service.ProfissionalService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profissionais")
public class ProfissionalController {

    @Autowired
    ProfissionalConverter profissionalConverter;

    private final ProfissionalService profissionalService;

    public ProfissionalController(ProfissionalService profissionalService) {
        this.profissionalService = profissionalService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProfissionalRecordDto adicionar(@RequestBody @Valid ProfissionalInput profissionalInput) {
        try{
            Profissional profissional = profissionalConverter.toDomainObject(profissionalInput);

            return profissionalConverter.toDto(profissionalService.salvar(profissional));
        } catch (EntidadeNaoEncontradaException e){
            throw new NegocioException(e.getMessage());
        }
    }


}
