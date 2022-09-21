package com.entra21.voluntariosApp.view.repository;

import com.entra21.voluntariosApp.model.entity.ContribuicaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContribuicaoRepository extends JpaRepository<ContribuicaoEntity, Long> {
    List<ContribuicaoEntity> findAllBypessoa_Id(Long idPessoa);
}
