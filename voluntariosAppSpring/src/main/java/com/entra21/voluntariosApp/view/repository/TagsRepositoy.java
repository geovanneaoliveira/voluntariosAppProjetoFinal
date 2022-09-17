package com.entra21.voluntariosApp.view.repository;

import com.entra21.voluntariosApp.model.entity.TagsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagsRepositoy extends JpaRepository<TagsEntity, Long> {
}
