package com.entra21.voluntariosApp.view.service;

import com.entra21.voluntariosApp.model.dto.server.*;
import com.entra21.voluntariosApp.model.dto.user.EventoDTO;
import com.entra21.voluntariosApp.model.dto.user.EventoInfosDTO;
import com.entra21.voluntariosApp.model.dto.user.PatrocinadorDTO;
import com.entra21.voluntariosApp.model.entity.*;
import com.entra21.voluntariosApp.view.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.xml.crypto.Data;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class EventoService {

    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private PessoasEventoRepository pessoasEventoRepository;

    @Autowired
    private TagsRepository tagsRepository;

    @Autowired
    private OrganizacaoRepository organizacaoRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private PatrocinadorEventoRepository patrocinadorEventoRepository;

    @Autowired
    private PatrocinadorRepository patrocinadorRepository;

    @Autowired
    private PessoasEventoService pessoasEventoService;
    @Autowired
    private TagsService tagsService;

    /**
     * Adiciona um Evento ao repositório.<br>
     * Atributos de EventoDTO:
     * <li>String nome</li>
     * <li>LocalDateTime data</li>
     * <li>Long idOrganizacao</li>
     *
     * @param eventoDTOs
     * @throws ResponseStatusException
     */
    public void adicionarEvento(EventoDTOs eventoDTOs) {
        organizacaoRepository.findById(eventoDTOs.getIdOrganizacao()).ifPresentOrElse(org -> {
            EventoEntity eventoEntity = new EventoEntity();
            eventoEntity.setNome(eventoDTOs.getNome());
            eventoEntity.setData(eventoDTOs.getData());
            eventoEntity.setOrganizacao(org);
            eventoEntity.setTags(tagsService.tagsPorIds(eventoDTOs.getIdTags()));
            eventoRepository.save(eventoEntity);
        }, () -> {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Organização não encontrada!");
        });
    }

    /**
     * Retorna os Eventos com nomes similares ao passado por parâmetro.
     * @param nomeEvento
     * @return {@code List<EventoBuscaDTO>}
     */
    public List<EventoDTO> buscarEvento(String nomeEvento) {
        List<EventoEntity> eventos = eventoRepository.findAll().stream()
                .filter(ev -> ev.getNome().toLowerCase().contains(nomeEvento.toLowerCase())).collect(Collectors.toList());
        return eventos.stream().map(ev -> {
            EventoDTO dto = new com.entra21.voluntariosApp.model.dto.user.EventoDTO();
            dto.setNome(ev.getNome());
            dto.setData(ev.getData());
            dto.setNomeOrganizacao(ev.getOrganizacao().getNome());
            return dto;
        }).collect(Collectors.toList());
    }

    /**
     * Retorna a lista de pessoas com Presença confirmada em um evento de acordo com o Id dele.
     *
     * @param idEvento
     * @return List
     */
    public List<PessoaEventoPresencaDTO> buscarPresentes(Long idEvento) {
        List<PessoasEventoEntity> pessoasEvento = pessoasEventoRepository.findAllByEvento_Id(idEvento).stream().filter(PessoasEventoEntity::getPresenca).collect(Collectors.toList());
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
     *
     * @param pessoasEventoDTO
     */
    public void adicionarPessoaEvento(PessoasEventoDTO pessoasEventoDTO) {
        eventoRepository.findById(pessoasEventoDTO.getIdEvento()).ifPresentOrElse(ev -> {
            pessoaRepository.findById(pessoasEventoDTO.getIdPessoa()).ifPresentOrElse(pe -> {
                PessoasEventoEntity pessoasEventoEntity = new PessoasEventoEntity();
                pessoasEventoEntity.setPessoa(pe);
                pessoasEventoEntity.setEvento(ev);
                pessoasEventoEntity.setPresenca(Boolean.TRUE);
                pessoasEventoRepository.save(pessoasEventoEntity);
            }, () -> {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Pessoa não encontrada!");
            });
        }, () -> {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Evento não encontrado!");
        });
    }

    /**
     * Retorna todos os Eventos que contiverem a Tag cujo Id for igual ao passado por parâmetro.
     *
     * @param idTag
     * @return {@code List<EventoBuscaDTO>}
     */
    public List<EventoDTO> buscarEventoPorTags(Long idTag) {
        return eventoRepository.findAllBytags_Id(idTag).stream().map(ev -> {
            EventoDTO eBD = new EventoDTO();
            eBD.setNome(ev.getNome());
            eBD.setData(ev.getData());
            eBD.setNomeOrganizacao(ev.getOrganizacao().getNome());
            return eBD;
        }).collect(Collectors.toList());
    }

    public List<EventoInfosDTO> buscarEventoPorListaTags(Long idUser) {
        List<TagsEntity> tagsUser = tagsService.tagsDoUser(idUser);
        List<Long> idsEventosTags = new ArrayList<>();
        tagsUser.forEach(tU -> {
            eventoRepository.findAll().forEach(eE -> {
                if (eE.getTags().contains(tU) && !idsEventosTags.contains(eE.getId())){
                    idsEventosTags.add(eE.getId());
                }
            });
        });
        return eventosPorIds(idsEventosTags);
    }

    /**
     * Retorna uma lista com todos os eventos que possuem tags cujo Id é o mesmo do que o passado por parâmetro
     *
     * @param idTag
     * @return {@code List<EventoDTOs>}
     */
    public List<EventoDTOs> getAll(Long idTag) {
        List<EventoEntity> list = new ArrayList<>();
        if (idTag != null) {
            list = eventoRepository.findAllBytags_Id(idTag);
        } else {
            list = eventoRepository.findAll();
        }
        return list.stream().map(i -> {
            EventoDTOs dto = new EventoDTOs();
            dto.setNome(i.getNome());
            dto.setData(i.getData());
            dto.setIdOrganizacao(i.getOrganizacao().getId());
            return dto;
        }).collect(Collectors.toList());
    }

    /**
     * Atualiza as informações do Evento cujo Id for igual ao passado por parâmetro para as informações
     * contidas em um EventoDTO:
     * <li>String nome</li>
     * <li>LocalDateTime data</li>
     * <li>Long idOrganizacao</li>
     * @param idEvento
     * @param dto
     * @throws ResponseStatusException
     */
    public void atualizarEvento(Long idEvento, EventoDTOs dto) {
        eventoRepository.findById(idEvento).ifPresentOrElse(eE -> {
            organizacaoRepository.findById(dto.getIdOrganizacao()).ifPresentOrElse(org -> {
                eE.setNome(dto.getNome());
                eE.setData(dto.getData());
                eE.setOrganizacao(org);
                eventoRepository.save(eE);
            }, () -> {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Organização não encontrado!");
            });
        }, () -> {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Evento não encontrado!");
        });
    }

    /**
     * Adiciona um patrocinador à lista de patrocinadores do evento.
     *
     * @param dto
     * @throws ResponseStatusException
     */
    public void addPatrocinadorEventoIds(PatrocinadorEventoDTO dto) {
        eventoRepository.findById(dto.getIdEvento()).ifPresentOrElse(evento -> {
            evento.setPatrocinadores(patrocinadorRepository.findAllById(dto.getIdsPatrocinadores()));
            eventoRepository.save(evento);
        }, () -> {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Evento não encontrado!");
        });
    }

    /**
     * Remove um patrocinador da lista de patrocinadores de um evento, de acordo com o seu Id
     *
     * @param idEvento
     * @param idPatrocinador
     * @throws ResponseStatusException
     */
    public void deletarPatrocinadorEvento(Long idEvento, Long idPatrocinador) {
        eventoRepository.findById(idEvento).ifPresentOrElse(evento -> {
            patrocinadorEventoRepository.deleteById(idPatrocinador);
        }, () -> {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Evento não encontrado!");
        });
    }

    /**
     * Retorna todos os patrocinadores de um evento.
     *
     * @param idEvento
     * @return {@code List<PatrocinadorDTO>}
     */
    public List<PatrocinadorDTO> buscarPatrocinadoresPorIdEvento(Long idEvento) {
        return patrocinadorRepository.findAllByEventos_Id(idEvento).stream().map(pE -> {
            PatrocinadorDTO patrocinadorDTO = new PatrocinadorDTO();
            patrocinadorDTO.setNome(pE.getNome());
            patrocinadorDTO.setFotoPatrocinador(pE.getFotoPatrocinador());
            patrocinadorDTO.setRepresentante(pE.getRepresentante());
            return patrocinadorDTO;
        }).collect(Collectors.toList());
    }

    public List<EventoInfosDTO> todosEventos() {
        return eventoRepository.findAll().stream().map(eE -> {
            EventoInfosDTO dto = new EventoInfosDTO();
            dto.setNome(eE.getNome());
            dto.setData(eE.getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));
            dto.setNomeOrganizacao(eE.getOrganizacao().getNome());
//            dto.setPessoasEvento(eE.getPessoasEvento());
            dto.setTagsEvento(eE.getTags().stream().map(TagsEntity::getNome).collect(Collectors.toList()));
            dto.setPatrocinadores(eE.getPatrocinadores());
            dto.setId(eE.getId());
            return dto;
        }).collect(Collectors.toList());
    }

    public List<EventoInfosDTO> eventosUsuarioPresente(Long idUsuario) {
        return pessoasEventoRepository.findAllByPessoa_Id(idUsuario).stream().map(pessoasEventoEntity -> {
            EventoInfosDTO dto = new EventoInfosDTO();
            dto.setNome(pessoasEventoEntity.getEvento().getNome());
            dto.setData(pessoasEventoEntity.getEvento().getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));
            dto.setNomeOrganizacao(pessoasEventoEntity.getEvento().getOrganizacao().getNome());
            return dto;
        }).collect(Collectors.toList());
    }

    public List<EventoInfosDTO> buscarEventoPorIdOrg(Long idOrg) {
        return eventoRepository.findAllByorganizacao_Id(idOrg).stream().map(eE -> {
            EventoInfosDTO dto = new EventoInfosDTO();
            dto.setData(eE.getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));
//            dto.setPessoasEvento(eE.getPessoasEvento());
            dto.setTagsEvento(eE.getTags().stream().map(TagsEntity::getNome).collect(Collectors.toList()));
            dto.setNome(eE.getNome());
            dto.setId(eE.getId());
            dto.setPatrocinadores(eE.getPatrocinadores());
            return dto;
        }).collect(Collectors.toList());
    }

    public EventoInfosDTO eventoPorId(Long idEvento) {
        EventoInfosDTO dto = new EventoInfosDTO();
        eventoRepository.findById(idEvento).ifPresentOrElse(eE -> {
            dto.setData(eE.getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));
            dto.setPessoasEvento(pessoasEventoService.getVoluntariosEvento(eE.getId()));
            dto.setTagsEvento(eE.getTags().stream().map(TagsEntity::getNome).collect(Collectors.toList()));
            dto.setNome(eE.getNome());
            dto.setId(eE.getId());
            dto.setNomeOrganizacao(eE.getOrganizacao().getNome());
            dto.setPatrocinadores(eE.getPatrocinadores());
        }, () -> {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Evento não encontrado!");
        });
        return dto;
    }

    public List<EventoInfosDTO> eventosPorIds(List<Long> ids) {
        List<EventoEntity> eventoInfosDTOS = new ArrayList<>(eventoRepository.findAllById(ids));
        return eventoInfosDTOS.stream().map(eE -> {
            EventoInfosDTO dto = new EventoInfosDTO();
            dto.setNome(eE.getNome());
            dto.setData(eE.getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));
            dto.setNomeOrganizacao(eE.getOrganizacao().getNome());
//            dto.setPessoasEvento(eE.getPessoasEvento());
            dto.setTagsEvento(eE.getTags().stream().map(TagsEntity::getNome).collect(Collectors.toList()));
            dto.setPatrocinadores(eE.getPatrocinadores());
            dto.setId(eE.getId());
            return dto;
        }).collect(Collectors.toList());
    }

    public List<EventoInfosDTO> eventosNaoPresente(Long idUsuario) {
        List<Long> idsEventos = new ArrayList<>();
        List<Long> eventoPessoaPresente = new ArrayList<>();
        pessoasEventoRepository.findAllByPessoa_Id(idUsuario).forEach(pEE -> {
            eventoPessoaPresente.add(pEE.getEvento().getId());
        });
        eventoRepository.findAll().forEach(eE -> {
            if (!eventoPessoaPresente.contains(eE.getId())){
                idsEventos.add(eE.getId());
            }
        });
        System.out.println(idsEventos);
        return eventosPorIds(idsEventos);
    }
}
