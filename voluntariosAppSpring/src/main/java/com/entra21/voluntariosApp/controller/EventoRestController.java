package com.entra21.voluntariosApp.controller;

import com.entra21.voluntariosApp.model.dto.server.EventoDTOs;
import com.entra21.voluntariosApp.model.dto.server.PatrocinadorDTOs;
import com.entra21.voluntariosApp.model.dto.server.PessoaEventoPresencaDTO;
import com.entra21.voluntariosApp.model.dto.server.PessoasEventoDTO;
import com.entra21.voluntariosApp.model.dto.user.EventoDTO;
import com.entra21.voluntariosApp.model.dto.user.EventoInfosDTO;
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

    /**
     * Chama um método que Busca eventos referentes ao nome informado por parâmetro
     *
     * @param nome
     * @return {@code List<EventoDTO>}
     */
    @GetMapping("/buscar")
    public List<EventoDTO> buscarEvento(@RequestParam(name = "nome") String nome) {
        return eventoService.buscarEvento(nome);
    }

    @GetMapping
    public List<EventoInfosDTO> todosEventos() {
        return eventoService.todosEventos();
    }

    /**
     * Chama um método que Adiciona um novo evento ao banco de daodos
     *
     * @param eventoDTOs
     */
    @PostMapping("/criar")
    public void adicionarEvento(@RequestBody EventoDTOs eventoDTOs) {
        eventoService.adicionarEvento(eventoDTOs);
    }

    /**
     * Chama um método que Adiciona uma pessoa à lista de presentes de um evento
     *
     * @param idEvento
     * @param idPessoa
     */
    @PostMapping("/presenca")
    public void adicionarPessoaEvento(@RequestParam(name = "idPessoa") Long idPessoa,
                                      @RequestParam(name = "idEvento") Long idEvento) {
        eventoService.adicionarPessoaEvento(idPessoa,idEvento);
    }

    /**
     * Chama um método que Retorna a lista de presentes em um evento
     *
     * @param idEvento
     * @return {@code List<PessoaEventoPresencaDTO>}
     */
    @GetMapping("/presentes")
    public List<PessoaEventoPresencaDTO> buscarPresentes(@RequestParam(name = "idEvento") Long idEvento) {
        return eventoService.buscarPresentes(idEvento);
    }

    /**
     * Chama um método que Retorna uma lista de eventos que tiverem uma tag cujo o Id é igual ao passado por parâmetro
     *
     * @param idTag
     * @return {@code List<EventoDTO>}
     */
    @GetMapping("/idTag")
    public List<EventoDTO> buscarEventoPorIdTag(@RequestParam(name = "idTag") Long idTag) {
        return eventoService.buscarEventoPorTags(idTag);
    }

     /**
     * Chama um método que Atualiza os dados de um evento
     *
     * @param idEvento
     * @param dto
     */
    @PutMapping("/atualizar")
    public void atualizarEvento(@RequestParam(name = "idEvento")Long idEvento,@RequestBody EventoDTOs dto){
        eventoService.atualizarEvento(idEvento,dto);
    }

    /**
     * Chama um método que adiciona um patrocinador à um evento
     *
     * @param idEvento
     * @param idPatrocinador
     */
    @PostMapping("/addPatrocinadorIds")
    public void addPatrocinador(@RequestParam(name = "idEvento") Long idEvento,
                                @RequestParam(name = "idPatrocinador") Long idPatrocinador) {
        eventoService.addPatrocinadorEventoIds(idEvento,idPatrocinador);
    }

    /**
     * Chama um método que deleta um patrocinador de um evento
     *
     * @param idEvento
     * @param idPatrocinador
     */
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