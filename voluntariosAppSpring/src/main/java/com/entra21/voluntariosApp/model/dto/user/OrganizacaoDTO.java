package com.entra21.voluntariosApp.model.dto.user;

import lombok.Data;

import java.util.List;

/**DTO dedicado para enviar informações de uma Organização para o usuário.<br>
 * Atributos:
 * <li>String nomeOrg</li>
 * <li>String descricao</li>
 * <li>String nomeSupervisor</li>
 * <li>String sobrenomeSupervisor</li>
 * <li>String imagePath</li>
 */
@Data
public class OrganizacaoDTO {
    private String nomeOrg;
    private String descricao;
    private String nomeSupervisor;
    private String sobrenomeSupervisor;
    private String orgFoto;
    private Long id;
    private String cnpj;
    private Double valorTotal;
    private List<EventoInfosDTO> eventos;
}
