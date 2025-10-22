package br.pucrs.totem.dto;

public class BuildingDTO {
    private Long id;
    private String name;
    private String modelPath;
    private Long nodeId;

    public BuildingDTO() {}

    public BuildingDTO(Long id, String name, String modelPath, Long nodeId) {
        this.id = id;
        this.name = name;
        this.modelPath = modelPath;
        this.nodeId = nodeId;
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

    public Long getNodeId() {
        return nodeId;
    }

    public void setNodeId(Long nodeId) {
        this.nodeId = nodeId;
    }
}
