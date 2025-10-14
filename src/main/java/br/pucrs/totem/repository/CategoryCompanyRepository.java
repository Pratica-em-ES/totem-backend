package br.pucrs.totem.repository;

import br.pucrs.totem.entity.CategoryCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryCompanyRepository extends JpaRepository<CategoryCompany, Long> {
    
    @Query("SELECT cc FROM CategoryCompany cc WHERE cc.company.id = :companyId")
    List<CategoryCompany> findByCompanyId(@Param("companyId") Long companyId);
    
    @Query("SELECT cc FROM CategoryCompany cc WHERE cc.category.id = :categoryId")
    List<CategoryCompany> findByCategoryId(@Param("categoryId") Long categoryId);
}