package br.com.diegoleandro.api.entity.input;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ContatoInput {

    @NotBlank
    private String nome;

    @Valid
    @NotNull
    private ProfissionalInput profissionalInput;


}
