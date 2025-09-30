package br.pucrs.totem.dto;

public class BuildingDTO {
    private Long id;
    private String name;
    private String modelPath;
    private Long coordinateId;

    public BuildingDTO() {}

    public BuildingDTO(Long id, String name, String modelPath, Long coordinateId) {
        this.id = id;
        this.name = name;
        this.modelPath = modelPath;
        this.coordinateId = coordinateId;
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

    public String getModelPath() {
        return modelPath;
    }

    public void setModelPath(String modelPath) {
        this.modelPath = modelPath;
    }

    public Long getCoordinateId() {
        return coordinateId;
    }

    public void setCoordinateId(Long coordinateId) {
        this.coordinateId = coordinateId;
    }
}