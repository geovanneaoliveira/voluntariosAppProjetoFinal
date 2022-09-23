package com.entra21.voluntariosApp.model.dto.server;

import lombok.Data;

import java.time.LocalDateTime;

/**DTO dedicado para enviar informações de um Evento para o servidor.<br>
 * Atributos:
 * <li>String nome</li>
 * <li>LocalDateTime data</li>
 * <li>Long idOrganizacao</li>
 */
@Data
public class EventoDTOs {
    private String nome;
    private LocalDateTime data;
    private Long idOrganizacao;
}
