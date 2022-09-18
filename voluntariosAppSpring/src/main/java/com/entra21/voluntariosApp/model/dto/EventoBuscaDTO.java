package com.entra21.voluntariosApp.model.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EventoBuscaDTO {
    private String nome;
    private LocalDateTime data;
    private String nomeOrganizacao;
}
