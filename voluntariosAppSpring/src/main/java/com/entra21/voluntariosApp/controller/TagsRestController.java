package com.entra21.voluntariosApp.controller;

import com.entra21.voluntariosApp.model.dto.server.TagsEventoDTO;
import com.entra21.voluntariosApp.model.dto.TagsPessoaDTO;
import com.entra21.voluntariosApp.view.service.TagsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tags")
public class TagsRestController {
    @Autowired
    private TagsService tagsService;

    @PostMapping("/criar")
    public void criarTag(@RequestBody String nome){
        tagsService.addTag(nome);
    }

    @DeleteMapping("/deletar")
    public void deleteTag(@RequestParam(name = "idTag") Long idTag){tagsService.deleteTag(idTag);}

    @PutMapping("/atualizar/{idTag}")
    public void atualizarTag(@PathVariable(name = "idTag") Long idTag, @RequestParam(name = "novoNome") String novoNome){tagsService.atualizarTag(idTag,novoNome);}

    @PostMapping("/tagsEvento")
    public void setTagsEvento(@RequestBody TagsEventoDTO tagsEventoDTO){tagsService.setTagsEvento(tagsEventoDTO);}

    @PostMapping("/tagsPessoa")
    public void setTagsPessoa(@RequestBody TagsPessoaDTO tagsPessoaDTO){tagsService.setTagsPessoa(tagsPessoaDTO);}
}
