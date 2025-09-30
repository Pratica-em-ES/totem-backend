package br.pucrs.totem.dto;

import java.util.List;

public class MapDTO {
    private List<MapBuildingDTO> buildings;
    private List<MapStreetDTO> streets;

    public MapDTO() {}

    public MapDTO(List<MapBuildingDTO> buildings, List<MapStreetDTO> streets) {
        this.buildings = buildings;
        this.streets = streets;
    }

    public List<MapBuildingDTO> getBuildings() {
        return buildings;
    }

    public void setBuildings(List<MapBuildingDTO> buildings) {
        this.buildings = buildings;
    }

    public List<MapStreetDTO> getStreets() {
        return streets;
    }

    public void setStreets(List<MapStreetDTO> streets) {
        this.streets = streets;
    }
}