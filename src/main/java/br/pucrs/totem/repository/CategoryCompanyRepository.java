package br.pucrs.totem.repository;

import br.pucrs.totem.entity.CategoryCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryCompanyRepository extends JpaRepository<CategoryCompany, Long> {
    List<CategoryCompany> findByCategoryId(Long categoryId);
    List<CategoryCompany> findByCompanyId(Long companyId);
    Optional<CategoryCompany> findByCompanyIdAndCategoryId(Long companyId, Long categoryId);
}