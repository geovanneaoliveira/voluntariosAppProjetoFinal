package com.entra21.voluntariosApp.model.dto.user;

import com.entra21.voluntariosApp.model.entity.TagsEntity;
import lombok.Data;

import java.util.List;

/**DTO dedicado para login na aplicação.
 * */
@Data
public class LoginDTO {
    private Long id;
    private String login;
    private String senha;
    private String nome;
    private String fotoPerfil;
}
