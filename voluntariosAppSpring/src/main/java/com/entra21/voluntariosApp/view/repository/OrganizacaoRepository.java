package com.entra21.voluntariosApp.view.repository;

import com.entra21.voluntariosApp.model.entity.OrganizacaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrganizacaoRepository extends JpaRepository<OrganizacaoEntity,Long> {

    List<OrganizacaoEntity> findAllBysupervisor_Id(Long id);
}
