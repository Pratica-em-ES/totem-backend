package br.pucrs.totem.dto;

import java.util.List;

public class MapDTO {
    private List<BuildingDTO> buildings;
    private List<StreetDTO> streets;

    public MapDTO() {}

    public MapDTO(List<BuildingDTO> buildings, List<StreetDTO> streets) {
        this.buildings = buildings;
        this.streets = streets;
    }

    public List<BuildingDTO> getBuildings() {
        return buildings;
    }

    public void setBuildings(List<BuildingDTO> buildings) {
        this.buildings = buildings;
    }

    public List<StreetDTO> getStreets() {
        return streets;
    }

    public void setStreets(List<StreetDTO> streets) {
        this.streets = streets;
    }
}