package com.entra21.voluntariosApp.model.dto.user;

import lombok.Data;

import java.time.LocalDateTime;

/**DTO dedicado para enviar informações de um Evento para o usuário.<br>
 * Atributos:
 * <li>String nome</li>
 * <li>LocalDateTime data</li>
 * <li>String nomeOrganizacao</li>
 */
@Data
public class EventoDTO {
    private String nome;
    private LocalDateTime data;
    private String nomeOrganizacao;
}
