package br.pucrs.totem.dto;

public class NodeDTO {
    private Long id;
    private Double x;
    private Double y;

    public NodeDTO() {
    }

    public NodeDTO(Double x, Double y) {
        this.x = x;
        this.y = y;
    }

    public NodeDTO(Long id, Double x, Double y) {
        this.id = id;
        this.x = x;
        this.y = y;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }
}
