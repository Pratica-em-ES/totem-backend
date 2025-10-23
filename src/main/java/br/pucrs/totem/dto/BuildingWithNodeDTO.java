package br.pucrs.totem.dto;

public class BuildingWithNodeDTO {
    private Long id;
    private String name;
    private String modelPath;
    private NodeDTO node;

    public BuildingWithNodeDTO() {}

    public BuildingWithNodeDTO(Long id, String name, String modelPath, NodeDTO node) {
        this.id = id;
        this.name = name;
        this.modelPath = modelPath;
        this.node = node;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
