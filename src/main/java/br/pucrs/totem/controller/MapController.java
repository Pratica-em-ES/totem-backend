package br.pucrs.totem.controller;

import br.pucrs.totem.dto.MapDto;
import br.pucrs.totem.service.MapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/maps")
public class MapController {

    @Autowired
    private MapService mapService;

    @GetMapping
    public List<MapDto> getAllMaps() {
        return mapService.getAllMaps();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MapDto> getMapById(@PathVariable Long id) {
        return mapService.getMapById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}