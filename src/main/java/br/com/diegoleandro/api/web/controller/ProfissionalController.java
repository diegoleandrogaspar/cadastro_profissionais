package br.com.diegoleandro.api.web.controller;

import br.com.diegoleandro.infrastructure.assembler.ProfessionalConverter;
import br.com.diegoleandro.api.application.dto.request.ProfessionalRequestDTO;
import br.com.diegoleandro.api.application.response.ProfessionalResponseDTO;
import br.com.diegoleandro.api.domain.entity.Professional;
import br.com.diegoleandro.api.web.exception.BusinessException;
import br.com.diegoleandro.api.web.exception.ResourceNotFoundException;
import br.com.diegoleandro.api.domain.service.ProfessionalService;
import javax.validation.Valid;

import br.com.diegoleandro.infrastructure.repository.specifications.ProfessionalSpecifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/profissionais")
public class ProfissionalController {

    private final ProfessionalService professionalService;

    @Autowired
    ProfessionalConverter professionalConverter;

    public ProfissionalController(ProfessionalService professionalService) {
        this.professionalService = professionalService;
    }

    @GetMapping
    public List<ProfessionalResponseDTO> getAll(ProfessionalSpecifications professionalSpec) {
      return  professionalConverter.toCollectionDTO(professionalService.getAllProfessional(professionalSpec));
    }

    @GetMapping("/{professionalId}")
    public ProfessionalResponseDTO getId(@PathVariable Long professionalId) {

        Professional professional = professionalService.findByIdOrThrow(professionalId);
        return professionalConverter.toDto(professional);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProfessionalResponseDTO create(@RequestBody @Valid ProfessionalRequestDTO professionalRequestDTO) {
       try {
           Professional professional = professionalConverter.toDomainObject(professionalRequestDTO);

           return professionalConverter.toDto(professionalService.createProfessional(professional));
       } catch (ResourceNotFoundException e){
           throw new BusinessException(e.getMessage());
       }

    }

    @PutMapping("/{professionalId}")
    public ProfessionalResponseDTO update(@PathVariable Long professionalId, @RequestBody @Valid ProfessionalRequestDTO professionalRequestDTO) {
        try {
            Professional professionalAtual = professionalService.findByIdOrThrow(professionalId);

            professionalConverter.copyToDomainObject(professionalRequestDTO, professionalAtual);
            return professionalConverter.toDto(professionalService.createProfessional(professionalAtual));

        } catch (ResourceNotFoundException ex) {
            throw new BusinessException(ex.getMessage());
        }

    }

    @DeleteMapping("/{professionalId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long professionalId) {
        professionalService.deleteProfessional(professionalId);
    }

    }


