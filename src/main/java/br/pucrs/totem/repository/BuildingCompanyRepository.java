package br.pucrs.totem.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import br.pucrs.totem.entity.BuildingCompany;

public interface BuildingCompanyRepository extends JpaRepository<BuildingCompany, Long> {
    List<BuildingCompany> findByCompanyId(Long companyId);
    List<BuildingCompany> findByBuildingId(Long buildingId);
}