package com.entra21.voluntariosApp.model.dto.server;

import lombok.Data;

import java.time.LocalDateTime;

/**DTO dedicado para enviar informações de uma Contribuição para o servidor.<br>
 * Atributos:
 * <li>LocalDateTime data</li>
 * <li>Double valor</li>
 * <li>Long idPessoa</li>
 * <li>Long idOrganizacao</li>
 */
@Data
public class ContribuicaoDTO {
    private LocalDateTime data;
    private Double valor;
    private Long idPessoa;
    private Long idOrganizacao;
}