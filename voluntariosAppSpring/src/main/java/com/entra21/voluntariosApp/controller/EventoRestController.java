package com.entra21.voluntariosApp.controller;

<<<<<<< Updated upstream
import com.entra21.voluntariosApp.model.dto.EventoDTO;
import com.entra21.voluntariosApp.model.dto.PessoasEventoDTO;
import com.entra21.voluntariosApp.model.entity.EventoEntity;
=======
import com.entra21.voluntariosApp.model.dto.*;
>>>>>>> Stashed changes
import com.entra21.voluntariosApp.view.service.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/evento")
public class EventoRestController {

    @Autowired
    private EventoService eventoService;

<<<<<<< Updated upstream

    //todo //- buscar um evento
    //todo - buscar eventos com uma tag()
    //todo - deletar evento
    //todo - atualizar infos do evento

=======
>>>>>>> Stashed changes
    @GetMapping("/buscar")
    public List<EventoDTO> buscarEvento(@RequestBody String nome){
        return eventoService.buscarEvento(nome);
    }

    @PostMapping("/criar")
    public void adicionarEvento(@RequestBody EventoDTO eventoDTO) {
        eventoService.adicionarEvento(eventoDTO);
    }

<<<<<<< Updated upstream
    @PostMapping("/presenca")
    public void adicionarPessoaEvento(@RequestBody PessoasEventoDTO pessoasEventoDTO){
        eventoService.adicionarPessoaEvento(pessoasEventoDTO);

    }
=======
>>>>>>> Stashed changes

    @GetMapping("/presentes")
    public List<PessoasEventoDTO> buscarPresenca(@RequestBody Long idEvento){
        return eventoService.buscarPresenca(idEvento);
    }
    @GetMapping
    public List<EventoDTO> getAll(@RequestParam(name = "idTag", required = false) Long id) {
        return eventoService.getAll(id);
    }
<<<<<<< Updated upstream
=======
    @DeleteMapping("deletar")
    public void deletarEvento(@RequestParam(name = "id")Long id){
        eventoService.deletarEvento(id);

    }
    //busca e retorna todos os patrocinadores de um evento
    @GetMapping("/patrocinadoresDoEvento")
    public List<PatrocinadorDTO> findAllBypatrocinadores_IdEvento(@RequestParam(name = "idEvento")Long idEvento){
      return  eventoService.findAllByPatrocinadores_Id(idEvento);
    }

>>>>>>> Stashed changes

}