package br.pucrs.totem.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import br.pucrs.totem.entity.BuildingStreet;

public interface BuildingStreetRepository extends JpaRepository<BuildingStreet, Long> {
    List<BuildingStreet> findByBuildingId(Long buildingId);
    List<BuildingStreet> findByStreetId(Long streetId);
}