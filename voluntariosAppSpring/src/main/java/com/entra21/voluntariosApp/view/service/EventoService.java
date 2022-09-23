package com.entra21.voluntariosApp.view.service;

import com.entra21.voluntariosApp.model.dto.EventoBuscaDTO;
import com.entra21.voluntariosApp.model.dto.EventoDTO;
import com.entra21.voluntariosApp.model.dto.PessoaEventoPresencaDTO;
import com.entra21.voluntariosApp.model.dto.PessoasEventoDTO;
import com.entra21.voluntariosApp.model.entity.EventoEntity;
import com.entra21.voluntariosApp.model.entity.PessoasEventoEntity;
import com.entra21.voluntariosApp.view.repository.EventoRepository;
import com.entra21.voluntariosApp.view.repository.OrganizacaoRepository;
import com.entra21.voluntariosApp.view.repository.PessoaRepository;
import com.entra21.voluntariosApp.view.repository.PessoasEventoRepository;
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

    /**
     * Adiciona um Evento ao repositório.<br>
     * Atributos de EventoDTO:
     * <li>String nome</li>
     * <li>LocalDateTime data</li>
     * <li>Long idOrganizacao</li>
     * @param eventoDTO
     */
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

    /**
     * Retorna os Eventos com nomes similares ao passado por parâmetro.
     * @param nome
     * @return List {@code <EventoBuscaDTO>}
     */
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

    /**
     * Retorna a lista de pessoas com Presença confirmada em um evento de acordo com o Id dele.
     * @param idEvento
     * @return List
     */
    public List<PessoaEventoPresencaDTO> buscarPresentes(Long idEvento) {
        List<PessoasEventoEntity> pessoasEvento = pessoasEventoRepository.findAllByidEvento_Id(idEvento).stream().filter(PessoasEventoEntity::getPresenca).collect(Collectors.toList());
        return pessoasEvento.stream().map(pv -> {
            PessoaEventoPresencaDTO dto = new PessoaEventoPresencaDTO();
            dto.setIdPessoa(pv.getPessoa().getId());
            dto.setNome(pv.getPessoa().getNome());
            dto.setSobrenome(pv.getPessoa().getSobrenome());
            return dto;
        }).collect(Collectors.toList());
    }

    /**
     * Adiciona uma Pessoa à lista de presentes de um Evento de acordo com seus respectivos Ids.<br>
     * Atributos de PessoasEventoDTO:
     * <li>Long idPessoa</li>
     * <li>Long idEvento</li>
     * @param dto
     */
    public void adicionarPessoaEvento(PessoasEventoDTO dto) {
        eventoRepository.findById(dto.getIdEvento()).ifPresentOrElse(ev -> {
            pessoaRepository.findById(dto.getIdPessoa()).ifPresentOrElse(pe -> {
                PessoasEventoEntity pessoasEventoEntity = new PessoasEventoEntity();
                pessoasEventoEntity.setPessoa(pe);
                pessoasEventoEntity.setIdEvento(ev);
                pessoasEventoEntity.setPresenca(true);
                pessoasEventoRepository.save(pessoasEventoEntity);
            }, () -> {throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Pessoa não encontrada!");});
        }, () -> {throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Evento não encontrado!");});
    }
<<<<<<< Updated upstream
=======

    /**
     * Retorna todos os Eventos que contiverem a Tag cujo Id for igual ao passado por parâmetro.
     * @param idTag
     * @return List {@code <EventoBuscaDTO>}
     */
    public List<EventoBuscaDTO> findEventoByTags(Long idTag) {
        return eventoRepository.findAllBytags_Id(idTag).stream().map(ev -> {
            EventoBuscaDTO eBD = new EventoBuscaDTO();
            eBD.setNome(ev.getNome());
            eBD.setData(ev.getData());
            eBD.setNomeOrganizacao(ev.getOrganizacao().getNome());
            return eBD;
        }).collect(Collectors.toList());
    }

    /**
     * Atualiza as informações do Evento cujo Id for igual ao passado por parâmetro para as informações
     * contidas em um EventoDTO:
     * <li>String nome</li>
     * <li>LocalDateTime data</li>
     * <li>Long idOrganizacao</li>
     * @param id
     * @param dto
     */
    public void atualizarEvento(Long id, EventoDTO dto) {
        eventoRepository.findById(id).ifPresentOrElse(eE -> {
            eE.setNome(dto.getNome());
            eE.setData(dto.getData());
            eE.setOrganizacao(eE.getOrganizacao());
            eventoRepository.save(eE);
        }, () -> {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Evento não encontrado!");
        });
    }
>>>>>>> Stashed changes
}