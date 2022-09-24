package com.entra21.voluntariosApp.model.dto.user;

import lombok.Data;

import java.time.LocalDateTime;

/**DTO dedicado para enviar informações de uma Contribuição para o usuário.<br>
 * Atributos:
 * <li>LocalDateTime data</li>
 * <li>Double valor</li>
 * <li>String nomeUsuario</li>
 * <li>String sobrenome</li>
 * <li>String nomeOrg</li>
 */
@Data
public class ContribuicaoDTO {
    private LocalDateTime data;
    private Double valor;
    private String nomeUsuario;
    private String sobrenome;
    private String nomeOrg;
}
