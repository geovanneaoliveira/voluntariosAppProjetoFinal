package com.entra21.voluntariosApp.controller;

import com.entra21.voluntariosApp.model.dto.server.EventoDTOs;
import com.entra21.voluntariosApp.model.dto.server.PatrocinadorDTOs;
import com.entra21.voluntariosApp.view.service.PatrocinadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patrocinador")
public class PatrocinadorRestController {

    @Autowired
    private PatrocinadorService patrocinadorService;

    @GetMapping("/todos")
    private List<PatrocinadorDTOs> retornarPatrocinadores() {
        return patrocinadorService.retornarPatrocinadores();
    }

    @PostMapping("/criar")
    private void addPatrocinador(@RequestBody PatrocinadorDTOs patrocinadorDTOs) {
        patrocinadorService.addPatrocinador(patrocinadorDTOs);
    }

    @GetMapping("/eventosPatrocinados")
    public List<EventoDTOs> buscarEventoPatrocinadoPorId(@RequestParam(name = "idPatrocinador")Long idPatrocinador) {
        return patrocinadorService.buscarEventosPatrocinadosPorId(idPatrocinador);
    }

    @DeleteMapping("/excluir")
    private void excluirPatrocinador(@RequestParam(name = "idPatrocinador") Long idPatrocinador){
        patrocinadorService.excluirPatrocinador(idPatrocinador);
    }
}
