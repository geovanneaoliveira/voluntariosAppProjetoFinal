package com.entra21.voluntariosApp.view.repository;

import com.entra21.voluntariosApp.model.entity.PessoasEventoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PessoasEventoRepository extends JpaRepository<PessoasEventoEntity,Long> {
    List<PessoasEventoEntity> findAllByEvento_Id(Long idEvento);
    List<PessoasEventoEntity> findAllByPessoa_Id(Long idPessoa);
}
