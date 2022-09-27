package com.entra21.voluntariosApp.controller;

import com.entra21.voluntariosApp.model.dto.server.TagsEventoDTO;
import com.entra21.voluntariosApp.model.dto.server.TagsPessoaDTO;
import com.entra21.voluntariosApp.view.service.TagsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tags")
public class TagsRestController {

    @Autowired
    private TagsService tagsService;

    /**
     * Chama um método que cria uma nova tag
     * @param nome
     */
    @PostMapping("/criar")
    public void criarTag(@RequestBody String nome) {
        tagsService.addTag(nome);
    }

    /**
     * Chama um método que deleta uma tag de acordo com o Id informado por parâmetro
     * @param idTag
     */
    @DeleteMapping("/excluir")
    public void excluirTag(@RequestParam(name = "idTag") Long idTag) {
        tagsService.excluirTag(idTag);
    }

    /**
     * Chama um método que atualiza o nome de uma tag
     * @param idTag
     * @param novoNome
     */
    @PutMapping("/atualizar/{idTag}")
    public void atualizarTag(@PathVariable(name = "idTag") Long idTag, @RequestParam(name = "novoNome") String novoNome) {
        tagsService.atualizarTag(idTag,novoNome);
    }

    /**
     * Adiciona as tags á um evento
     * @param tagsEventoDTO
     */
    @PostMapping("/tagsEvento")
    public void addTagsEvento(@RequestBody TagsEventoDTO tagsEventoDTO) {
        tagsService.addTagsEvento(tagsEventoDTO);
    }

    /**
     * Adiciona as tags á uma pessoa
     * @param tagsPessoaDTO
     */
    @PostMapping("/tagsPessoa")
    public void addTagsPessoa(@RequestBody TagsPessoaDTO tagsPessoaDTO) {
        tagsService.addTagsPessoa(tagsPessoaDTO);
    }
}
