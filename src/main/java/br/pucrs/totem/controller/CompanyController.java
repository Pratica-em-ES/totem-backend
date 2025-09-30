package br.pucrs.totem.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.pucrs.totem.entity.Company;
import br.pucrs.totem.entity.BuildingCompany;
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
    @Operation(summary = "Get all companies", description = "Retrieve all companies")
    public ResponseEntity<List<Company>> getAllCompanies() {
        List<Company> companies = companyService.findAll();
        return ResponseEntity.ok(companies);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get company by ID", description = "Retrieve a company by its ID")
    public ResponseEntity<Company> getCompanyById(@PathVariable Long id) {
        return companyService.findById(id)
                .map(company -> ResponseEntity.ok(company))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Create company", description = "Create a new company")
    public ResponseEntity<Company> createCompany(@RequestBody Company company) {
        Company savedCompany = companyService.save(company);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCompany);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update company", description = "Update an existing company")
    public ResponseEntity<Company> updateCompany(@PathVariable Long id, @RequestBody Company company) {
        if (!companyService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        company.setId(id);
        Company updatedCompany = companyService.save(company);
        return ResponseEntity.ok(updatedCompany);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete company", description = "Delete a company by its ID")
    public ResponseEntity<Void> deleteCompany(@PathVariable Long id) {
        if (!companyService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        companyService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/buildings")
    @Operation(summary = "Get company buildings", description = "Get all building connections for a company")
    public ResponseEntity<List<BuildingCompany>> getCompanyBuildings(@PathVariable Long id) {
        if (!companyService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        List<BuildingCompany> buildingCompanies = companyService.findBuildingsByCompanyId(id);
        return ResponseEntity.ok(buildingCompanies);
    }

    @PostMapping("/{id}/buildings")
    @Operation(summary = "Add company building connection", description = "Add a building connection to a company")
    public ResponseEntity<BuildingCompany> addCompanyBuilding(@PathVariable Long id, @RequestBody BuildingCompany buildingCompany) {
        return companyService.findById(id)
                .map(company -> {
                    buildingCompany.setCompany(company);
                    BuildingCompany savedBuildingCompany = companyService.saveBuildingCompany(buildingCompany);
                    return ResponseEntity.status(HttpStatus.CREATED).body(savedBuildingCompany);
                })
                .orElse(ResponseEntity.notFound().build());
    }
}