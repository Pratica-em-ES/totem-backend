package br.pucrs.totem.dto;

public class CoordinateDTO {
    private Double x;
    private Double y;

    public CoordinateDTO() {
    }

    public CoordinateDTO(Double x, Double y) {
        this.x = x;
        this.y = y;
    }

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }
}