package com.entra21.voluntariosApp.model.dto.server;

import lombok.Data;

import java.util.List;

/**DTO dedicado para adicionar Tags a um Evento.<br>
 * Atributos:
 * <li>Long idEvento</li>
 * <i>List{@code <Long>} idTagsEvento</i>
 * */
@Data
public class TagsEventoDTO {
    private Long idEvento;
    private List<Long> idTagsEvento;
}
