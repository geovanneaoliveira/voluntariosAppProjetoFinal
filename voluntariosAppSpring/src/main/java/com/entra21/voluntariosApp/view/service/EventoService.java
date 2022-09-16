package com.entra21.voluntariosApp.view.service;

import com.entra21.voluntariosApp.model.dto.EventoDTO;
import com.entra21.voluntariosApp.model.dto.PessoasEventoDTO;
import com.entra21.voluntariosApp.model.entity.EventoEntity;
import com.entra21.voluntariosApp.model.entity.OrganizacaoEntity;
import com.entra21.voluntariosApp.model.entity.PessoasEventoEntity;
import com.entra21.voluntariosApp.view.repository.EventoRepository;
import com.entra21.voluntariosApp.view.repository.OrganizacaoRepository;
import com.entra21.voluntariosApp.view.repository.PessoasEventoRepository;
import org.apache.catalina.LifecycleState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EventoService {

    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private PessoasEventoRepository pessoasEventoRepository;

    @Autowired
    private OrganizacaoRepository organizacaoRepository;



    public void adicionarEvento(EventoDTO eventoDTO) {
        organizacaoRepository.findById(eventoDTO.getIdOrganizacao()).ifPresentOrElse(org -> {
            EventoEntity eventoEntity = new EventoEntity();
            eventoEntity.setNome(eventoDTO.getNome());
            eventoEntity.setData(eventoDTO.getData());
            eventoEntity.setOrganizacao(org);
            eventoRepository.save(eventoEntity);
        }, () -> {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Organização não encontrada!");
        });
    }



    public List<EventoDTO> buscarEvento(String nome) {
        List<EventoEntity> eventos = eventoRepository.findAll().stream()
                .filter(ev -> ev.getNome().contains(nome)).collect(Collectors.toList());
        return eventos.stream().map(ev -> {
            EventoDTO dto = new EventoDTO();
            dto.setNome(ev.getNome());
            dto.setData(ev.getData());
            dto.setIdOrganizacao(ev.getOrganizacao().getId());
            return dto;
        }).collect(Collectors.toList());

    }

    public List<PessoasEventoDTO> buscarpresenca(Long idEvento){
        List<PessoasEventoEntity> pessoasEvento = pessoasEventoRepository.findAll().stream()
                .filter(pv -> pv.getPresenca().booleanValue()).collect(Collectors.toList());
        return pessoasEvento.stream().map(pv -> {
            PessoasEventoDTO dto = new PessoasEventoDTO();
            dto.setId_evento(pv.getIdEvento());
            dto.setId_pessoa(pv.getIdPessoa());
            dto.setPresenca(pv.getPresenca());
            return dto;
        }).collect(Collectors.toList());
    }
}
//
//    public void adicionarPessoa(PessoasEventoDTO pessoasEventoDTO){
//
//    }
