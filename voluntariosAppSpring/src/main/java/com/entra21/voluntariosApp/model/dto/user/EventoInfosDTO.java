package com.entra21.voluntariosApp.model.dto.user;

import com.entra21.voluntariosApp.model.entity.PatrocinadorEntity;
import com.entra21.voluntariosApp.model.entity.PessoaEntity;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class EventoInfosDTO {
    private String nome;
    private String data;
    private String nomeOrganizacao;
    private List<PessoaEntity> pessoasEvento;
    private List<String> tagsEvento;
    private List<PatrocinadorEntity> patrocinadores;
    private Long id;
}
