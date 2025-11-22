package br.pucrs.totem.service;

import org.springframework.stereotype.Service;
import br.pucrs.totem.entity.Category;
import br.pucrs.totem.entity.CategoryCompany;
import br.pucrs.totem.entity.Company;
import br.pucrs.totem.repository.CategoryRepository;
import br.pucrs.totem.repository.CategoryCompanyRepository;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    
    private final CategoryRepository categoryRepository;
    private final CategoryCompanyRepository categoryCompanyRepository;

    public CategoryService(CategoryRepository categoryRepository, 
                          CategoryCompanyRepository categoryCompanyRepository) {
        this.categoryRepository = categoryRepository;
        this.categoryCompanyRepository = categoryCompanyRepository;
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAllOrderByNameAsc();
    }
    
    public Optional<Category> findCategoryById(Long id) {
        return categoryRepository.findById(id);
    }
    
    /**
     * Busca empresas que têm uma categoria específica
     */
    public List<Company> getCompaniesByCategoryId(Long categoryId) {
        return categoryCompanyRepository.findByCategoryId(categoryId).stream()
                .map(CategoryCompany::getCompany)
                .toList();
    }

    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }
}