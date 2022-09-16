package com.entra21.voluntariosApp.view.service;

import com.entra21.voluntariosApp.model.entity.TagsEntity;
import com.entra21.voluntariosApp.view.repository.TagsRepositoy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class TagsService {
    @Autowired
    private TagsRepositoy tagsRepositoy;

    public void addTag(String nomeTag){
        TagsEntity tagsEntity = new TagsEntity();
        tagsEntity.setNome(nomeTag);
        tagsRepositoy.save(tagsEntity);
    }
}
