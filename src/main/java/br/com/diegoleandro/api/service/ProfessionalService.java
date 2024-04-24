package br.com.diegoleandro.api.service;

import br.com.diegoleandro.api.entity.Professional;
import br.com.diegoleandro.api.exception.ResourceNotFoundException;
import br.com.diegoleandro.api.repository.ProfessionalRepository;
import br.com.diegoleandro.api.repository.specifications.ProfessionalSpecifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProfessionalService {

    public static final String MSG_PROFISSIONAL_NAO_ENCONTRADO =
            "Não existe um cadastro de profissional com o código %d";

    @Autowired
    private ProfessionalRepository professionalRepository;

    public List<Professional> getAllProfessional(ProfessionalSpecifications professionalSpec) {
        return professionalRepository.findAll(professionalSpec);
    }

    @Transactional
    public Professional createProfessional(Professional professional){
        return professionalRepository.save(professional);
    }

    @Transactional
    public void deleteProfessional(Long profissionalId) {
        try {
            professionalRepository.deleteById(profissionalId);
        } catch (EmptyResultDataAccessException e){
            throw new ResourceNotFoundException(
                    String.format(MSG_PROFISSIONAL_NAO_ENCONTRADO, profissionalId)
            );
        }
    }

    public Professional findByIdOrThrow(Long profissionalId) {
        return professionalRepository.findById(profissionalId)
                .orElseThrow(() -> new ResourceNotFoundException(
                String.format(MSG_PROFISSIONAL_NAO_ENCONTRADO, profissionalId)
        ));
    }


}
