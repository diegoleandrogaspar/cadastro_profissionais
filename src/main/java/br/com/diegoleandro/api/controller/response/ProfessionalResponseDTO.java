package br.com.diegoleandro.api.controller.response;

import br.com.diegoleandro.api.entity.enums.CargoEnum;
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
