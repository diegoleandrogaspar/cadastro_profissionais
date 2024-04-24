package br.com.diegoleandro.api.application.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ContactResponseDTO {

    private Long id;
    private String nome;
    private String contato;
    private LocalDateTime createdDate;

    @JsonProperty("profissional_id")
    private ProfessionalResponseDTO profissionalId;

}
