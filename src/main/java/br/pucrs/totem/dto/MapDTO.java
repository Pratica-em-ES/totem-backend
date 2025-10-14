package br.pucrs.totem.dto;

import java.util.List;

public class MapDTO {
    private List<BuildingDTO> buildings;
    private List<EdgeDTO> edges;

    public MapDTO() {}

    public MapDTO(List<BuildingDTO> buildings, List<EdgeDTO> edges) {
        this.buildings = buildings;
        this.edges = edges;
    }

    public List<BuildingDTO> getBuildings() {
        return buildings;
    }

    public void setBuildings(List<BuildingDTO> buildings) {
        this.buildings = buildings;
    }

    public List<EdgeDTO> getEdges() {
        return edges;
    }

    public void setEdges(List<EdgeDTO> edges) {
        this.edges = edges;
    }
}
