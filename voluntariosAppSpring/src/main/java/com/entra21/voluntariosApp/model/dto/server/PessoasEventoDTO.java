package com.entra21.voluntariosApp.model.dto.server;

import lombok.Data;

/**DTO dedicado para adicionar um Usuário à lista de presentes do Evento.<br>
 * Atributos:
 * <li>Long idPessoa</li>
 * <li>Long idEvento</li>
 */
@Data
public class PessoasEventoDTO {
    private Long idPessoa;
    private Long idEvento;
}
