package br.pucrs.totem.model;

import jakarta.persistence.*;

@Entity
public class BuildingCompany {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "building_id")
    private Building building;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    private String floors;
    // getters and setters
}
