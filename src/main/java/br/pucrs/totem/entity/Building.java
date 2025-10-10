package br.pucrs.totem.entity;

import jakarta.persistence.*;

@Entity
public class Building {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String modelPath;

    @ManyToOne
    @JoinColumn(name = "node_id")
    private Node node;

    @ManyToOne
    @JoinColumn(name = "edge_id")
    private Edge edge;

    @ManyToOne
    @JoinColumn(name = "edge_node_id")
    private Node edgeNode;

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

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }

    public Edge getEdge() {
        return edge;
    }

    public void setEdge(Edge edge) {
        this.edge = edge;
    }

    public Node getEdgeNode() {
        return edgeNode;
    }

    public void setEdgeNode(Node edgeNode) {
        this.edgeNode = edgeNode;
    }
}
