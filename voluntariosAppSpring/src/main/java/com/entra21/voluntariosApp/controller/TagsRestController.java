package com.entra21.voluntariosApp.controller;

import com.entra21.voluntariosApp.model.dto.TagsEventoDTO;
import com.entra21.voluntariosApp.view.service.TagsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tags")
public class TagsRestController {
    @Autowired
    private TagsService tagsService;

    @PostMapping("/criar")
    public void criarTag(@RequestBody String nome){
        tagsService.addTag(nome);
    }

    @PostMapping("/tagsEvento")
    public void setTagsEvento(@RequestBody TagsEventoDTO tagsEventoDTO){tagsService.addTagsEvento(tagsEventoDTO);}
}
