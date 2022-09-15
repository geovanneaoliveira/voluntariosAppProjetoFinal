package com.entra21.voluntariosApp.view.service;

import com.entra21.voluntariosApp.model.dto.EventoDTO;
import com.entra21.voluntariosApp.model.entity.EventoEntity;
import com.entra21.voluntariosApp.view.repository.EventoRepository;
import com.entra21.voluntariosApp.view.repository.OrganizacaoRepository;
import org.apache.catalina.LifecycleState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EventoService {
    @Autowired
    private EventoRepository eventoRepository;


    public void adicionarEvento(EventoDTO eventoDTO){
        EventoEntity eventoEntity = new EventoEntity();
        eventoEntity.setNome(eventoDTO.getNome());
        eventoEntity.setData(eventoDTO.getData());
        eventoEntity.setOrganizacao(eventoDTO.getOrganizacaoEntity());
        eventoRepository.save(eventoEntity);
    }

    public List<EventoDTO> buscarEvento(String nome) {
        List<EventoEntity> eventos = eventoRepository.findAll().stream()
                .filter(ev -> ev.getNome().contains(nome)).collect(Collectors.toList());
        return eventos.stream().map(ev -> {
            EventoDTO dto = new EventoDTO();
            dto.setNome(ev.getNome());
            dto.setData(ev.getData());
            dto.setOrganizacaoEntity(ev.getOrganizacao());
            return dto;
        }).collect(Collectors.toList());

    }
}
