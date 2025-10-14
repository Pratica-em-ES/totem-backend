package br.pucrs.totem.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 500)
    private String name;
    @Column(length = 100)
    private String category;
    @Column(length = 2000)
    private String description;
    @Column(length = 50)
    private String building;
    
    // Relacionamento N:N com Category através de CategoryCompany
    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CategoryCompany> categoryCompanies;
    
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public List<CategoryCompany> getCategoryCompanies() {
        return categoryCompanies;
    }

    public void setCategoryCompanies(List<CategoryCompany> categoryCompanies) {
        this.categoryCompanies = categoryCompanies;
    }
}
