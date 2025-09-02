package br.pucrs.totem.controller;

import br.pucrs.totem.dto.MapDto;
import br.pucrs.totem.service.MapService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/maps")
@Tag(name = "Maps", description = "Endpoints for managing maps")
public class Maps {

    @Autowired
    private MapService mapService;

    @GetMapping
    @Operation(summary = "Get all maps", description = "Retrieve a list of all maps")
    public ResponseEntity<List<MapDto>> getAll() {
        // return ResponseEntity.ok(mapService.getAll());
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get map by ID", description = "Retrieve a specific map by its ID")
    public ResponseEntity<MapDto> getById(@PathVariable UUID id) {
        return mapService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/maps")
    @Operation(summary = "Create a new map", description = "Create a new map with the provided details")
    public ResponseEntity<MapDto> create(@RequestBody MapDto createMapDto) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @PatchMapping("/maps/{id}")
    @Operation(summary = "Update an existing map", description = "Update the details of an existing map by its ID")
    public String update(@PathVariable Long id, @RequestBody Object updateMapDto) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @DeleteMapping("/maps/{id}")
    @Operation(summary = "Delete a map", description = "Delete a specific map by its ID")
    public String delete(@PathVariable Long id) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}