package br.pucrs.totem.controller;

import br.pucrs.totem.dto.MapDTO;
import br.pucrs.totem.repository.BuildingRepository;
import br.pucrs.totem.service.MapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/map")
public class MapController {

    private final MapService mapService;

    public MapController(MapService mapService) {
        this.mapService = mapService;
    }

    @GetMapping()
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