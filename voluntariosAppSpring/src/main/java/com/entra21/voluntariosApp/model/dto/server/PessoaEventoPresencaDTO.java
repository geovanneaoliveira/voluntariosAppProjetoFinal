package com.entra21.voluntariosApp.model.dto.server;

import lombok.Data;

/**DTO dedicado para enviar a lista de presentes de um evento.<br>
 * Atributos:
 * <li>Long idPessoa</li>
 * <li>String nome</li>
 * <li>String sobrenome</li>
 */
@Data
public class PessoaEventoPresencaDTO {
    private Long idPessoa;
    private String nome;
    private String sobrenome;
}
