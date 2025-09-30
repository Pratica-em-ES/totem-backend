package br.pucrs.totem.dto;

public class StreetDTO {
    private Long id;
    private Double width;
    private Long coordinateAId;
    private Long coordinateBId;

    public StreetDTO() {}

    public StreetDTO(Long id, Double width, Long coordinateAId, Long coordinateBId) {
        this.id = id;
        this.width = width;
        this.coordinateAId = coordinateAId;
        this.coordinateBId = coordinateBId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getWidth() {
        return width;
    }

    public void setWidth(Double width) {
        this.width = width;
    }

    public Long getCoordinateAId() {
        return coordinateAId;
    }

    public void setCoordinateAId(Long coordinateAId) {
        this.coordinateAId = coordinateAId;
    }

    public Long getCoordinateBId() {
        return coordinateBId;
    }

    public void setCoordinateBId(Long coordinateBId) {
        this.coordinateBId = coordinateBId;
    }
}