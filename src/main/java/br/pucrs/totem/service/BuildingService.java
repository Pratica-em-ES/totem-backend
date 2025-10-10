package br.pucrs.totem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.pucrs.totem.entity.Building;
import br.pucrs.totem.entity.BuildingCompany;
import br.pucrs.totem.entity.Company;
import br.pucrs.totem.repository.BuildingRepository;
import br.pucrs.totem.repository.BuildingCompanyRepository;
import br.pucrs.totem.repository.CompanyRepository;

@Service
public class BuildingService {

    private final BuildingRepository buildingRepository;
    private final BuildingCompanyRepository buildingCompanyRepository;
    private final CompanyRepository companyRepository;

    public BuildingService(BuildingRepository buildingRepository, 
                          BuildingCompanyRepository buildingCompanyRepository,
                          CompanyRepository companyRepository) {
        this.buildingRepository = buildingRepository;
        this.buildingCompanyRepository = buildingCompanyRepository;
        this.companyRepository = companyRepository;
    }

    public List<Building> getAllBuildings() {
        return buildingRepository.findAll();
    }

    public Optional<Building> getBuildingById(Long id) {
        return buildingRepository.findById(id);
    }

    public Building saveBuilding(Building building) {
        return buildingRepository.save(building);
    }

    public Building updateBuilding(Long id, Building buildingDetails) {
        return buildingRepository.findById(id)
                .map(building -> {
                    building.setName(buildingDetails.getName());
                    building.setModelPath(buildingDetails.getModelPath());
                    building.setNode(buildingDetails.getNode());
                    return buildingRepository.save(building);
                })
                .orElse(null);
    }

    public boolean deleteBuilding(Long id) {
        if (buildingRepository.existsById(id)) {
            buildingRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<BuildingCompany> getBuildingCompanies(Long buildingId) {
        return buildingCompanyRepository.findByBuildingId(buildingId);
    }

    public BuildingCompany addCompanyToBuilding(Long buildingId, Long companyId, String localization) {
        Optional<Building> building = buildingRepository.findById(buildingId);
        Optional<Company> company = companyRepository.findById(companyId);
        if (building.isPresent() && company.isPresent()) {
            BuildingCompany buildingCompany = new BuildingCompany();
            buildingCompany.setBuilding(building.get());
            buildingCompany.setCompany(company.get());
            buildingCompany.setLocalization(localization);
            return buildingCompanyRepository.save(buildingCompany);
        }
        return null;
    }

    public BuildingCompany updateBuildingCompany(Long buildingCompanyId, String localization) {
        return buildingCompanyRepository.findById(buildingCompanyId)
                .map(buildingCompany -> {
                    buildingCompany.setLocalization(localization);
                    return buildingCompanyRepository.save(buildingCompany);
                })
                .orElse(null);
    }

    public boolean deleteBuildingCompany(Long buildingCompanyId) {
        if (buildingCompanyRepository.existsById(buildingCompanyId)) {
            buildingCompanyRepository.deleteById(buildingCompanyId);
            return true;
        }
        return false;
    }
}