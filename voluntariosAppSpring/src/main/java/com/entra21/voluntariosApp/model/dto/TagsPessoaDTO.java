package com.entra21.voluntariosApp.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class TagsPessoaDTO {
    private Long idPessoa;
    private List<Long> idTagsPessoa;
}
