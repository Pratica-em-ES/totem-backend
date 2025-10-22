package br.pucrs.totem.entity;

import jakarta.persistence.*;

@Entity
public class Edge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double weight;

    @ManyToOne
    @JoinColumn(name = "node_a_id")
    private Node nodeA;

    @ManyToOne
    @JoinColumn(name = "node_b_id")
    private Node nodeB;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Node getNodeA() {
        return nodeA;
    }

    public void setNodeA(Node nodeA) {
        this.nodeA = nodeA;
    }

    public Node getNodeB() {
        return nodeB;
    }

    public void setNodeB(Node nodeB) {
        this.nodeB = nodeB;
    }
}
