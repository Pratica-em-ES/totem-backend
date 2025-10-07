package br.pucrs.totem.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.pucrs.totem.entity.Street;
import br.pucrs.totem.entity.BuildingStreet;
import br.pucrs.totem.service.StreetService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/streets")
@Tag(name = "Streets", description = "Endpoints for managing streets")
public class StreetController {

    private final StreetService streetService;

    public StreetController(StreetService streetService) {
        this.streetService = streetService;
    }

    @GetMapping
    @Operation(summary = "Get all streets", description = "Retrieve a list of all streets")
    public ResponseEntity<List<Street>> getAllStreets() {
        List<Street> streets = streetService.getAllStreets();
        return ResponseEntity.ok(streets);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get street by ID", description = "Retrieve a specific street by its ID")
    public ResponseEntity<Street> getStreetById(@PathVariable Long id) {
        Optional<Street> street = streetService.getStreetById(id);
        return street.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Create street", description = "Create a new street")
    public ResponseEntity<Street> createStreet(@RequestBody Street street) {
        Street savedStreet = streetService.saveStreet(street);
        return ResponseEntity.ok(savedStreet);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update street", description = "Update an existing street")
    public ResponseEntity<Street> updateStreet(@PathVariable Long id, @RequestBody Street streetDetails) {
        Street updatedStreet = streetService.updateStreet(id, streetDetails);
        if (updatedStreet != null) {
            return ResponseEntity.ok(updatedStreet);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete street", description = "Delete a street by its ID")
    public ResponseEntity<Void> deleteStreet(@PathVariable Long id) {
        boolean deleted = streetService.deleteStreet(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}/buildings")
    @Operation(summary = "Get street buildings", description = "Retrieve all buildings associated with a street")
    public ResponseEntity<List<BuildingStreet>> getStreetBuildings(@PathVariable Long id) {
        List<BuildingStreet> buildings = streetService.getStreetBuildings(id);
        return ResponseEntity.ok(buildings);
    }

    @PostMapping("/{id}/buildings")
    @Operation(summary = "Add building to street", description = "Associate a building with a street")
    public ResponseEntity<BuildingStreet> addBuildingToStreet(
            @PathVariable Long id,
            @RequestParam Long buildingId) {
        BuildingStreet buildingStreet = streetService.addBuildingToStreet(id, buildingId);
        if (buildingStreet != null) {
            return ResponseEntity.ok(buildingStreet);
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{id}/buildings/{buildingStreetId}")
    @Operation(summary = "Remove building from street", description = "Remove the association between a street and a building")
    public ResponseEntity<Void> deleteStreetBuilding(
            @PathVariable Long id,
            @PathVariable Long buildingStreetId) {
        boolean deleted = streetService.deleteStreetBuilding(buildingStreetId);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}