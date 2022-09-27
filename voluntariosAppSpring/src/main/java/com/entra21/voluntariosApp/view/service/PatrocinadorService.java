package com.entra21.voluntariosApp.view.service;


import com.entra21.voluntariosApp.model.dto.server.EventoDTOs;
import com.entra21.voluntariosApp.model.dto.server.PatrocinadorDTO;
import com.entra21.voluntariosApp.model.entity.PatrocinadorEntity;
import com.entra21.voluntariosApp.view.repository.EventoRepository;
import com.entra21.voluntariosApp.view.repository.PatrocinadorRepository;
import com.entra21.voluntariosApp.view.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class PatrocinadorService {
    @Autowired
    private PatrocinadorRepository patrocinadorRepository;
    @Autowired
    private PessoaRepository pessoaRepository;
    @Autowired
    private EventoRepository eventoRepository;

    /**
     * Retorna todos os Patrocinadores salvos no repositório.
     *
     * @return List {@code <PatrocinadorDTO>}
     */
    public List<PatrocinadorDTO> getAllPatrocinadores() {
        return patrocinadorRepository.findAll().stream().map(patrocinadorEntity -> {
            PatrocinadorDTO patrocinadorDTO = new PatrocinadorDTO();
            patrocinadorDTO.setNome(patrocinadorEntity.getNome());
            patrocinadorDTO.setIdRepresentante(patrocinadorEntity.getRepresentante().getId());
            patrocinadorDTO.setImagePath(patrocinadorEntity.getImagePath());
            return patrocinadorDTO;
        }).collect(Collectors.toList());
    }

    /**
     * Adiciona um Patrocinador ao repositório.<br>
     * Atributos de PatrocinadorDTO:
     * <li>String nome</li>
     * <li>Long idRepresentante</li>
     * <li>String imagePath</li>
     *
     * @param patrocinadorDTO
     * @throws ResponseStatusException
     */
    public void addPatrocinador(PatrocinadorDTO patrocinadorDTO) {
        pessoaRepository.findById(patrocinadorDTO.getIdRepresentante()).ifPresentOrElse(pessoa -> {
            PatrocinadorEntity patrocinadorEntity = new PatrocinadorEntity();
            patrocinadorEntity.setNome(patrocinadorDTO.getNome());
            patrocinadorEntity.setImagePath(patrocinadorDTO.getImagePath());
            patrocinadorEntity.setRepresentante(pessoa);
            patrocinadorRepository.save(patrocinadorEntity);
        }, () -> {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Pessoa não existente!");
        });
    }

    /**
     * Busca todos os eventos que o patrocinador patrocina, de acordo com o Id do patrocinador informado por parâmtro
     * @param idPatrocinador
     * @return {@code List<EventosDTOs>}
     * @throws ResponseStatusException
     */

    public List<EventoDTOs> findAllByEventos_Id(Long idPatrocinador) {
        PatrocinadorEntity p = patrocinadorRepository.findById(idPatrocinador).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.BAD_REQUEST, "Patrocinador não existente!"));
        return p.getEventos().stream().map(eE -> {
            EventoDTOs dto = new EventoDTOs();
            dto.setNome(eE.getNome());
            dto.setData(eE.getData());
            dto.setIdOrganizacao(eE.getOrganizacao().getId());
            return dto;
        }).collect(Collectors.toList());
    }

    /**
     * Deleta um patrocinador de acordo com o Id passado por parâmetro
     * @param idPatrocinador
     */
    public void deletePatrocinador(Long idPatrocinador) {
        patrocinadorRepository.deleteById(idPatrocinador);
    }
}