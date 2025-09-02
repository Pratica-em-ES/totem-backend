package br.pucrs.totem.model;

import jakarta.persistence.*;

@Entity
public class BuildingStreet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "building_id")
    private Building building;

    @ManyToOne
    @JoinColumn(name = "street_id")
    private Street street;

    @ManyToOne
    @JoinColumn(name = "coordinate_id")
    private Coordinate coordinate;
    // getters and setters
}
