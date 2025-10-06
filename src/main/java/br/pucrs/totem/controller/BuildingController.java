package br.pucrs.totem.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.pucrs.totem.entity.Building;
import br.pucrs.totem.entity.BuildingCompany;
import br.pucrs.totem.entity.BuildingStreet;
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
    @Operation(summary = "Get all buildings", description = "Retrieve a list of all buildings")
    public ResponseEntity<List<Building>> getAllBuildings() {
        List<Building> buildings = buildingService.getAllBuildings();
        return ResponseEntity.ok(buildings);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get building by ID", description = "Retrieve a specific building by its ID")
    public ResponseEntity<Building> getBuildingById(@PathVariable Long id) {
        Optional<Building> building = buildingService.getBuildingById(id);
        return building.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Create building", description = "Create a new building")
    public ResponseEntity<Building> createBuilding(@RequestBody Building building) {
        Building savedBuilding = buildingService.saveBuilding(building);
        return ResponseEntity.ok(savedBuilding);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update building", description = "Update an existing building")
    public ResponseEntity<Building> updateBuilding(@PathVariable Long id, @RequestBody Building buildingDetails) {
        Building updatedBuilding = buildingService.updateBuilding(id, buildingDetails);
        if (updatedBuilding != null) {
            return ResponseEntity.ok(updatedBuilding);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete building", description = "Delete a building by its ID")
    public ResponseEntity<Void> deleteBuilding(@PathVariable Long id) {
        boolean deleted = buildingService.deleteBuilding(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}/companies")
    @Operation(summary = "Get building companies", description = "Retrieve all companies associated with a building")
    public ResponseEntity<List<BuildingCompany>> getBuildingCompanies(@PathVariable Long id) {
        List<BuildingCompany> companies = buildingService.getBuildingCompanies(id);
        return ResponseEntity.ok(companies);
    }

    @PostMapping("/{id}/companies")
    @Operation(summary = "Add company to building", description = "Associate a company with a building")
    public ResponseEntity<BuildingCompany> addCompanyToBuilding(
            @PathVariable Long id,
            @RequestParam Long companyId,
            @RequestParam(required = false) String localization) {
        BuildingCompany buildingCompany = buildingService.addCompanyToBuilding(id, companyId, localization);
        if (buildingCompany != null) {
            return ResponseEntity.ok(buildingCompany);
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/{id}/companies/{buildingCompanyId}")
    @Operation(summary = "Update building company", description = "Update the localization information for a building company association")
    public ResponseEntity<BuildingCompany> updateBuildingCompany(
            @PathVariable Long id,
            @PathVariable Long buildingCompanyId,
            @RequestParam String localization) {
        BuildingCompany updatedBuildingCompany = buildingService.updateBuildingCompany(buildingCompanyId, localization);
        if (updatedBuildingCompany != null) {
            return ResponseEntity.ok(updatedBuildingCompany);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}/companies/{buildingCompanyId}")
    @Operation(summary = "Remove company from building", description = "Remove the association between a building and a company")
    public ResponseEntity<Void> deleteBuildingCompany(
            @PathVariable Long id,
            @PathVariable Long buildingCompanyId) {
        boolean deleted = buildingService.deleteBuildingCompany(buildingCompanyId);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}/streets")
    @Operation(summary = "Get building streets", description = "Retrieve all streets associated with a building")
    public ResponseEntity<List<BuildingStreet>> getBuildingStreets(@PathVariable Long id) {
        List<BuildingStreet> streets = buildingService.getBuildingStreets(id);
        return ResponseEntity.ok(streets);
    }

    @PostMapping("/{id}/streets")
    @Operation(summary = "Add street to building", description = "Associate a street with a building")
    public ResponseEntity<BuildingStreet> addStreetToBuilding(
            @PathVariable Long id,
            @RequestParam Long streetId,
            @RequestParam(required = false) Long coordinateId) {
        BuildingStreet buildingStreet = buildingService.addStreetToBuilding(id, streetId, coordinateId);
        if (buildingStreet != null) {
            return ResponseEntity.ok(buildingStreet);
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/{id}/streets/{buildingStreetId}")
    @Operation(summary = "Update building street", description = "Update a building street association")
    public ResponseEntity<BuildingStreet> updateBuildingStreet(
            @PathVariable Long id,
            @PathVariable Long buildingStreetId) {
        BuildingStreet updatedBuildingStreet = buildingService.updateBuildingStreet(buildingStreetId);
        if (updatedBuildingStreet != null) {
            return ResponseEntity.ok(updatedBuildingStreet);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}/streets/{buildingStreetId}")
    @Operation(summary = "Remove street from building", description = "Remove the association between a building and a street")
    public ResponseEntity<Void> deleteBuildingStreet(
            @PathVariable Long id,
            @PathVariable Long buildingStreetId) {
        boolean deleted = buildingService.deleteBuildingStreet(buildingStreetId);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}