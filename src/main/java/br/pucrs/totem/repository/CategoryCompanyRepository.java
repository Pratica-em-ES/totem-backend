package br.pucrs.totem.repository;

import br.pucrs.totem.entity.CategoryCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CategoryCompanyRepository extends JpaRepository<CategoryCompany, Long> {
    List<CategoryCompany> findByCategoryId(Long categoryId);
    List<CategoryCompany> findByCompanyId(Long companyId);
}