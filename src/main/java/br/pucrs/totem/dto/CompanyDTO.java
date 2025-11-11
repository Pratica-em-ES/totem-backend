package br.pucrs.totem.dto;

import java.util.List;

public class CompanyDTO {
    private Long id;
    private String name;
    private String description;
    private String imagePath;
    private BuildingWithNodeDTO building;
    private String block;
    private String room;
    private String floor;
    private List<CategoryDTO> categories;

    public CompanyDTO() {}

    public CompanyDTO(Long id, String name, String description, String imagePath,
                     String block, String room, String floor, BuildingWithNodeDTO building, List<CategoryDTO> categories) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.imagePath = imagePath;
        this.block = block;
        this.room = room;
        this.floor = floor;
        this.building = building;
        this.categories = categories;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public BuildingWithNodeDTO getBuilding() {
        return building;
    }

    public void setBuilding(BuildingWithNodeDTO building) {
        this.building = building;
    }

    public String getBlock() {
        return block;
    }

    public void setBlock(String block) {
        this.block = block;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public List<CategoryDTO> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoryDTO> categories) {
        this.categories = categories;
    }
}
