package br.pucrs.totem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.pucrs.totem.entity.BuildingStreet;

import java.util.List;

@Repository
public interface BuildingStreetRepository extends JpaRepository<BuildingStreet, Long> {
    
    @Query("SELECT bs FROM BuildingStreet bs WHERE bs.building.id = :buildingId")
    List<BuildingStreet> findByBuildingId(@Param("buildingId") Long buildingId);
    
    @Query("SELECT bs FROM BuildingStreet bs WHERE bs.street.id = :streetId")
    List<BuildingStreet> findByStreetId(@Param("streetId") Long streetId);
}