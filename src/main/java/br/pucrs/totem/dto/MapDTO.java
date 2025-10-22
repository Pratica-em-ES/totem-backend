package br.pucrs.totem.dto;

import java.util.List;

public class MapDTO {
    private List<NodeDTO> nodes;
    private List<EdgeDTO> edges;
    private List<BuildingDTO> buildings;

    public MapDTO() {}

    public MapDTO(List<NodeDTO> nodes, List<EdgeDTO> edges, List<BuildingDTO> buildings) {
        this.nodes = nodes;
        this.edges = edges;
        this.buildings = buildings;
    }

    public List<NodeDTO> getNodes() {
        return nodes;
    }

    public void setNodes(List<NodeDTO> nodes) {
        this.nodes = nodes;
    }

    public List<EdgeDTO> getEdges() {
        return edges;
    }

    public void setEdges(List<EdgeDTO> edges) {
        this.edges = edges;
    }

    public List<BuildingDTO> getBuildings() {
        return buildings;
    }

    public void setBuildings(List<BuildingDTO> buildings) {
        this.buildings = buildings;
    }
}
