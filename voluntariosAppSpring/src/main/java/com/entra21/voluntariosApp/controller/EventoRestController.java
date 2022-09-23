package com.entra21.voluntariosApp.controller;

import com.entra21.voluntariosApp.model.dto.*;
import com.entra21.voluntariosApp.view.service.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/evento")
public class EventoRestController {

    @Autowired
    private EventoService eventoService;

    //todo - deletar evento
    //todo - atualizar infos do evento

    @GetMapping("/buscar")
    public List<com.entra21.voluntariosApp.model.dto.user.EventoDTO> buscarEvento(@RequestParam(name = "nome") String nome)
    {
        return eventoService.buscarEvento(nome);
    }

    @PostMapping("/criar")
    public void adicionarEvento(@RequestBody EventoDTOs eventoDTOs) {
        eventoService.adicionarEvento(eventoDTOs);
    }

    @PostMapping("/presenca")
    public void adicionarPessoaEvento(@RequestBody PessoasEventoDTO pessoasEventoDTO){
        eventoService.adicionarPessoaEvento(pessoasEventoDTO);
    }

    @GetMapping("/presentes")
    public List<PessoaEventoPresencaDTO> buscarPresentes(@RequestParam(name = "idEvento") Long idEvento){
        return eventoService.buscarPresentes(idEvento);
    }

    @GetMapping("/idTag")
    public List<com.entra21.voluntariosApp.model.dto.user.EventoDTO> findEventoByIdTag(@RequestParam(name = "idTag") Long idTag){
        return eventoService.findEventoByTags(idTag);
    }

    @GetMapping("/idTag")
    public List<EventoBuscaDTO> findEventoByIdTag(@RequestParam(name = "idTag") Long idTag){
        return eventoService.findEventoByTags(idTag);
    }

    @PutMapping("/atualizar")
    public void atualizarEvento(@RequestParam(name = "id")Long id,@RequestBody EventoDTO dto){
        eventoService.atualizarEvento(id,dto);
    }
    @PostMapping("/addPatrocinador")
    public void addPatrocinador(@RequestParam(name = "idEvento") Long idEvento, @RequestBody PatrocinadorDTO dto) {
        eventoService.addPatrocinadorEvento(idEvento,dto);
    }

    @DeleteMapping("/excluirPatrocinador")
    private void deletarPatrocinadorEvento(@RequestParam(name = "idEvento") Long idEvento, @RequestParam(name = "idPatrocinador") Long idPatrocinador){
        eventoService.deletarPatrocinadorEvento(idEvento, idPatrocinador);
    }
}