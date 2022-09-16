package com.entra21.voluntariosApp.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class TagsEventoDTO {
    private Long idEvento;
    private List<Long> idTagsEvento;
}
