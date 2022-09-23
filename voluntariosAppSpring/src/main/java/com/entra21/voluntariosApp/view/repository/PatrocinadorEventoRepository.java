package com.entra21.voluntariosApp.view.repository;

import com.entra21.voluntariosApp.model.entity.EventoEntity;
import com.entra21.voluntariosApp.model.entity.PatrocinadoresEventoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatrocinadorEventoRepository extends JpaRepository<PatrocinadoresEventoEntity,Long> {

}
