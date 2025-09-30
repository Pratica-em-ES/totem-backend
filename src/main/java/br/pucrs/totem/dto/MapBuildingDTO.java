package br.pucrs.totem.dto;

public class MapBuildingDTO {
    private String name;
    private String modelPath;
    private MapCoordinateDTO coordinate;

    public MapBuildingDTO() {}

    public MapBuildingDTO(String name, String modelPath, MapCoordinateDTO coordinate) {
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

    public MapCoordinateDTO getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(MapCoordinateDTO coordinate) {
        this.coordinate = coordinate;
    }
}
