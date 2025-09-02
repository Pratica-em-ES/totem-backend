package br.pucrs.totem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.pucrs.totem.entity_old.MapModel;

public interface MapRepository extends JpaRepository<MapModel, Long> {
}
