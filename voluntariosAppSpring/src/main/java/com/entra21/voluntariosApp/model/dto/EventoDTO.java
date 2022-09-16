package com.entra21.voluntariosApp.model.dto;

import com.entra21.voluntariosApp.model.entity.OrganizacaoEntity;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EventoDTO {
    private String nome;
    private LocalDateTime data;
    private Long idOrganizacao;
}
