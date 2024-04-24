package br.com.diegoleandro.api.controller.request;

import br.com.diegoleandro.api.controller.response.ProfessionalResponseDTO;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ContactRequestDTO {

    @NotBlank
    private String nome;

    @NotBlank
    private String contato;

    @Valid
    @NotNull
    private ProfessionalRequestDTO professionalId;

}
