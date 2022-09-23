package com.entra21.voluntariosApp.view.service;

import com.entra21.voluntariosApp.model.dto.*;
import com.entra21.voluntariosApp.model.entity.EventoEntity;
import com.entra21.voluntariosApp.model.entity.PatrocinadorEntity;
import com.entra21.voluntariosApp.model.entity.PatrocinadoresEventoEntity;
import com.entra21.voluntariosApp.model.entity.PessoasEventoEntity;
import com.entra21.voluntariosApp.view.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import java.util.stream.Collectors;

@Service
public class EventoService {

    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private PessoasEventoRepository pessoasEventoRepository;

    @Autowired
    private OrganizacaoRepository organizacaoRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private PatrocinadorEventoRepository patrocinadorEventoRepository;

    @Autowired
    private  PatrocinadorRepository patrocinadorRepository;

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

    public List<EventoBuscaDTO> buscarEvento(String nome) {
        List<EventoEntity> eventos = eventoRepository.findAll().stream()
                .filter(ev -> ev.getNome().toLowerCase().contains(nome.toLowerCase())).collect(Collectors.toList());
        return eventos.stream().map(ev -> {
            EventoBuscaDTO dto = new EventoBuscaDTO();
            dto.setNome(ev.getNome());
            dto.setData(ev.getData());
            dto.setNomeOrganizacao(ev.getOrganizacao().getNome());
            return dto;
        }).collect(Collectors.toList());

    }

    public List<PessoaEventoPresencaDTO> buscarPresenca(Long idEvento) {
        List<PessoasEventoEntity> pessoasEvento = pessoasEventoRepository.findAllByidEvento_Id(idEvento).stream().filter(PessoasEventoEntity::getPresenca).collect(Collectors.toList());
        return pessoasEvento.stream().map(pv -> {
            PessoaEventoPresencaDTO dto = new PessoaEventoPresencaDTO();
            dto.setIdPessoa(pv.getPessoa().getId());
            dto.setNome(pv.getPessoa().getNome());
            dto.setSobrenome(pv.getPessoa().getSobrenome());
            return dto;
        }).collect(Collectors.toList());
    }

    public void adicionarPessoaEvento(PessoasEventoDTO dto) {
        eventoRepository.findById(dto.getIdEvento()).ifPresentOrElse(ev -> {
            pessoaRepository.findById(dto.getIdPessoa()).ifPresentOrElse(pe -> {
                PessoasEventoEntity pessoasEventoEntity = new PessoasEventoEntity();
                pessoasEventoEntity.setPessoa(pe);
                pessoasEventoEntity.setIdEvento(ev);
                pessoasEventoEntity.setPresenca(true);
                pessoasEventoRepository.save(pessoasEventoEntity);
            }, () -> {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Pessoa não encontrada!");
            });
        }, () -> {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Evento não encontrado!");
        });
    }
<<<<<<< Updated upstream
}
=======

    public List<EventoBuscaDTO> findEventoByTags(Long idTag) {
        return eventoRepository.findAllBytags_Id(idTag).stream().map(ev -> {
            EventoBuscaDTO eBD = new EventoBuscaDTO();
            eBD.setNome(ev.getNome());
            eBD.setData(ev.getData());
            eBD.setNomeOrganizacao(ev.getOrganizacao().getNome());
            return eBD;
        }).collect(Collectors.toList());
    }

    public void atualizarEvento(Long id, EventoDTO dto) {
        eventoRepository.findById(id).ifPresentOrElse(eE -> {
            organizacaoRepository.findById(dto.getIdOrganizacao()).ifPresentOrElse(org -> {
                eE.setNome(dto.getNome());
                eE.setData(dto.getData());
                eE.setOrganizacao(org);
                eventoRepository.save(eE);
            }, () -> {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Organização não encontrado!");});
        }, () -> {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Evento não encontrado!");});
    }

    public void addPatrocinadorEvento(Long idEvento, PatrocinadorDTO dto) {
        eventoRepository.findById(idEvento).ifPresentOrElse(evento -> {
            patrocinadorRepository.findById(dto.getIdRepresentante()).ifPresentOrElse(pa -> {
                pessoaRepository.findById(dto.getIdRepresentante()).ifPresentOrElse(pe -> {
                    PatrocinadoresEventoEntity patrocinadoresEvento = new PatrocinadoresEventoEntity();
                    patrocinadoresEvento.setPatrocinador(pa);
                    patrocinadoresEvento.setId(dto.getIdRepresentante());
                    patrocinadoresEvento.setEvento(evento);
                    patrocinadorEventoRepository.save(patrocinadoresEvento);
                },() -> {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Pessoa não encontrada!");});
            }, () -> {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Organização não encontrado!");});
        }, () -> {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Evento não encontrado!");});
    }

    public void deletarPatrocinadorEvento(Long idEvento, Long idPatrocinador){
        eventoRepository.findById(idEvento).ifPresentOrElse(evento -> {
            patrocinadorEventoRepository.deleteById(idPatrocinador);
    }, () -> {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Evento não encontrado!");});

    }
}














<<<<<<< Updated upstream
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
