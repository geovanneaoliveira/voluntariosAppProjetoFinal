package com.entra21.voluntariosApp.controller;

import com.entra21.voluntariosApp.model.dto.PatrocinadorDTO;
import com.entra21.voluntariosApp.view.service.PatrocinadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patrocinador")
public class PatrocinadorRestController {

    @Autowired
    private PatrocinadorService patrocinadorService;

    @GetMapping("/todos")
    private List<PatrocinadorDTO> getAll(){
        return patrocinadorService.getAll();
    }

    @PostMapping("/criar")
    private void addPatrocinador(@RequestBody PatrocinadorDTO patrocinadorDTO){
        patrocinadorService.addPatrocinador(patrocinadorDTO);
    }
}
