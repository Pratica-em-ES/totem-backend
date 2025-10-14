package br.pucrs.totem.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(length = 100, unique = true)
    private String name;
    
    // Relacionamento N:N com Company através de CategoryCompany
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CategoryCompany> categoryCompanies;
    
    public Category() {}
    
    public Category(String name) {
        this.name = name;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CategoryCompany> getCategoryCompanies() {
        return categoryCompanies;
    }

    public void setCategoryCompanies(List<CategoryCompany> categoryCompanies) {
        this.categoryCompanies = categoryCompanies;
    }
}