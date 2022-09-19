package com.entra21.voluntariosApp.view.repository;

import com.entra21.voluntariosApp.model.entity.PessoasEventoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PessoasEventoRepository extends JpaRepository<PessoasEventoEntity,Long> {

    List<PessoasEventoEntity> findAllByidEvento_Id(Long idEvento);

}
