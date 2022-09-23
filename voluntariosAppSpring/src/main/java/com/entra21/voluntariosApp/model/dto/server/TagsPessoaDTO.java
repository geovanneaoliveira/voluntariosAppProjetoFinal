package com.entra21.voluntariosApp.model.dto.server;

import lombok.Data;

import java.util.List;

/**DTO dedicado para adicionar Tags a um Usuário.<br>
 * Atributos:
 * <li>Long idPessoa</li>
 * <i>List{@code <Long>} idTagsPessoa</i>
 * */
@Data
public class TagsPessoaDTO {
    private Long idPessoa;
    private List<Long> idTagsPessoa;
}
