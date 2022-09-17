package com.entra21.voluntariosApp.controller;

import com.entra21.voluntariosApp.model.dto.EventoDTO;
import com.entra21.voluntariosApp.model.dto.PessoasEventoDTO;
import com.entra21.voluntariosApp.model.entity.EventoEntity;
import com.entra21.voluntariosApp.view.service.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/evento")
public class EventoRestController {

    @Autowired
    private EventoService eventoService;


    //todo - buscar um evento
    //todo - buscar eventos com uma tag()
    //todo - deletar evento
    //todo - atualizar infos do evento

    @GetMapping("/buscar")
    public List<EventoDTO> buscarEvento(@RequestBody String nome){
        return eventoService.buscarEvento(nome);
    }

    @PostMapping("/criar")
    public void adicionarEvento(@RequestBody EventoDTO eventoDTO) {
        eventoService.adicionarEvento(eventoDTO);
    }

    @PostMapping("/presenca")
    public void adicionarPessoaEvento(@RequestBody PessoasEventoDTO pessoasEventoDTO){
        eventoService.adicionarPessoaEvento(pessoasEventoDTO);

    }

    @GetMapping("/presentes")
    public List<PessoasEventoDTO> buscarPresenca(@RequestParam(name = "idEvento") Long idEvento){
        return eventoService.buscarPresenca(idEvento);
    }
}