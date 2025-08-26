package br.pucrs.totem.dto;

public class RouteDto {
    private Long id;
    private String origin;
    private String destination;
    private String pathData;
    private Long mapId;

    public RouteDto() {}

    public RouteDto(Long id, String origin, String destination, String pathData, Long mapId) {
        this.id = id;
        this.origin = origin;
        this.destination = destination;
        this.pathData = pathData;
        this.mapId = mapId;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getOrigin() { return origin; }
    public void setOrigin(String origin) { this.origin = origin; }

    public String getDestination() { return destination; }
    public void setDestination(String destination) { this.destination = destination; }

    public String getPathData() { return pathData; }
    public void setPathData(String pathData) { this.pathData = pathData; }

    public Long getMapId() { return mapId; }
    public void setMapId(Long mapId) { this.mapId = mapId; }
}