package br.pucrs.totem.entity;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "routes")
public class RouteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String origin;

    @Column(nullable = false)
    private String destination;

    @Column(nullable = false, length = 2048)
    private String pathData; // Pode ser um JSON ou string representando a rota

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "map_id")
    private MapEntity map;

    public RouteEntity() {}

    public RouteEntity(Long id, String origin, String destination, String pathData, MapEntity map) {
        this.id = id;
        this.origin = origin;
        this.destination = destination;
        this.pathData = pathData;
        this.map = map;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getOrigin() { return origin; }
    public void setOrigin(String origin) { this.origin = origin; }

    public String getDestination() { return destination; }
    public void setDestination(String destination) { this.destination = destination; }

    public String getPathData() { return pathData; }
    public void setPathData(String pathData) { this.pathData = pathData; }

    public MapEntity getMap() { return map; }
    public void setMap(MapEntity map) { this.map = map; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RouteEntity route = (RouteEntity) o;
        return Objects.equals(id, route.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}