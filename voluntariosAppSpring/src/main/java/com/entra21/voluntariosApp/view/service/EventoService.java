package com.entra21.voluntariosApp.view.service;

<<<<<<< Updated upstream
<<<<<<< Updated upstream
import com.entra21.voluntariosApp.model.dto.EventoDTO;
import com.entra21.voluntariosApp.model.dto.PessoasEventoDTO;
=======
import com.entra21.voluntariosApp.model.dto.*;
>>>>>>> Stashed changes
=======
import com.entra21.voluntariosApp.model.dto.*;
>>>>>>> Stashed changes
import com.entra21.voluntariosApp.model.entity.EventoEntity;
import com.entra21.voluntariosApp.model.entity.OrganizacaoEntity;
import com.entra21.voluntariosApp.model.entity.PessoasEventoEntity;
import com.entra21.voluntariosApp.view.repository.EventoRepository;
import com.entra21.voluntariosApp.view.repository.OrganizacaoRepository;
import com.entra21.voluntariosApp.view.repository.PessoasEventoRepository;
import org.apache.catalina.LifecycleState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

<<<<<<< Updated upstream
import java.util.ArrayList;
import java.util.List;

import java.util.Optional;
=======
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


import java.util.Objects;
>>>>>>> Stashed changes
import java.util.stream.Collectors;

@Service
public class EventoService {

    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private PessoasEventoRepository pessoasEventoRepository;

    @Autowired
    private OrganizacaoRepository organizacaoRepository;

    public void adicionarEvento(EventoDTO eventoDTO) {
        organizacaoRepository.findById(eventoDTO.getIdOrganizacao()).ifPresentOrElse(org -> {
            EventoEntity eventoEntity = new EventoEntity();
            eventoEntity.setNome(eventoDTO.getNome());
            eventoEntity.setData(eventoDTO.getData());
            eventoEntity.setOrganizacao(org);
            eventoRepository.save(eventoEntity);
        }, () -> {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Organização não encontrada!");
        });
    }

    public List<EventoDTO> buscarEvento(String nome) {
        List<EventoEntity> eventos = eventoRepository.findAll().stream().filter(ev -> ev.getNome().contains(nome)).collect(Collectors.toList());
        return eventos.stream().map(ev -> {
            EventoDTO dto = new EventoDTO();
            dto.setNome(ev.getNome());
            dto.setData(ev.getData());
            dto.setIdOrganizacao(ev.getOrganizacao().getId());
            return dto;
        }).collect(Collectors.toList());
    }

    public List<PessoasEventoDTO> buscarPresenca(Long idEvento){
        List<PessoasEventoEntity> pessoasEvento = pessoasEventoRepository.findAll().stream()
                .filter(pv -> pv.getPresenca().booleanValue()).collect(Collectors.toList());
        return pessoasEvento.stream().map(pv -> {
            PessoasEventoDTO dto = new PessoasEventoDTO();
            dto.setIdEvento(pv.getIdEvento());
            dto.setIdPessoa(pv.getIdPessoa());
            return dto;
        }).collect(Collectors.toList());
    }


    public void adicionarPessoaEvento(PessoasEventoDTO pessoasEventoDTO){
        PessoasEventoEntity pessoasEventoEntity = new PessoasEventoEntity();
        pessoasEventoEntity.setIdPessoa(pessoasEventoDTO.getIdPessoa());
        pessoasEventoEntity.setIdEvento(pessoasEventoDTO.getIdEvento());
        pessoasEventoEntity.setPresenca(true);
        pessoasEventoRepository.save(pessoasEventoEntity);
    }

<<<<<<< Updated upstream
=======
//    private List<EventoDTO> buscarEventoComtag(String buscarTag){
//        List<EventoEntity> Eventoentity =eventoRepository.findAll().stream().filter(eE -> eE.getTags().equals(buscarTag)).collect(Collectors.toList());
//        return Eventoentity.stream().map(cE ->{
//            EventoDTO edto=new EventoDTO();
//            edto.setData(cE.getData());
//            edto.setNome(cE.getNome());
//            edto.setIdOrganizacao(cE.getId());
//            return edto;
//        }).collect(Collectors.toList());
//    }

    public List<EventoDTO> getAll(Long idTag) {
        List<EventoEntity> list= new ArrayList<>();
        if(idTag != null){
            list = eventoRepository.findAllByTags_Id(idTag);
        }else{
            list=eventoRepository.findAll();
        }
        return list.stream().map(i -> {
            EventoDTO dto = new EventoDTO();
            dto.setNome(i.getNome());
            dto.setData(i.getData());
            dto.setIdOrganizacao(i.getOrganizacao().getId());
            return dto;
        }).collect(Collectors.toList());
    }

<<<<<<< Updated upstream

>>>>>>> Stashed changes
}
=======
    public void deletarEvento(Long id){
        eventoRepository.deleteById(id);
    }


    //busca e retorna todos os patrocinadores de um evento
    public List<PatrocinadorDTO> findAllByPatrocinadores_Id(Long idEvento) {
        EventoEntity e = eventoRepository.findById(idEvento).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Evento não encontrado!"));
        return e.getPatrocinadores().stream().map(pat -> {
            PatrocinadorDTO dto = new PatrocinadorDTO();
            dto.setNome(pat.getNome());
            return dto;
        }).collect(Collectors.toList());
    }



}


>>>>>>> Stashed changes
