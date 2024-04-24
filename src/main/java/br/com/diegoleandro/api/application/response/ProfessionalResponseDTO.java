package br.com.diegoleandro.api.application.response;

import br.com.diegoleandro.api.domain.entity.enums.CargoEnum;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ProfessionalResponseDTO {

    private Long id;
    private String nome;
    private CargoEnum cargo;
    private LocalDateTime createdDate;


}
