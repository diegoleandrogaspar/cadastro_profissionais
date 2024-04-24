package br.com.diegoleandro.api.controller.response;

import br.com.diegoleandro.api.entity.Professional;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class ContactResponseDTO {

    private Long id;
    private String nome;
    private String contato;
    private LocalDateTime createdDate;

    private Long professionalId;

}
