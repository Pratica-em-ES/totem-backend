package br.pucrs.totem.dto;

public class CompanyDTO {
    private Long id;
    private String name;
    private String category;
    private String description;
    private String building;

    public CompanyDTO() {}

    public CompanyDTO(Long id, String name, String category, String description, String building) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.description = description;
        this.building = building;
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
}