package br.pucrs.totem.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.pucrs.totem.dto.MapDto;
import br.pucrs.totem.service.MapService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/map")
@Tag(name = "Maps", description = "Endpoints for managing maps")
public class MapController {

    private final MapService mapService;

    public MapController(MapService mapService) {
        this.mapService = mapService;
    }

    @GetMapping()
    @Operation(summary = "Get map", description = "Retrieve the map object")
    public ResponseEntity<MapDto> getMap() {
        System.out.println("Fetching map");
        MapDto map = mapService.getMap();
        if (map == null) {
            System.out.println("Map not found");
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(map);
    }
}