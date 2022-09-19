package com.entra21.voluntariosApp.model.dto;

import com.entra21.voluntariosApp.model.entity.PessoaEntity;
import lombok.Data;

import javax.persistence.Column;

@Data
public class OrganizacaoDTO {
    private String nome;
    private String descricao;
    private Long idSupervisor;
}
