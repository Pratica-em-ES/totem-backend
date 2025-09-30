package br.pucrs.totem.dto;

public class BuildingCompanyDTO {
    private Long id;
    private Long buildingId;
    private Long companyId;
    private String floors;

    public BuildingCompanyDTO() {}

    public BuildingCompanyDTO(Long id, Long buildingId, Long companyId, String floors) {
        this.id = id;
        this.buildingId = buildingId;
        this.companyId = companyId;
        this.floors = floors;
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

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getFloors() {
        return floors;
    }

    public void setFloors(String floors) {
        this.floors = floors;
    }
}