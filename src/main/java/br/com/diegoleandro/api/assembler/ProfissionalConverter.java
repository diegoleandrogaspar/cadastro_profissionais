package br.com.diegoleandro.api.assembler;

import br.com.diegoleandro.api.entity.Profissional;
import br.com.diegoleandro.api.entity.input.ProfissionalInput;
import br.com.diegoleandro.api.records.ProfissionalRecordDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProfissionalConverter implements Converter<Profissional, ProfissionalRecordDto, ProfissionalInput>{

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Profissional toDomainObject(ProfissionalInput profissionalInput) {
        // Configuração customizada para converter o campo 'nascimento' de String para LocalDate
        modelMapper.typeMap(ProfissionalInput.class, Profissional.class)
                .addMappings(mapper -> mapper.map(src -> LocalDate.parse(src.getNascimento(), DateTimeFormatter.ISO_DATE), Profissional::setNascimento));

        return modelMapper.map(profissionalInput, Profissional.class);
    }

    @Override
    public ProfissionalRecordDto toDto(Profissional profissional) {
        return modelMapper.map(profissional, ProfissionalRecordDto.class);
    }

    @Override
    public List<ProfissionalRecordDto> toCollectionDTO(List<Profissional> profissionalList) {
        return profissionalList.stream()
                .map(profissional -> toDto(profissional))
                .collect(Collectors.toList());
    }
}
