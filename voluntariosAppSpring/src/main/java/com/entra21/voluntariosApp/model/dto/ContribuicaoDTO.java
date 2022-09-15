package com.entra21.voluntariosApp.model.dto;

import com.entra21.voluntariosApp.model.entity.OrganizacaoEntity;
import com.entra21.voluntariosApp.model.entity.PessoaEntity;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ContribuicaoDTO {

    private LocalDateTime data;
    private double valor;
    private Long idPessoa;
    private Long idOrganizacao;
}