package com.entra21.voluntariosApp.view.service;

import com.entra21.voluntariosApp.model.dto.ContribuicaoDTO;
import com.entra21.voluntariosApp.model.entity.ContribuicaoEntity;
import com.entra21.voluntariosApp.view.repository.ContribuicaoRepository;
import com.entra21.voluntariosApp.view.repository.OrganizacaoRepository;
import com.entra21.voluntariosApp.view.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContribuicaoService {

    @Autowired
    private ContribuicaoRepository contribuicaoRepository;

    @Autowired
    private OrganizacaoRepository organizacaoRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    public void addContribuicao(ContribuicaoDTO input) {
        ContribuicaoEntity cE = new ContribuicaoEntity();
        cE.setData(input.getData());
        cE.setValor(input.getValor());
        cE.setIdOrganizacao(organizacaoRepository.findById(input.getIdOrganizacao()).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST)));
        cE.setIdPessoa(pessoaRepository.findById(input.getIdPessoa()).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST)));
        contribuicaoRepository.save(cE);
    }

    public List<ContribuicaoDTO> findAllContribuicao(){
        return contribuicaoRepository.findAll().stream().map(FC -> {
            ContribuicaoDTO cdto= new ContribuicaoDTO();
            cdto.setData(FC.getData());
            cdto.setValor(FC.getValor());
            cdto.setIdOrganizacao(FC.getId());
            cdto.setIdPessoa(FC.getId());
            return cdto;
<<<<<<< Updated upstream
        } ).collect(Collectors.toList());
=======
        }).collect(Collectors.toList());
    }

    public List<GetContribuicoesDTO> findContribuicoesByOrg(Long idOrg) {
        return contribuicaoRepository.findAll().stream().filter(cE ->
                Objects.equals(cE.getOrganizacao().getId(), idOrg)).map(cE -> {
                    GetContribuicoesDTO gcd = new GetContribuicoesDTO();
                    gcd.setData(cE.getData());
                    gcd.setValor(cE.getValor());
                    gcd.setNomeOrg(cE.getOrganizacao().getNome());
                    gcd.setNomeUsuario(cE.getPessoa().getNome());
                    gcd.setSobrenome(cE.getPessoa().getSobrenome());
                    return gcd;
                }).collect(Collectors.toList());
    }

    public List<GetContribuicoesDTO> findContribuicoesByUser(Long idUser) {
        return contribuicaoRepository.findAllBypessoa_Id(idUser).stream()
                .filter(cE -> Objects.equals(cE.getPessoa().getId(), idUser)).map(cE -> {
                    GetContribuicoesDTO contribuicaoUser = new GetContribuicoesDTO();
                    contribuicaoUser.setData(cE.getData());
                    contribuicaoUser.setValor(cE.getValor());
                    contribuicaoUser.setNomeUsuario(cE.getPessoa().getNome());
                    contribuicaoUser.setSobrenome(cE.getPessoa().getSobrenome());
                    contribuicaoUser.setNomeOrg(cE.getOrganizacao().getNome());
                    return contribuicaoUser;
                }).collect(Collectors.toList());
    }

    public Double getTotalContribuicoesByOrg(Long idOrg) {
        AtomicReference<Double> valorTotal = new AtomicReference<>(0.0);
        findContribuicoesByOrg(idOrg).forEach(cE -> {
            valorTotal.updateAndGet(v -> v + cE.getValor());
        });
        return valorTotal.get();
    }

    public Double getTotalContribuicoesByUser(Long idUser) {
        AtomicReference<Double> valorTotal = new AtomicReference<>(0.0);
        findContribuicoesByUser(idUser).forEach(cE -> {
            valorTotal.updateAndGet(v -> v + cE.getValor());
        });
        return valorTotal.get();
>>>>>>> Stashed changes
    }
}