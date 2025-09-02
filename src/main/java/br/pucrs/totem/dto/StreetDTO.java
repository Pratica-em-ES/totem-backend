package br.pucrs.totem.dto;

public class StreetDTO {
    private Double width;
    private CoordinateDTO coordinateA;
    private CoordinateDTO coordinateB;

    public StreetDTO() {}

    public StreetDTO(Double width, CoordinateDTO coordinateA, CoordinateDTO coordinateB) {
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

    public CoordinateDTO getCoordinateA() {
        return coordinateA;
    }

    public void setCoordinateA(CoordinateDTO coordinateA) {
        this.coordinateA = coordinateA;
    }

    public CoordinateDTO getCoordinateB() {
        return coordinateB;
    }

    public void setCoordinateB(CoordinateDTO coordinateB) {
        this.coordinateB = coordinateB;
    }
}
