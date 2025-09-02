package br.pucrs.totem.dto;

public class MapDTO {
    private Long id;
    private List<BuildingDTO> buildings;
    private List<StreetDTO> streets;

    public MapDTO() {}

    public MapDTO(Long id, List<BuildingDTO> buildings, List<StreetDTO> streets) {
        this.id = id;
        this.buildings = buildings;
        this.streets = streets;
    }

    public Long getId() { return id; }

    public List<BuildingDTO> getBuildings() { return buildings; }
    public void setBuildings(List<BuildingDTO> buildings) { this.buildings = buildings; }

    public List<StreetDTO> getStreets() { return streets; }
    public void setStreets(List<StreetDTO> streets) { this.streets = streets; }
}