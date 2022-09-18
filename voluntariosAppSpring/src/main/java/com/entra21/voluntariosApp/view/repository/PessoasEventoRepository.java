package com.entra21.voluntariosApp.view.repository;

import com.entra21.voluntariosApp.model.entity.PessoasEventoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PessoasEventoRepository extends JpaRepository<PessoasEventoEntity,Long> {

    public List<PessoasEventoEntity> findAllByidEvento(Long idEvento);

}
