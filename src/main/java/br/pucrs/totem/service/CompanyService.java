package br.pucrs.totem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.pucrs.totem.entity.Building;
import br.pucrs.totem.entity.BuildingCompany;
import br.pucrs.totem.entity.Company;
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
        return companyRepository.findAll();
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
                    company.setCategory(companyDetails.getCategory());
                    company.setDescription(companyDetails.getDescription());
                    company.setBuilding(companyDetails.getBuilding());
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
}