package com.entra21.voluntariosApp.controller;

import com.entra21.voluntariosApp.model.dto.server.EventoDTOs;
import com.entra21.voluntariosApp.model.dto.server.PatrocinadorDTO;
import com.entra21.voluntariosApp.model.dto.server.PessoaEventoPresencaDTO;
import com.entra21.voluntariosApp.model.dto.server.PessoasEventoDTO;
import com.entra21.voluntariosApp.model.dto.user.EventoDTO;
import com.entra21.voluntariosApp.view.service.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/evento")
public class EventoRestController {

    @Autowired
    private EventoService eventoService;

    @GetMapping("/buscar")
    public List<com.entra21.voluntariosApp.model.dto.user.EventoDTO> buscarEvento(@RequestParam(name = "nome") String nome) {
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
    public List<EventoDTO> findEventoByIdTag(@RequestParam(name = "idTag") Long idTag){
        return eventoService.findEventoByTags(idTag);
    }


    @PutMapping("/atualizar")
    public void atualizarEvento(@RequestParam(name = "id")Long id,@RequestBody EventoDTOs dto){
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

    //todo
//    //busca e retorna todos os patrocinadores de um evento
//    @GetMapping("/patrocinadoresDoEvento")
//    public List<PatrocinadorDTO> findAllBypatrocinadores_IdEvento(@RequestParam(name = "idEvento")Long idEvento){
//      return eventoService.findAllByPatrocinadores_Id(idEvento);
//    }
}