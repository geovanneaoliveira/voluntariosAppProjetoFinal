package com.entra21.voluntariosApp.model.dto.server;

import lombok.Data;

/**DTO dedicado para enviar informações de uma Organização para o servidor.<br>
 * Atributos:
 * <li>String nomeOrg</li>
 * <li>String descricao</li>
 * <li>Long idSupervisor</li>
 * <li>String cnpj</li>
 */
@Data
public class OrganizacaoDTO {
    private String nome;
    private String descricao;
    private Long idSupervisor;
    private String cnpj;
}
