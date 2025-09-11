package br.pucrs.totem.controller;

import br.pucrs.totem.dto.MapDTO;
import br.pucrs.totem.service.MapService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@CrossOrigin(origins = "*")
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
    public ResponseEntity<MapDTO> getMap() {
        System.out.println("Fetching map");
        MapDTO map = mapService.getMap();
        if (map == null) {
            System.out.println("Map not found");
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(map);
    }
}