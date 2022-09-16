package com.entra21.voluntariosApp.view.service;

import com.entra21.voluntariosApp.model.dto.TagsEventoDTO;
import com.entra21.voluntariosApp.model.entity.TagsEntity;
import com.entra21.voluntariosApp.view.repository.EventoRepository;
import com.entra21.voluntariosApp.view.repository.TagsRepositoy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class TagsService {
    @Autowired
    private TagsRepositoy tagsRepositoy;

    @Autowired
    private EventoRepository eventoRepository;

    public void addTag(String nomeTag) {
        TagsEntity tagsEntity = new TagsEntity();
        tagsEntity.setNome(nomeTag);
        tagsRepositoy.save(tagsEntity);
    }

    public void addTagsEvento(TagsEventoDTO tagsEventoDTO) {
        eventoRepository.findById(tagsEventoDTO.getIdEvento()).ifPresentOrElse(ev -> {
            List<TagsEntity> tagsEntities = new ArrayList<>(tagsRepositoy.findAllById(tagsEventoDTO.getIdTagsEvento()));
            ev.setTags(tagsEntities);
            eventoRepository.save(ev);
            System.out.println(ev.getTags());
        }, () -> {throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Evento não encontrado!");});
    }
}
