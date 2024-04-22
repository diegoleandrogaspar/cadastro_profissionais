package br.com.diegoleandro.api.records;

import br.com.diegoleandro.api.entity.Profissional;

import java.time.LocalDateTime;

public record ContatoRecordDto(
    Long id,
    String nome,
    String contato,
    LocalDateTime createdDate,
    Profissional profissional
) { }
