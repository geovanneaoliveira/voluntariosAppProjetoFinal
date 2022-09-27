package com.entra21.voluntariosApp.controller;

import com.entra21.voluntariosApp.model.dto.server.EventoDTOs;
import com.entra21.voluntariosApp.model.dto.server.PatrocinadorDTOs;
import com.entra21.voluntariosApp.model.dto.server.PessoaEventoPresencaDTO;
import com.entra21.voluntariosApp.model.dto.server.PessoasEventoDTO;
import com.entra21.voluntariosApp.model.dto.user.EventoDTO;
import com.entra21.voluntariosApp.model.dto.user.PatrocinadorDTO;
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
    public void adicionarPessoaEvento(@RequestParam(name = "idPessoa") Long idPessoa,
                                      @RequestParam(name = "idEvento") Long idEvento) {
        eventoService.adicionarPessoaEvento(idPessoa,idEvento);
    }

    @GetMapping("/presentes")
    public List<PessoaEventoPresencaDTO> buscarPresentes(@RequestParam(name = "idEvento") Long idEvento) {
        return eventoService.buscarPresentes(idEvento);
    }

    @GetMapping("/idTag")
    public List<EventoDTO> buscarEventoPorIdTag(@RequestParam(name = "idTag") Long idTag) {
        return eventoService.buscarEventoPorTags(idTag);
    }

    @PutMapping("/atualizar")
    public void atualizarEvento(@RequestParam(name = "id")Long id,@RequestBody EventoDTOs dto) {
        eventoService.atualizarEvento(id,dto);
    }

    @PostMapping("/addPatrocinadorIds")
    public void addPatrocinador(@RequestParam(name = "idEvento") Long idEvento,
                                @RequestParam(name = "idPatrocinador") Long idPatrocinador) {
        eventoService.addPatrocinadorEventoIds(idEvento,idPatrocinador);
    }

    @DeleteMapping("/excluirPatrocinador")
    private void deletarPatrocinadorEvento(@RequestParam(name = "idEvento") Long idEvento,
                                           @RequestParam(name = "idPatrocinador") Long idPatrocinador) {
        eventoService.deletarPatrocinadorEvento(idEvento, idPatrocinador);
    }

    @GetMapping("/patrocinadoresdoEvento")
    public List<PatrocinadorDTO> buscarPatrocinadoresPorIdEvento(@RequestParam(name = "idEvento")Long idEvento){
      return eventoService.buscarPatrocinadoresPorIdEvento(idEvento);
    }
}