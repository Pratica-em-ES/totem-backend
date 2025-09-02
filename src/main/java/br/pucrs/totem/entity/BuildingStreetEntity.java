package br.pucrs.totem.entity;

import jakarta.persistence.*;

@Entity
public class BuildingStreetEntity {
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
    private CoordinateEntity coordinate;
    // getters and setters
}
