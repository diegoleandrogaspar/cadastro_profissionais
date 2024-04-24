package br.com.diegoleandro.api.assembler;

import br.com.diegoleandro.api.controller.request.ProfessionalRequestDTO;
import br.com.diegoleandro.api.controller.response.ProfessionalResponseDTO;
import br.com.diegoleandro.api.entity.Professional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProfessionalConverter implements Converter<Professional, ProfessionalResponseDTO,ProfessionalRequestDTO > {

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Professional toDomainObject(ProfessionalRequestDTO professionalRequestDTO) {
        return modelMapper.map(professionalRequestDTO, Professional.class);
    }

    @Override
    public ProfessionalResponseDTO toDto(Professional professional) {
        return modelMapper.map(professional, ProfessionalResponseDTO.class);
    }

    @Override
    public List<ProfessionalResponseDTO> toCollectionDTO(List<Professional> professionalList) {
        return professionalList.stream()
                .map(professional -> toDto(professional))
                .collect(Collectors.toList());
    }

    @Override
    public void copyToDomainObject(ProfessionalRequestDTO professionalRequestDTO, Professional professional) {
        modelMapper.map(professionalRequestDTO, professional);
    }
}
