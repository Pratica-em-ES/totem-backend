package br.pucrs.totem.controller;

import br.pucrs.totem.dto.MapDTO;
import br.pucrs.totem.entity_old.MapModel;
import br.pucrs.totem.repository.MapRepository;
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
        MapModel map = mapService.getMap();
        if (map == null) {
            System.out.println("Map not found");
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(MapDTO.fromModel(map));
        }

        System.out.println("Map found: " + map.getID());
        MapDTO response = new MapDTO();
        response.setId(map.getID());
        response.setBuildings(map.getBuildings());
        response.setStreets(map.getStreets());

        return ResponseEntity.ok(response);
    }
}