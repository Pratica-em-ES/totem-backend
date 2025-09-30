package br.pucrs.totem.dto;

public class MapStreetDTO {
    private Double width;
    private MapCoordinateDTO coordinateA;
    private MapCoordinateDTO coordinateB;

    public MapStreetDTO() {}

    public MapStreetDTO(Double width, MapCoordinateDTO coordinateA, MapCoordinateDTO coordinateB) {
        this.width = width;
        this.coordinateA = coordinateA;
        this.coordinateB = coordinateB;
    }

    public Double getWidth() {
        return width;
    }

    public void setWidth(Double width) {
        this.width = width;
    }

    public MapCoordinateDTO getCoordinateA() {
        return coordinateA;
    }

    public void setCoordinateA(MapCoordinateDTO coordinateA) {
        this.coordinateA = coordinateA;
    }

    public MapCoordinateDTO getCoordinateB() {
        return coordinateB;
    }

    public void setCoordinateB(MapCoordinateDTO coordinateB) {
        this.coordinateB = coordinateB;
    }
}
