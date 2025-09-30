package br.pucrs.totem.dto;

public class BuildingStreetDTO {
    private Long id;
    private Long buildingId;
    private Long streetId;
    private Long coordinateId;

    public BuildingStreetDTO() {}

    public BuildingStreetDTO(Long id, Long buildingId, Long streetId, Long coordinateId) {
        this.id = id;
        this.buildingId = buildingId;
        this.streetId = streetId;
        this.coordinateId = coordinateId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(Long buildingId) {
        this.buildingId = buildingId;
    }

    public Long getStreetId() {
        return streetId;
    }

    public void setStreetId(Long streetId) {
        this.streetId = streetId;
    }

    public Long getCoordinateId() {
        return coordinateId;
    }

    public void setCoordinateId(Long coordinateId) {
        this.coordinateId = coordinateId;
    }
}