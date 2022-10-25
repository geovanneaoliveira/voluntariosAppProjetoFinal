package com.entra21.voluntariosApp.model.dto.user;

import lombok.Data;

@Data
public class OrganizacaoInfosDTO {
    private Long id;
    private String nomeOrg;
    private String descricao;
    private String nomeSupervisor;
    private String sobrenomeSupervisor;
    private String orgFoto;
}
