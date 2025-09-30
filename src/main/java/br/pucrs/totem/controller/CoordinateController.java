package br.pucrs.totem.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
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
    @Operation(summary = "Get all coordinates", description = "Retrieve all coordinates")
    public ResponseEntity<List<Coordinate>> getAllCoordinates() {
        List<Coordinate> coordinates = coordinateService.findAll();
        return ResponseEntity.ok(coordinates);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get coordinate by ID", description = "Retrieve a coordinate by its ID")
    public ResponseEntity<Coordinate> getCoordinateById(@PathVariable Long id) {
        return coordinateService.findById(id)
                .map(coordinate -> ResponseEntity.ok(coordinate))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Create coordinate", description = "Create a new coordinate")
    public ResponseEntity<Coordinate> createCoordinate(@RequestBody Coordinate coordinate) {
        Coordinate savedCoordinate = coordinateService.save(coordinate);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCoordinate);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update coordinate", description = "Update an existing coordinate")
    public ResponseEntity<Coordinate> updateCoordinate(@PathVariable Long id, @RequestBody Coordinate coordinate) {
        if (!coordinateService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        coordinate.setId(id);
        Coordinate updatedCoordinate = coordinateService.save(coordinate);
        return ResponseEntity.ok(updatedCoordinate);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete coordinate", description = "Delete a coordinate by its ID")
    public ResponseEntity<Void> deleteCoordinate(@PathVariable Long id) {
        if (!coordinateService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        coordinateService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}