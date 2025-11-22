package br.pucrs.totem.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.pucrs.totem.dto.CompanyDTO;
import br.pucrs.totem.entity.Building;
import br.pucrs.totem.service.BuildingService;
import br.pucrs.totem.service.CompanyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/buildings")
@Tag(name = "Buildings", description = "Endpoints for managing buildings")
public class BuildingController {

    private final BuildingService buildingService;
    private final CompanyService companyService;

    public BuildingController(BuildingService buildingService, CompanyService companyService) {
        this.buildingService = buildingService;
        this.companyService = companyService;
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
    public ResponseEntity<List<CompanyDTO>> getBuildingCompanies(@PathVariable Long id) {
        List<CompanyDTO> companies = companyService.getCompaniesByBuildingId(id)
                .stream()
                .map(company -> companyService.getCompanyDTOById(company.getId()).get())
                .toList();
        return ResponseEntity.ok(companies);
    }
}