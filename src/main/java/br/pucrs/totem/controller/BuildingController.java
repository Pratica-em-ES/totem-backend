package br.pucrs.totem.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.pucrs.totem.entity.Building;
import br.pucrs.totem.entity.BuildingStreet;
import br.pucrs.totem.entity.BuildingCompany;
import br.pucrs.totem.service.BuildingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/buildings")
@Tag(name = "Buildings", description = "Endpoints for managing buildings")
public class BuildingController {

    private final BuildingService buildingService;

    public BuildingController(BuildingService buildingService) {
        this.buildingService = buildingService;
    }

    @GetMapping
    @Operation(summary = "Get all buildings", description = "Retrieve all buildings")
    public ResponseEntity<List<Building>> getAllBuildings() {
        List<Building> buildings = buildingService.findAll();
        return ResponseEntity.ok(buildings);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get building by ID", description = "Retrieve a building by its ID")
    public ResponseEntity<Building> getBuildingById(@PathVariable Long id) {
        return buildingService.findById(id)
                .map(building -> ResponseEntity.ok(building))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Create building", description = "Create a new building")
    public ResponseEntity<Building> createBuilding(@RequestBody Building building) {
        Building savedBuilding = buildingService.save(building);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedBuilding);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update building", description = "Update an existing building")
    public ResponseEntity<Building> updateBuilding(@PathVariable Long id, @RequestBody Building building) {
        if (!buildingService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        building.setId(id);
        Building updatedBuilding = buildingService.save(building);
        return ResponseEntity.ok(updatedBuilding);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete building", description = "Delete a building by its ID")
    public ResponseEntity<Void> deleteBuilding(@PathVariable Long id) {
        if (!buildingService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        buildingService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/streets")
    @Operation(summary = "Get building streets", description = "Get all street connections for a building")
    public ResponseEntity<List<BuildingStreet>> getBuildingStreets(@PathVariable Long id) {
        if (!buildingService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        List<BuildingStreet> buildingStreets = buildingService.findStreetsByBuildingId(id);
        return ResponseEntity.ok(buildingStreets);
    }

    @PostMapping("/{id}/streets")
    @Operation(summary = "Add building street connection", description = "Add a street connection to a building")
    public ResponseEntity<BuildingStreet> addBuildingStreet(@PathVariable Long id, @RequestBody BuildingStreet buildingStreet) {
        return buildingService.findById(id)
                .map(building -> {
                    buildingStreet.setBuilding(building);
                    BuildingStreet savedBuildingStreet = buildingService.saveBuildingStreet(buildingStreet);
                    return ResponseEntity.status(HttpStatus.CREATED).body(savedBuildingStreet);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/companies")
    @Operation(summary = "Get building companies", description = "Get all company connections for a building")
    public ResponseEntity<List<BuildingCompany>> getBuildingCompanies(@PathVariable Long id) {
        if (!buildingService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        List<BuildingCompany> buildingCompanies = buildingService.findCompaniesByBuildingId(id);
        return ResponseEntity.ok(buildingCompanies);
    }

    @PostMapping("/{id}/companies")
    @Operation(summary = "Add building company connection", description = "Add a company connection to a building")
    public ResponseEntity<BuildingCompany> addBuildingCompany(@PathVariable Long id, @RequestBody BuildingCompany buildingCompany) {
        return buildingService.findById(id)
                .map(building -> {
                    buildingCompany.setBuilding(building);
                    BuildingCompany savedBuildingCompany = buildingService.saveBuildingCompany(buildingCompany);
                    return ResponseEntity.status(HttpStatus.CREATED).body(savedBuildingCompany);
                })
                .orElse(ResponseEntity.notFound().build());
    }
}