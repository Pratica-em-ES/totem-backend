package br.pucrs.totem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import br.pucrs.totem.entity.Company;
import java.util.List;

public interface CompanyRepository extends JpaRepository<Company, Long> {
    
    @Query("SELECT c FROM Company c WHERE c.id IN :companyIds")
    List<Company> findByIdIn(@Param("companyIds") List<Long> companyIds);
}