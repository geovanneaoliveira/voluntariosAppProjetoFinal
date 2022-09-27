package com.entra21.voluntariosApp.controller;

import com.entra21.voluntariosApp.model.dto.server.EventoDTOs;
import com.entra21.voluntariosApp.model.dto.server.PatrocinadorDTO;
import com.entra21.voluntariosApp.model.dto.user.EventoDTO;
import com.entra21.voluntariosApp.view.service.PatrocinadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patrocinador")
public class PatrocinadorRestController {

    @Autowired
    private PatrocinadorService patrocinadorService;

    /**
     * Chama um método que retorna todos os patrocinadores do banco de dados
     *
     * @return {@code List<PatrocinadorDTO>}
     */
    @GetMapping("/todos")
    private List<PatrocinadorDTO> getAllPatrocinadores() {
        return patrocinadorService.getAllPatrocinadores();
    }

    /**
     * Chama um método que adiciona um novo patrocinador ao banco de dados
     *
     * @param patrocinadorDTO
     */
    @PostMapping("/criar")
    private void addPatrocinador(@RequestBody PatrocinadorDTO patrocinadorDTO) {
        patrocinadorService.addPatrocinador(patrocinadorDTO);
    }

    /**
     * Chama um método que retorna todos os eventos que o patrocinador patrocina
     * @param idPatrocinador
     * @return {@code List<EventoDTOs>}
     */

    @GetMapping("/eventosPatrocinados")
    public List<EventoDTOs> findAllByEvento_Id(@RequestParam(name = "idPatrocinador") Long idPatrocinador) {
        return patrocinadorService.findAllByEventos_Id(idPatrocinador);
    }

    /**
     * Chama um método que deleta um patrocinador de acordo com o Id informado no parâmetro
     * @param idPatrocinador
     */
    @DeleteMapping("/excluir")
    private void deletePatrocinador(@RequestParam(name = "idPatrocinador") Long idPatrocinador) {
        patrocinadorService.deletePatrocinador(idPatrocinador);
    }

}
