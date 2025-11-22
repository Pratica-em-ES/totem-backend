package br.pucrs.totem.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.pucrs.totem.dto.CompanyDTO;
import br.pucrs.totem.dto.CategoryDTO;
import br.pucrs.totem.entity.Company;
import br.pucrs.totem.service.CompanyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

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
    @Operation(summary = "Get company building", description = "Retrieve the building associated with a company")
    public ResponseEntity<CompanyDTO> getCompanyBuilding(@PathVariable Long id) {
        Optional<CompanyDTO> company = companyService.getCompanyDTOById(id);
        return company.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}/building/{buildingId}")
    @Operation(summary = "Set company building", description = "Associate a company with a building")
    public ResponseEntity<Void> setCompanyBuilding(
            @PathVariable Long id,
            @PathVariable Long buildingId) {
        boolean updated = companyService.setBuildingForCompany(id, buildingId);
        if (updated) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{id}/building")
    @Operation(summary = "Remove company building", description = "Remove the building association from a company")
    public ResponseEntity<Void> removeCompanyBuilding(@PathVariable Long id) {
        boolean deleted = companyService.removeBuildingFromCompany(id);
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

    @GetMapping("/{id}/categories")
    @Operation(summary = "Get company categories", description = "Retrieve all categories associated with a company")
    public ResponseEntity<List<CategoryDTO>> getCompanyCategories(@PathVariable Long id) {
        List<CategoryDTO> categories = companyService.getCompanyCategoriesDTO(id);
        return ResponseEntity.ok(categories);
    }

    @PostMapping("/{id}/categories/{categoryId}")
    @Operation(summary = "Add category to company", description = "Associate a category with a company")
    public ResponseEntity<Void> addCategoryToCompany(
            @PathVariable Long id,
            @PathVariable Long categoryId) {
        boolean added = companyService.addCategoryToCompany(id, categoryId);
        if (added) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{id}/categories/{categoryId}")
    @Operation(summary = "Remove category from company", description = "Remove the association between a company and a category")
    public ResponseEntity<Void> removeCategoryFromCompany(
            @PathVariable Long id,
            @PathVariable Long categoryId) {
        boolean removed = companyService.removeCategoryFromCompany(id, categoryId);
        if (removed) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}