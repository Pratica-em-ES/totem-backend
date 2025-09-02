package br.pucrs.totem.entity;

import jakarta.persistence.*;

@Entity
public class CompanyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String category;
    private String description;
    private String building;
    // getters and setters
}
