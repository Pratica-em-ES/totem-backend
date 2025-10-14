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
        return categoryRepository.findAll();
    }

    /**
     * Busca categorias das empresas através da nova relação N:N
     */
    public List<String> getCategoriesByCompanyIds(List<Long> companyIds) {
        return categoryCompanyRepository.findAll().stream()
                .filter(cc -> companyIds.contains(cc.getCompany().getId()))
                .map(cc -> cc.getCategory().getName())
                .distinct()
                .sorted()
                .toList();
    }
    
    /**
     * Busca empresas por categoria usando a relação N:N
     */
    public List<Company> getCompaniesByCategory(String categoryName) {
        Optional<Category> category = categoryRepository.findByName(categoryName);
        if (category.isPresent()) {
            return categoryCompanyRepository.findByCategoryId(category.get().getId()).stream()
                    .map(CategoryCompany::getCompany)
                    .toList();
        }
        return List.of();
    }

    /**
     * Retorna todas as categorias distintas
     */
    public List<String> getAllDistinctCategories() {
        return categoryRepository.findAll().stream()
                .map(Category::getName)
                .sorted()
                .toList();
    }
    
    public Optional<Category> findCategoryByName(String name) {
        return categoryRepository.findByName(name);
    }
    
    public Optional<Category> findCategoryById(Long id) {
        return categoryRepository.findById(id);
    }
    
    /**
     * Busca empresas que têm uma categoria específica (novo método N:N)
     */
    public List<Company> getCompaniesByCategoryId(Long categoryId) {
        return categoryCompanyRepository.findByCategoryId(categoryId).stream()
                .map(CategoryCompany::getCompany)
                .toList();
    }
    
    /**
     * Busca categorias de uma empresa específica
     */
    public List<Category> getCategoriesByCompanyId(Long companyId) {
        return categoryCompanyRepository.findByCompanyId(companyId).stream()
                .map(CategoryCompany::getCategory)
                .toList();
    }
}