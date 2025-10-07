package br.pucrs.totem.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.pucrs.totem.entity.Coordinate;
import br.pucrs.totem.service.CoordinateService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/coordinates")
@Tag(name = "Coordinates", description = "Endpoints for managing coordinates")
public class CoordinateController {

    private final CoordinateService coordinateService;

    public CoordinateController(CoordinateService coordinateService) {
        this.coordinateService = coordinateService;
    }

    @GetMapping
    @Operation(summary = "Get all coordinates", description = "Retrieve a list of all coordinates")
    public ResponseEntity<List<Coordinate>> getAllCoordinates() {
        List<Coordinate> coordinates = coordinateService.getAllCoordinates();
        return ResponseEntity.ok(coordinates);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get coordinate by ID", description = "Retrieve a specific coordinate by its ID")
    public ResponseEntity<Coordinate> getCoordinateById(@PathVariable Long id) {
        Optional<Coordinate> coordinate = coordinateService.getCoordinateById(id);
        return coordinate.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Create coordinate", description = "Create a new coordinate")
    public ResponseEntity<Coordinate> createCoordinate(@RequestBody Coordinate coordinate) {
        Coordinate savedCoordinate = coordinateService.saveCoordinate(coordinate);
        return ResponseEntity.ok(savedCoordinate);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update coordinate", description = "Update an existing coordinate")
    public ResponseEntity<Coordinate> updateCoordinate(@PathVariable Long id, @RequestBody Coordinate coordinateDetails) {
        Coordinate updatedCoordinate = coordinateService.updateCoordinate(id, coordinateDetails);
        if (updatedCoordinate != null) {
            return ResponseEntity.ok(updatedCoordinate);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete coordinate", description = "Delete a coordinate by its ID")
    public ResponseEntity<Void> deleteCoordinate(@PathVariable Long id) {
        boolean deleted = coordinateService.deleteCoordinate(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}