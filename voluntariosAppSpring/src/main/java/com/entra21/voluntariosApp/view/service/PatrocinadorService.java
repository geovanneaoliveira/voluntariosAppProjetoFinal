package com.entra21.voluntariosApp.view.service;


import com.entra21.voluntariosApp.model.dto.server.EventoDTOs;
import com.entra21.voluntariosApp.model.dto.server.PatrocinadorDTOs;
import com.entra21.voluntariosApp.model.entity.PatrocinadorEntity;
import com.entra21.voluntariosApp.view.repository.EventoRepository;
import com.entra21.voluntariosApp.view.repository.PatrocinadorRepository;
import com.entra21.voluntariosApp.view.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
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
    public List<PatrocinadorDTOs> retornarPatrocinadores() {
        return patrocinadorRepository.findAll().stream().map(patrocinadorEntity -> {
            PatrocinadorDTOs patrocinadorDTOs = new PatrocinadorDTOs();
            patrocinadorDTOs.setNome(patrocinadorEntity.getNome());
            patrocinadorDTOs.setIdRepresentante(patrocinadorEntity.getRepresentante().getId());
            patrocinadorDTOs.setFotoPatrocinador(patrocinadorEntity.getFotoPatrocinador());
            return patrocinadorDTOs;
        }).collect(Collectors.toList());
    }

    /**
     * Adiciona um Patrocinador ao repositório.<br>
     * Atributos de PatrocinadorDTO:
     * <li>String nome</li>
     * <li>Long idRepresentante</li>
     * <li>String imagePath</li>
     *
     * @param patrocinadorDTOs
     * @throws ResponseStatusException
     */
    public void addPatrocinador(PatrocinadorDTOs patrocinadorDTOs) {
        pessoaRepository.findById(patrocinadorDTOs.getIdRepresentante()).ifPresentOrElse(pessoa -> {
            PatrocinadorEntity patrocinadorEntity = new PatrocinadorEntity();
            patrocinadorEntity.setNome(patrocinadorDTOs.getNome());
            patrocinadorEntity.setFotoPatrocinador(patrocinadorDTOs.getFotoPatrocinador());
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
    public List<EventoDTOs> buscarEventosPatrocinadosPorId(Long idPatrocinador) {
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
    public void excluirPatrocinador(Long idPatrocinador) {
        patrocinadorRepository.deleteById(idPatrocinador);
    }
}