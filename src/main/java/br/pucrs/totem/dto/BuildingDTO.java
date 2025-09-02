package br.pucrs.totem.dto;

import br.pucrs.totem.entity.Coordinate;

public class BuildingDTO {
    private String name;
    private String modelPath;
    private Coordinate coordinate;

    public BuildingDTO() {}

    public BuildingDTO(String name, String modelPath, Coordinate coordinate) {
        this.name = name;
        this.modelPath = modelPath;
        this.coordinate = coordinate;
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

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }
}
