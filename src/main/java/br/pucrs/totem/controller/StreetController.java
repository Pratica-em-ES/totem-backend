package br.pucrs.totem.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
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
    @Operation(summary = "Get all streets", description = "Retrieve all streets")
    public ResponseEntity<List<Street>> getAllStreets() {
        List<Street> streets = streetService.findAll();
        return ResponseEntity.ok(streets);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get street by ID", description = "Retrieve a street by its ID")
    public ResponseEntity<Street> getStreetById(@PathVariable Long id) {
        return streetService.findById(id)
                .map(street -> ResponseEntity.ok(street))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Create street", description = "Create a new street")
    public ResponseEntity<Street> createStreet(@RequestBody Street street) {
        Street savedStreet = streetService.save(street);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedStreet);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update street", description = "Update an existing street")
    public ResponseEntity<Street> updateStreet(@PathVariable Long id, @RequestBody Street street) {
        if (!streetService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        street.setId(id);
        Street updatedStreet = streetService.save(street);
        return ResponseEntity.ok(updatedStreet);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete street", description = "Delete a street by its ID")
    public ResponseEntity<Void> deleteStreet(@PathVariable Long id) {
        if (!streetService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        streetService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/buildings")
    @Operation(summary = "Get street buildings", description = "Get all building connections for a street")
    public ResponseEntity<List<BuildingStreet>> getStreetBuildings(@PathVariable Long id) {
        if (!streetService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        List<BuildingStreet> buildingStreets = streetService.findBuildingsByStreetId(id);
        return ResponseEntity.ok(buildingStreets);
    }

    @PostMapping("/{id}/buildings")
    @Operation(summary = "Add street building connection", description = "Add a building connection to a street")
    public ResponseEntity<BuildingStreet> addStreetBuilding(@PathVariable Long id, @RequestBody BuildingStreet buildingStreet) {
        return streetService.findById(id)
                .map(street -> {
                    buildingStreet.setStreet(street);
                    BuildingStreet savedBuildingStreet = streetService.saveBuildingStreet(buildingStreet);
                    return ResponseEntity.status(HttpStatus.CREATED).body(savedBuildingStreet);
                })
                .orElse(ResponseEntity.notFound().build());
    }
}