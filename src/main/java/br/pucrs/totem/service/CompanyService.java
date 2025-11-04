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
import br.pucrs.totem.entity.Company;
import br.pucrs.totem.entity.Node;
import br.pucrs.totem.repository.BuildingCompanyRepository;
import br.pucrs.totem.repository.BuildingRepository;
import br.pucrs.totem.repository.CompanyRepository;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final BuildingCompanyRepository buildingCompanyRepository;
    private final BuildingRepository buildingRepository;

    public CompanyService(CompanyRepository companyRepository, 
                         BuildingCompanyRepository buildingCompanyRepository, 
                         BuildingRepository buildingRepository) {
        this.companyRepository = companyRepository;
        this.buildingCompanyRepository = buildingCompanyRepository;
        this.buildingRepository = buildingRepository;
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

    public boolean deleteCompany(Long id) {
        if (companyRepository.existsById(id)) {
            companyRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Building> getBuildingsByCompanyId(Long companyId) {
        return buildingCompanyRepository.findByCompanyId(companyId)
                .stream()
                .map(BuildingCompany::getBuilding)
                .toList();
    }

    public BuildingCompany addBuildingToCompany(Long companyId, Long buildingId, String localization) {
        Optional<Company> company = companyRepository.findById(companyId);
        Optional<Building> building = buildingRepository.findById(buildingId);
        if (company.isPresent() && building.isPresent()) {
            BuildingCompany buildingCompany = new BuildingCompany();
            buildingCompany.setCompany(company.get());
            buildingCompany.setBuilding(building.get());
            buildingCompany.setLocalization(localization);
            return buildingCompanyRepository.save(buildingCompany);
        }
        return null;
    }

    public BuildingCompany updateCompanyBuilding(Long buildingCompanyId, String localization) {
        return buildingCompanyRepository.findById(buildingCompanyId)
                .map(buildingCompany -> {
                    buildingCompany.setLocalization(localization);
                    return buildingCompanyRepository.save(buildingCompany);
                })
                .orElse(null);
    }

    public boolean deleteCompanyBuilding(Long buildingCompanyId) {
        if (buildingCompanyRepository.existsById(buildingCompanyId)) {
            buildingCompanyRepository.deleteById(buildingCompanyId);
            return true;
        }
        return false;
    }

    public List<BuildingCompany> getCompanyBuildings(Long companyId) {
        return buildingCompanyRepository.findByCompanyId(companyId);
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

        // Get the first building (assuming one company has one main building)
        BuildingWithNodeDTO buildingDTO = null;
        List<BuildingCompany> buildingCompanies = buildingCompanyRepository.findByCompanyId(company.getId());
        if (!buildingCompanies.isEmpty()) {
            Building building = buildingCompanies.get(0).getBuilding();
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
            company.getRoom(),
            company.getFloor(),
            buildingDTO,
            categoryDTOs
        );
    }

}