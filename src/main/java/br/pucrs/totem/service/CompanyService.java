package br.pucrs.totem.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.pucrs.totem.dto.BuildingWithNodeDTO;
import br.pucrs.totem.dto.CategoryDTO;
import br.pucrs.totem.dto.CompanyDTO;
import br.pucrs.totem.dto.NodeDTO;
import br.pucrs.totem.entity.Building;
import br.pucrs.totem.entity.BuildingCompany;
import br.pucrs.totem.entity.Category;
import br.pucrs.totem.entity.CategoryCompany;
import br.pucrs.totem.entity.Company;
import br.pucrs.totem.entity.Node;
import br.pucrs.totem.repository.BuildingCompanyRepository;
import br.pucrs.totem.repository.BuildingRepository;
import br.pucrs.totem.repository.CategoryCompanyRepository;
import br.pucrs.totem.repository.CategoryRepository;
import br.pucrs.totem.repository.CompanyRepository;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final BuildingRepository buildingRepository;
    private final CategoryRepository categoryRepository;
    private final CategoryCompanyRepository categoryCompanyRepository;
    private final BuildingCompanyRepository buildingCompanyRepository;

    public CompanyService(CompanyRepository companyRepository, 
                         BuildingRepository buildingRepository,
                         CategoryRepository categoryRepository,
                         CategoryCompanyRepository categoryCompanyRepository,
                         BuildingCompanyRepository buildingCompanyRepository) {
        this.companyRepository = companyRepository;
        this.buildingRepository = buildingRepository;
        this.categoryRepository = categoryRepository;
        this.categoryCompanyRepository = categoryCompanyRepository;
        this.buildingCompanyRepository = buildingCompanyRepository;
    }

    public List<Company> getAllCompanies() {
        return companyRepository.findAllOrderByNameAsc();
    }

    public Optional<Company> getCompanyById(Long id) {
        return companyRepository.findById(id);
    }

    public Company saveCompany(Company company) {
        return companyRepository.save(company);
    }

    public Company updateCompany(Long id, Company companyDetails) {
        return companyRepository.findById(id)
                .map(company -> {
                    company.setName(companyDetails.getName());
                    company.setDescription(companyDetails.getDescription());
                    company.setBuilding(companyDetails.getBuilding());
                    company.setBlock(companyDetails.getBlock());
                    company.setRoom(companyDetails.getRoom());
                    company.setFloor(companyDetails.getFloor());
                    company.setImagePath(companyDetails.getImagePath());
                    return companyRepository.save(company);
                })
                .orElse(null);
    }

    public Company updateCompanyImagePath(Long id, String imagePath) {
        return companyRepository.findById(id)
                .map(company -> {
                    company.setImagePath(imagePath);
                    return companyRepository.save(company);
                })
                .orElse(null);
    }

    @Transactional
    public boolean deleteCompany(Long id) {
        Optional<Company> company = companyRepository.findById(id);
        if (company.isPresent()) {
            Company companyToDelete = company.get();
            
            // Remove all building associations first
            List<BuildingCompany> buildingCompanies = companyToDelete.getBuildingCompanies();
            if (buildingCompanies != null && !buildingCompanies.isEmpty()) {
                buildingCompanies.clear();
            }
            
            // Remove all category associations
            List<CategoryCompany> categories = companyToDelete.getCategories();
            if (categories != null && !categories.isEmpty()) {
                categories.clear();
            }
            
            // Delete the company
            companyRepository.delete(companyToDelete);
            return true;
        }
        return false;
    }

    public List<Building> getBuildingsByCompanyId(Long companyId) {
        Optional<Company> company = companyRepository.findById(companyId);
        if (company.isPresent() && company.get().getBuilding() != null) {
            return List.of(company.get().getBuilding());
        }
        return List.of();
    }

    public List<Company> getCompaniesByBuildingId(Long buildingId) {
        return companyRepository.findByBuildingId(buildingId);
    }

    public boolean setBuildingForCompany(Long companyId, Long buildingId) {
        Optional<Company> company = companyRepository.findById(companyId);
        Optional<Building> building = buildingRepository.findById(buildingId);
        if (company.isPresent() && building.isPresent()) {
            company.get().setBuilding(building.get());
            companyRepository.save(company.get());
            return true;
        }
        return false;
    }

    public boolean removeBuildingFromCompany(Long companyId) {
        Optional<Company> company = companyRepository.findById(companyId);
        if (company.isPresent()) {
            company.get().setBuilding(null);
            companyRepository.save(company.get());
            return true;
        }
        return false;
    }

    @Transactional(readOnly = true)
    public List<CompanyDTO> getAllCompaniesDTO() {
        List<Company> companies = companyRepository.findAllOrderByNameAsc();
        return companies.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Optional<CompanyDTO> getCompanyDTOById(Long id) {
        return companyRepository.findById(id)
                .map(this::convertToDTO);
    }

    private CompanyDTO convertToDTO(Company company) {
        // Convert categories
        List<CategoryDTO> categoryDTOs = company.getCategories() != null
            ? company.getCategories().stream()
                .map(cc -> new CategoryDTO(cc.getCategory().getId(), cc.getCategory().getName()))
                .collect(Collectors.toList())
            : List.of();

        // Get the building for this company
        BuildingWithNodeDTO buildingDTO = null;
        if (company.getBuilding() != null) {
            Building building = company.getBuilding();
            Node node = building.getNode();

            NodeDTO nodeDTO = node != null
                ? new NodeDTO(node.getId(), node.getX(), node.getY())
                : null;

            buildingDTO = new BuildingWithNodeDTO(
                building.getId(),
                building.getName(),
                building.getModelPath(),
                nodeDTO
            );
        }

        return new CompanyDTO(
            company.getId(),
            company.getName(),
            company.getDescription(),
            company.getImagePath(),
            company.getBlock(),
            company.getRoom(),
            company.getFloor(),
            buildingDTO,
            categoryDTOs
        );
    }

    @Transactional(readOnly = true)
    public List<CategoryDTO> getCompanyCategoriesDTO(Long companyId) {
        List<CategoryCompany> categoryCompanies = categoryCompanyRepository.findByCompanyId(companyId);
        return categoryCompanies.stream()
                .map(cc -> new CategoryDTO(cc.getCategory().getId(), cc.getCategory().getName()))
                .collect(Collectors.toList());
    }

    @Transactional
    public boolean addCategoryToCompany(Long companyId, Long categoryId) {
        Optional<Company> company = companyRepository.findById(companyId);
        Optional<Category> category = categoryRepository.findById(categoryId);
        
        if (company.isPresent() && category.isPresent()) {
            // Check if the association already exists
            Optional<CategoryCompany> existingAssociation = categoryCompanyRepository.findByCompanyIdAndCategoryId(companyId, categoryId);
            
            if (existingAssociation.isEmpty()) {
                CategoryCompany categoryCompany = new CategoryCompany(category.get(), company.get());
                categoryCompanyRepository.save(categoryCompany);
            }
            return true;
        }
        return false;
    }

    @Transactional
    public boolean removeCategoryFromCompany(Long companyId, Long categoryId) {
        Optional<CategoryCompany> categoryCompany = categoryCompanyRepository.findByCompanyIdAndCategoryId(companyId, categoryId);
        
        if (categoryCompany.isPresent()) {
            categoryCompanyRepository.delete(categoryCompany.get());
            return true;
        }
        return false;
    }

}