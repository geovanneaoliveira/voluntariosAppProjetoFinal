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

    @PostMapping("/criar")
    public void criarTag(@RequestBody String nome) {
        tagsService.addTag(nome);
    }

    @DeleteMapping("/excluir")
    public void excluirTag(@RequestParam(name = "idTag") Long idTag) {
        tagsService.excluirTag(idTag);
    }

    @PutMapping("/atualizar/{idTag}")
    public void atualizarTag(@PathVariable(name = "idTag") Long idTag, @RequestParam(name = "novoNome") String novoNome) {
        tagsService.atualizarTag(idTag,novoNome);
    }

    @PostMapping("/tagsEvento")
    public void addTagsEvento(@RequestBody TagsEventoDTO tagsEventoDTO) {
        tagsService.addTagsEvento(tagsEventoDTO);
    }

    @PostMapping("/tagsPessoa")
    public void addTagsPessoa(@RequestBody TagsPessoaDTO tagsPessoaDTO) {
        tagsService.addTagsPessoa(tagsPessoaDTO);
    }
}
