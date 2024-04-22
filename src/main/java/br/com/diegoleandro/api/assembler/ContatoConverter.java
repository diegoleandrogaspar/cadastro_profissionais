package br.com.diegoleandro.api.assembler;

import br.com.diegoleandro.api.entity.Contato;
import br.com.diegoleandro.api.entity.input.ContatoInput;
import br.com.diegoleandro.api.records.ContatoRecordDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ContatoConverter implements Converter<Contato, ContatoRecordDto, ContatoInput>{

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public Contato toDomainObject(ContatoInput contatoInput) {
        return modelMapper.map(contatoInput, Contato.class );
    }

    @Override
    public ContatoRecordDto toDto(Contato contato) {
        return modelMapper.map(contato, ContatoRecordDto.class);
    }

    @Override
    public List<ContatoRecordDto> toCollectionDTO(List<Contato> contatos) {
        return contatos.stream()
                .map(contato -> toDto(contato))
                .collect(Collectors.toList());
    }

}
