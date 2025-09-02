package br.pucrs.totem.entity;

import jakarta.persistence.*;

@Entity
public class Street {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double width;

    @ManyToOne
    @JoinColumn(name = "coordinate_a_id")
    private Coordinate coordinateA;

    @ManyToOne
    @JoinColumn(name = "coordinate_b_id")
    private Coordinate coordinateB;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getWidth() {
        return width;
    }

    public void setWidth(Double width) {
        this.width = width;
    }

    public Coordinate getCoordinateA() {
        return coordinateA;
    }

    public void setCoordinateA(Coordinate coordinateA) {
        this.coordinateA = coordinateA;
    }

    public Coordinate getCoordinateB() {
        return coordinateB;
    }

    public void setCoordinateB(Coordinate coordinateB) {
        this.coordinateB = coordinateB;
    }
}
