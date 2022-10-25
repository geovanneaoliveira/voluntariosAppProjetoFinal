package com.entra21.voluntariosApp.view.service;

import com.entra21.voluntariosApp.model.entity.PessoaEntity;
import com.entra21.voluntariosApp.model.entity.PessoasEventoEntity;
import com.entra21.voluntariosApp.view.repository.PessoasEventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PessoasEventoService {
    @Autowired
    private PessoasEventoRepository pessoasEventoRepository;

    public List<PessoaEntity> getVoluntariosEvento(Long idEvento) {
        return pessoasEventoRepository.findAllByEvento_Id(idEvento).stream().map(PessoasEventoEntity::getPessoa).collect(Collectors.toList());
    }
}
