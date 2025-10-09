package br.pucrs.totem.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import br.pucrs.totem.entity.BuildingEdge;

public interface BuildingEdgeRepository extends JpaRepository<BuildingEdge, Long> {
    List<BuildingEdge> findByBuildingId(Long buildingId);
    List<BuildingEdge> findByEdgeId(Long edgeId);
}
