package com.entra21.voluntariosApp.view.service;

import com.entra21.voluntariosApp.model.dto.server.TagsEventoDTO;
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

@Service
public class TagsService {
    @Autowired
    private TagsRepository tagsRepository;

    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    /**Adiciona uma Tag com o nome passado por parâmetro ao repositório.
     * @param nomeTag
     * @throws ResponseStatusException
     * */
    public void addTag(String nomeTag) {
        tagsRepository.findAll().forEach(tag -> {
            if (tag.getNome().equalsIgnoreCase(nomeTag)){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Tag já existente!");
            }
        });
        TagsEntity tagsEntity = new TagsEntity();
        tagsEntity.setNome(nomeTag);
        tagsRepository.save(tagsEntity);
    }

    /**Adiciona Tags à um Evento.<br>
     * Atributos de TagsEventoDTO
     * <li>Long idEvento</li>
     * <li>List {@code <Long>} idTagsEvento</li>
     * @param tagsEventoDTO
     * @throws ResponseStatusException
     * */
    public void setTagsEvento(TagsEventoDTO tagsEventoDTO) {
        eventoRepository.findById(tagsEventoDTO.getIdEvento()).ifPresentOrElse(ev -> {
            List<TagsEntity> tagsEntities = new ArrayList<>(tagsRepository.findAllById(tagsEventoDTO.getIdTagsEvento()));
            ev.setTags(tagsEntities);
            eventoRepository.save(ev);
        }, () -> {throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Evento não encontrado!");});
    }

    /**Adiciona Tags ao Usuário.<br>
     * Atributos de TagsPessoaDTO
     * <li>Long idPessoa</li>
     * <li>List {@code <Long>} idTagsPessoa</li>
     * @param tagsPessoaDTO
     * @throws ResponseStatusException
     * */
    public void setTagsPessoa(TagsPessoaDTO tagsPessoaDTO) {
        pessoaRepository.findById(tagsPessoaDTO.getIdPessoa()).ifPresentOrElse(user -> {
            List<TagsEntity> tagsEntities = new ArrayList<>(tagsRepository.findAllById(tagsPessoaDTO.getIdTagsPessoa()));
            user.setTags(tagsEntities);
            pessoaRepository.save(user);
        }, () -> {throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário não encontrado!");});
    }

    /**Remove uma Tag do repositório através do seu Id.
     * @param idTag
     * */
    public void deleteTag(Long idTag) {
        eventoRepository.findAll().forEach(ev -> ev.getTags().removeIf(tag -> Objects.equals(tag.getId(), idTag)));
        pessoaRepository.findAll().forEach(pe -> pe.getTags().removeIf(tag -> Objects.equals(tag.getId(), idTag)));
        tagsRepository.deleteById(idTag);
    }

    /**Atualiza a Tag cujo Id for igual ao passado por parâmetro com a String novoNome.
     * @param idTag
     * @param novoNome
     * @throws ResponseStatusException
     * */
    public void atualizarTag(Long idTag, String novoNome) {
        tagsRepository.findById(idTag).ifPresentOrElse(tag -> {
            tag.setNome(novoNome);
            tagsRepository.save(tag);
        }, () -> {throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Tag não encontrada!");});
    }
}
