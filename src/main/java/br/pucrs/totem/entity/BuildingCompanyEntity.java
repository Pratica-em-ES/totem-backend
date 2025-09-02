package br.pucrs.totem.entity;

import jakarta.persistence.*;

@Entity
public class BuildingCompanyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "building_id")
    private Building building;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private CompanyEntity company;

    private String floors;
    // getters and setters
}
