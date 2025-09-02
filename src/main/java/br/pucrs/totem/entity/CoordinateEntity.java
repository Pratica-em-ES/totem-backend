package br.pucrs.totem.entity;

import jakarta.persistence.*;

@Entity
public class CoordinateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double x;
    private Double y;
    // getters and setters
}
