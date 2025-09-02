package br.pucrs.totem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.pucrs.totem.entity.Building;

public interface BuildingRepository extends JpaRepository<Building, Long> {
}
