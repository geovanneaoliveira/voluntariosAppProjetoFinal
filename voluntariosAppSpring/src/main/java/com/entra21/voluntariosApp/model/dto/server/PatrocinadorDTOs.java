package com.entra21.voluntariosApp.model.dto.server;

import lombok.Data;

/**DTO dedicado para enviar informações de um Patrocinador para o servidor.<br>
 * Atributos:
 * <li>String nome</li>
 * <li>Long idRepresentante</li>
 */
@Data
public class PatrocinadorDTOs {
    private String nome;
    private Long idRepresentante;
    private String caminhoImagem;
}
