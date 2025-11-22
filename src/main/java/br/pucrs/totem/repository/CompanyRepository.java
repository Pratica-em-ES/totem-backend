package br.pucrs.totem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import br.pucrs.totem.entity.Company;
import java.util.List;

public interface CompanyRepository extends JpaRepository<Company, Long> {
    @Query("SELECT c FROM Company c ORDER BY c.name ASC")
    List<Company> findAllOrderByNameAsc();

    List<Company> findByBuildingId(Long buildingId);
}