package com.entra21.voluntariosApp.view.service;

import com.entra21.voluntariosApp.model.dto.server.PatrocinadorDTO;
import com.entra21.voluntariosApp.model.entity.PatrocinadorEntity;
import com.entra21.voluntariosApp.view.repository.PatrocinadorRepository;
import com.entra21.voluntariosApp.view.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PatrocinadorService {
    @Autowired
    private PatrocinadorRepository patrocinadorRepository;
    @Autowired
    private PessoaRepository pessoaRepository;

    /**
     * Retorna todos os Patrocinadores salvos no repositório.
     * @return List {@code <PatrocinadorDTO>}
     */
    public List<PatrocinadorDTO> getAllPatrocinadores() {
        return patrocinadorRepository.findAll().stream().map(patrocinadorEntity -> {
            PatrocinadorDTO patrocinadorDTO = new PatrocinadorDTO();
            patrocinadorDTO.setNome(patrocinadorEntity.getNome());
            patrocinadorDTO.setIdRepresentante(patrocinadorEntity.getRepresentante().getId());
            return patrocinadorDTO;
        }).collect(Collectors.toList());
    }

    /**
     * Adiciona um Patrocinador ao repositório.<br>
     * Atributos de PatrocinadorDTO:
     * <li>String nome</li>
     * <li>Long idRepresentante</li>
     * @param patrocinadorDTO
     * @throws ResponseStatusException
     */
    public void addPatrocinador(PatrocinadorDTO patrocinadorDTO) {
        pessoaRepository.findById(patrocinadorDTO.getIdRepresentante()).ifPresentOrElse(pessoa -> {
            PatrocinadorEntity patrocinadorEntity = new PatrocinadorEntity();
            patrocinadorEntity.setNome(patrocinadorDTO.getNome());
            patrocinadorEntity.setRepresentante(pessoa);
            patrocinadorRepository.save(patrocinadorEntity);
        }, () -> {throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Pessoa não existente!");});
    }

}
