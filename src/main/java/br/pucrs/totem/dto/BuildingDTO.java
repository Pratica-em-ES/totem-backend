package br.pucrs.totem.dto;

public class BuildingDTO {
    private String name;
    private String modelPath;
    private NodeDTO node;

    public BuildingDTO() {}

    public BuildingDTO(String name, String modelPath, NodeDTO node) {
        this.name = name;
        this.modelPath = modelPath;
        this.node = node;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModelPath() {
        return modelPath;
    }

    public void setModelPath(String modelPath) {
        this.modelPath = modelPath;
    }

    public NodeDTO getNode() {
        return node;
    }

    public void setNode(NodeDTO node) {
        this.node = node;
    }
}
