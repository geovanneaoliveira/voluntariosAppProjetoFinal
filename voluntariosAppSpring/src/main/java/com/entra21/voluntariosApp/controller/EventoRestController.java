package com.entra21.voluntariosApp.controller;

import com.entra21.voluntariosApp.model.dto.*;
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
    public List<EventoBuscaDTO> buscarEvento(@RequestParam(name = "nome") String nome)
    {
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
    public List<PessoaEventoPresencaDTO> buscarPresenca(@RequestParam(name = "idEvento") Long idEvento){
        return eventoService.buscarPresenca(idEvento);
    }
<<<<<<< Updated upstream
=======

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
>>>>>>> Stashed changes
}