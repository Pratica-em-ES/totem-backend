    @ManyToOne
    @JoinColumn(name = "map_id")
    private Map map;
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
    // getters and setters
}
