package br.pucrs.totem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.pucrs.totem.entity.Building;
import br.pucrs.totem.entity.BuildingCompany;
import br.pucrs.totem.entity.BuildingStreet;
import br.pucrs.totem.repository.BuildingRepository;
import br.pucrs.totem.repository.BuildingCompanyRepository;
import br.pucrs.totem.repository.BuildingStreetRepository;

@Service
public class BuildingService {

    private final BuildingRepository buildingRepository;
    private final BuildingCompanyRepository buildingCompanyRepository;
    private final BuildingStreetRepository buildingStreetRepository;

    public BuildingService(BuildingRepository buildingRepository, 
                          BuildingCompanyRepository buildingCompanyRepository,
                          BuildingStreetRepository buildingStreetRepository) {
        this.buildingRepository = buildingRepository;
        this.buildingCompanyRepository = buildingCompanyRepository;
        this.buildingStreetRepository = buildingStreetRepository;
    }

    public List<Building> findAll() {
        return buildingRepository.findAll();
    }

    public Optional<Building> findById(Long id) {
        return buildingRepository.findById(id);
    }

    public Building save(Building building) {
        return buildingRepository.save(building);
    }

    public void deleteById(Long id) {
        buildingRepository.deleteById(id);
    }

    public boolean existsById(Long id) {
        return buildingRepository.existsById(id);
    }

    public List<BuildingCompany> findCompaniesByBuildingId(Long buildingId) {
        return buildingCompanyRepository.findByBuildingId(buildingId);
    }

    public BuildingCompany saveBuildingCompany(BuildingCompany buildingCompany) {
        return buildingCompanyRepository.save(buildingCompany);
    }

    public List<BuildingStreet> findStreetsByBuildingId(Long buildingId) {
        return buildingStreetRepository.findByBuildingId(buildingId);
    }

    public BuildingStreet saveBuildingStreet(BuildingStreet buildingStreet) {
        return buildingStreetRepository.save(buildingStreet);
    }
}