package com.entra21.voluntariosApp.view.service;

import com.entra21.voluntariosApp.model.dto.server.ContribuicaoDTOs;
import com.entra21.voluntariosApp.model.dto.user.ContribuicaoDTO;
import com.entra21.voluntariosApp.model.dto.user.ContribuicaoInfosDTO;
import com.entra21.voluntariosApp.model.entity.ContribuicaoEntity;
import com.entra21.voluntariosApp.view.repository.ContribuicaoRepository;
import com.entra21.voluntariosApp.view.repository.EventoRepository;
import com.entra21.voluntariosApp.view.repository.OrganizacaoRepository;
import com.entra21.voluntariosApp.view.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
public class ContribuicaoService {

    @Autowired
    private ContribuicaoRepository contribuicaoRepository;

    @Autowired
    private OrganizacaoRepository organizacaoRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private EventoRepository eventoRepository;

    /**
     * Adiciona uma contribuição ao Repositório.<br>
     * Atributos de ContribuicaoDTO:
     * <li>LocalDateTime data</li>
     * <li>Double valor</li>
     * <li>Long idPessoa</li>
     * <li>Long idOrganizacao</li>
     * @param contribuicaoDTOs
     * @throws ResponseStatusException
     */
    public void addContribuicao(ContribuicaoDTOs contribuicaoDTOs) {
        ContribuicaoEntity cE = new ContribuicaoEntity();
        cE.setData(contribuicaoDTOs.getData());
        cE.setValor(contribuicaoDTOs.getValor());
        cE.setOrganizacao(organizacaoRepository.findById(contribuicaoDTOs.getIdOrganizacao()).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Organização não encontrada!")));
        cE.setPessoa(pessoaRepository.findById(contribuicaoDTOs.getIdPessoa()).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Contribuinte não encontrado!")));
        contribuicaoRepository.save(cE);
    }

    /**
     * Retorna todas as contribuições já realizadas.
     * @return List {@code <GetContribuicoesDTO>}
     */
    public Set<ContribuicaoInfosDTO> buscarContribuicoes() {
        return contribuicaoRepository.findAll().stream().map(cE -> {
            ContribuicaoInfosDTO cdto = new ContribuicaoInfosDTO();
            cdto.setData(cE.getData());
            cdto.setValor(cE.getValor());
            cdto.setNomeOrg(cE.getOrganizacao().getNome());
            cdto.setNomeUsuario(cE.getPessoa().getNome());
            cdto.setSobrenome(cE.getPessoa().getSobrenome());
            cdto.setId(cE.getId());
            return cdto;
        }).collect(Collectors.toSet());
    }

    /**
     * Retorna todas as contribuições recebidas por uma Organização especificada pelo Id.
     * @param idOrg
     * @return List {@code <GetContribuicoesDTO>}
     */
    public Set<ContribuicaoDTO> buscarContribuicoesPorOrg(Long idOrg) {
        return contribuicaoRepository.findAll().stream()
                .filter(cE -> Objects.equals(cE.getOrganizacao().getId(), idOrg)).map(cE -> {
                    ContribuicaoDTO gcd = new ContribuicaoDTO();
                    gcd.setData(cE.getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));
                    gcd.setValor(cE.getValor());
                    gcd.setNomeOrg(cE.getOrganizacao().getNome());
                    gcd.setNomeUsuario(cE.getPessoa().getNome());
                    gcd.setSobrenome(cE.getPessoa().getSobrenome());
                    return gcd;
                }).collect(Collectors.toSet());
    }

    /**
     * Retorna todas as contribuições já realizadas por um Usuário especificado pelo Id.
     * @param idUser
     * @return List {@code <GetContribuicoesDTO>}
     */
    public Set<ContribuicaoDTO> buscarContribuicoesPorUser(Long idUser) {
        return contribuicaoRepository.findAllBypessoa_Id(idUser).stream()
                .filter(cE -> Objects.equals(cE.getPessoa().getId(), idUser)).map(cE -> {
                    ContribuicaoDTO contribuicaoUser = new ContribuicaoDTO();
                    contribuicaoUser.setData(cE.getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));
                    contribuicaoUser.setValor(cE.getValor());
                    contribuicaoUser.setNomeUsuario(cE.getPessoa().getNome());
                    contribuicaoUser.setSobrenome(cE.getPessoa().getSobrenome());
                    contribuicaoUser.setNomeOrg(cE.getOrganizacao().getNome());
                    return contribuicaoUser;
                }).collect(Collectors.toSet());
    }

    /**
     * Retorna a soma de todas as Contribuições já recebidas por uma Organização.
     * @param idOrg
     * @return Double
     */
    public Double totalContribuicoesPorOrg(Long idOrg) {
        AtomicReference<Double> valorTotal = new AtomicReference<>(0.0);
        buscarContribuicoesPorOrg(idOrg).forEach(cE -> {
            valorTotal.updateAndGet(v -> v + cE.getValor());
        });
        return valorTotal.get();
    }

    /**
     * Retorna a soma das Contribuições já realizados por um Usuário;
     * @param idUser
     * @return Double
     */
    public Double totalContribuicoesPorUser(Long idUser) {
        AtomicReference<Double> valorTotal = new AtomicReference<>(0.0);
        buscarContribuicoesPorUser(idUser).forEach(cE -> {
            valorTotal.updateAndGet(v -> v + cE.getValor());
        });
        return valorTotal.get();
    }
}