package br.com.diegoleandro.api.application.dto.request;

import br.com.diegoleandro.api.domain.entity.enums.CargoEnum;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
public class ProfessionalRequestDTO {

    @NotBlank
    private String nome;

    @NotNull
    private CargoEnum cargo;

    @NotNull
    private LocalDate nascimento;

}
