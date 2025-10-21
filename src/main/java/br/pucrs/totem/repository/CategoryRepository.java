package br.pucrs.totem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import br.pucrs.totem.entity.Category;
import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    
    Optional<Category> findByName(String name);
    
    @Query("SELECT c FROM Category c ORDER BY c.name ASC")
    List<Category> findAllOrderByNameAsc();
}