package br.pucrs.totem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.List;

@Entity
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 500)
    private String name;
    @Column(length = 2000)
    private String description;
    @Column(length = 50)
    private String building;
    private String image_path;

    @OneToMany(mappedBy = "company")
    @JsonIgnore
    private List<CategoryCompany> categories;
    
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

    public String getImagePath() {
        return image_path;
    }

    public void setImagePath(String image_path) {
        this.image_path = image_path;
    }

    public List<CategoryCompany> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoryCompany> categories) {
        this.categories = categories;
    }
}
