package br.pucrs.totem.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.pucrs.totem.dto.CompanyDTO;
import br.pucrs.totem.entity.BuildingCompany;
import br.pucrs.totem.entity.Company;
import br.pucrs.totem.service.CompanyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/companies")
@Tag(name = "Companies", description = "Endpoints for managing companies")
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    @Operation(summary = "Get all companies", description = "Retrieve a list of all companies")
    public ResponseEntity<List<CompanyDTO>> getAllCompanies() {
        List<CompanyDTO> companies = companyService.getAllCompaniesDTO();
        return ResponseEntity.ok(companies);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get company by ID", description = "Retrieve a specific company by its ID")
    public ResponseEntity<CompanyDTO> getCompanyById(@PathVariable Long id) {
        Optional<CompanyDTO> company = companyService.getCompanyDTOById(id);
        return company.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Create company", description = "Create a new company")
    public ResponseEntity<Company> createCompany(@RequestBody Company company) {
        Company savedCompany = companyService.saveCompany(company);
        return ResponseEntity.ok(savedCompany);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update company", description = "Update an existing company")
    public ResponseEntity<Company> updateCompany(@PathVariable Long id, @RequestBody Company companyDetails) {
        Company updatedCompany = companyService.updateCompany(id, companyDetails);
        if (updatedCompany != null) {
            return ResponseEntity.ok(updatedCompany);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete company", description = "Delete a company by its ID")
    public ResponseEntity<Void> deleteCompany(@PathVariable Long id) {
        boolean deleted = companyService.deleteCompany(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}/buildings")
    @Operation(summary = "Get company buildings", description = "Retrieve all buildings associated with a company")
    public ResponseEntity<List<BuildingCompany>> getCompanyBuildings(@PathVariable Long id) {
        List<BuildingCompany> buildings = companyService.getCompanyBuildings(id);
        return ResponseEntity.ok(buildings);
    }

    @PostMapping("/{id}/buildings")
    @Operation(summary = "Add building to company", description = "Associate a building with a company")
    public ResponseEntity<BuildingCompany> addBuildingToCompany(
            @PathVariable Long id,
            @RequestParam Long buildingId,
            @RequestParam(required = false) String localization) {
        BuildingCompany buildingCompany = companyService.addBuildingToCompany(id, buildingId, localization);
        if (buildingCompany != null) {
            return ResponseEntity.ok(buildingCompany);
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/{id}/buildings/{buildingCompanyId}")
    @Operation(summary = "Update company building", description = "Update the floors information for a company building association")
    public ResponseEntity<BuildingCompany> updateCompanyBuilding(
            @PathVariable Long id,
            @PathVariable Long buildingCompanyId,
            @RequestParam String localization) {
        BuildingCompany updatedBuildingCompany = companyService.updateCompanyBuilding(buildingCompanyId, localization);
        if (updatedBuildingCompany != null) {
            return ResponseEntity.ok(updatedBuildingCompany);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}/buildings/{buildingCompanyId}")
    @Operation(summary = "Remove building from company", description = "Remove the association between a company and a building")
    public ResponseEntity<Void> deleteCompanyBuilding(
            @PathVariable Long id,
            @PathVariable Long buildingCompanyId) {
        boolean deleted = companyService.deleteCompanyBuilding(buildingCompanyId);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}/image")
    @Operation(summary = "Get company image path", description = "Get the image path of a specific company")
    public ResponseEntity<String> getCompanyImagePath(@PathVariable Long id) {
        return companyService.getCompanyById(id)
                .map(company -> ResponseEntity.ok(company.getImagePath()))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}/image")
    @Operation(summary = "Update company image path", description = "Update the image path of a specific company")
    public ResponseEntity<Company> updateCompanyImagePath(
            @PathVariable Long id,
            @RequestParam String imagePath) {
        Company updatedCompany = companyService.updateCompanyImagePath(id, imagePath);
        if (updatedCompany != null) {
            return ResponseEntity.ok(updatedCompany);
        }
        return ResponseEntity.notFound().build();
    }
}