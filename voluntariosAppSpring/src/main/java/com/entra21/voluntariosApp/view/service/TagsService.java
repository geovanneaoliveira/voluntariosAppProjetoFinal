package com.entra21.voluntariosApp.view.service;

import com.entra21.voluntariosApp.model.dto.TagsEventoDTO;
import com.entra21.voluntariosApp.model.dto.TagsPessoaDTO;
import com.entra21.voluntariosApp.model.entity.TagsEntity;
import com.entra21.voluntariosApp.view.repository.EventoRepository;
import com.entra21.voluntariosApp.view.repository.PessoaRepository;
import com.entra21.voluntariosApp.view.repository.TagsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class TagsService {
    @Autowired
    private TagsRepository tagsRepository;

    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    public void addTag(String nomeTag) {
        TagsEntity tagsEntity = new TagsEntity();
        tagsEntity.setNome(nomeTag);
        tagsRepository.save(tagsEntity);
    }

    public void setTagsEvento(TagsEventoDTO tagsEventoDTO) {
        eventoRepository.findById(tagsEventoDTO.getIdEvento()).ifPresentOrElse(ev -> {
            List<TagsEntity> tagsEntities = new ArrayList<>(tagsRepository.findAllById(tagsEventoDTO.getIdTagsEvento()));
            ev.setTags(tagsEntities);
            eventoRepository.save(ev);
        }, () -> {throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Evento não encontrado!");});
    }

    public void setTagsPessoa(TagsPessoaDTO tagsPessoaDTO) {
        pessoaRepository.findById(tagsPessoaDTO.getIdPessoa()).ifPresentOrElse(user -> {
            List<TagsEntity> tagsEntities = new ArrayList<>(tagsRepository.findAllById(tagsPessoaDTO.getIdTagsPessoa()));
            user.setTags(tagsEntities);
            pessoaRepository.save(user);
        }, () -> {throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário não encontrado!");});
    }

    public void deleteTag(Long idTag) {
        eventoRepository.findAll().forEach(ev -> ev.getTags().removeIf(tag -> Objects.equals(tag.getId(), idTag)));
        pessoaRepository.findAll().forEach(pe -> pe.getTags().removeIf(tag -> Objects.equals(tag.getId(), idTag)));
        tagsRepositoy.deleteById(idTag);
    }

    public void atualizarTag(Long idTag, String novoNome) {
        tagsRepositoy.findById(idTag).ifPresentOrElse(tag -> {
            tag.setNome(novoNome);
            tagsRepositoy.save(tag);
        }, () -> {throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Tag não encontrada!");});
    }
}
