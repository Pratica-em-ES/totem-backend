package br.pucrs.totem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.pucrs.totem.entity.BuildingCompany;

import java.util.List;

@Repository
public interface BuildingCompanyRepository extends JpaRepository<BuildingCompany, Long> {
    
    @Query("SELECT bc FROM BuildingCompany bc WHERE bc.building.id = :buildingId")
    List<BuildingCompany> findByBuildingId(@Param("buildingId") Long buildingId);
    
    @Query("SELECT bc FROM BuildingCompany bc WHERE bc.company.id = :companyId")
    List<BuildingCompany> findByCompanyId(@Param("companyId") Long companyId);
}