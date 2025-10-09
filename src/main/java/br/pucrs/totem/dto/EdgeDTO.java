package br.pucrs.totem.dto;

public class EdgeDTO {
    private Double width;
    private NodeDTO nodeA;
    private NodeDTO nodeB;

    public EdgeDTO() {}

    public EdgeDTO(Double width, NodeDTO nodeA, NodeDTO nodeB) {
        this.width = width;
        this.nodeA = nodeA;
        this.nodeB = nodeB;
    }

    public Double getWidth() {
        return width;
    }

    public void setWidth(Double width) {
        this.width = width;
    }

    public NodeDTO getNodeA() {
        return nodeA;
    }

    public void setNodeA(NodeDTO nodeA) {
        this.nodeA = nodeA;
    }

    public NodeDTO getNodeB() {
        return nodeB;
    }

    public void setNodeB(NodeDTO nodeB) {
        this.nodeB = nodeB;
    }
}
