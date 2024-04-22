package br.com.diegoleandro.api.records;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record ProfissionalRecordDto(Long id, String nome, String cargo, LocalDate nascimento, LocalDateTime createdDate) {
}
