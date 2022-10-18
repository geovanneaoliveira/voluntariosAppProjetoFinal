package com.entra21.voluntariosApp.model.dto.user;

import com.entra21.voluntariosApp.model.entity.PatrocinadorEntity;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class EventoInfosDTO {
    private String nome;
    private LocalDateTime data;
    private String nomeOrganizacao;
    private List<String> pessoasEvento;
    private List<String> tagsEvento;
    private List<String> patrocinadores;
}
