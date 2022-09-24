package com.entra21.voluntariosApp.view.service;

import com.entra21.voluntariosApp.model.dto.*;
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

    /**
     * Adiciona um Evento ao repositório.<br>
     * Atributos de EventoDTO:
     * <li>String nome</li>
     * <li>LocalDateTime data</li>
     * <li>Long idOrganizacao</li>
     * @param eventoDTOs
     */
    public void adicionarEvento(EventoDTOs eventoDTOs) {
        organizacaoRepository.findById(eventoDTOs.getIdOrganizacao()).ifPresentOrElse(org -> {
            EventoEntity eventoEntity = new EventoEntity();
            eventoEntity.setNome(eventoDTOs.getNome());
            eventoEntity.setData(eventoDTOs.getData());
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
    public List<EventoDTO> buscarEvento(String nome) {
        List<EventoEntity> eventos = eventoRepository.findAll().stream()
                .filter(ev -> ev.getNome().toLowerCase().contains(nome.toLowerCase())).collect(Collectors.toList());
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

    /**
     * Retorna todos os Eventos que contiverem a Tag cujo Id for igual ao passado por parâmetro.
     * @param idTag
     * @return List {@code <EventoBuscaDTO>}
     */
    public List<EventoDTO> findEventoByTags(Long idTag) {
        return eventoRepository.findAllBytags_Id(idTag).stream().map(ev -> {
            EventoDTO eBD = new com.entra21.voluntariosApp.model.dto.user.EventoDTO();
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
    public void atualizarEvento(Long id, EventoDTOs dto) {
        eventoRepository.findById(id).ifPresentOrElse(eE -> {
            eE.setNome(dto.getNome());
            eE.setData(dto.getData());
            eE.setOrganizacao(eE.getOrganizacao());
            eventoRepository.save(eE);
        }, () -> {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Evento não encontrado!");
        });
    }
}

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
