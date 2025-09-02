    @ManyToOne
    @JoinColumn(name = "map_id")
    private Map map;
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
    @JoinColumn(name = "coordinate_id")
    private Coordinate coordinate;
    // getters and setters
}
