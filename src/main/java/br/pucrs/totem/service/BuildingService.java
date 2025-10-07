package br.pucrs.totem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.pucrs.totem.entity.Building;
import br.pucrs.totem.entity.BuildingCompany;
import br.pucrs.totem.entity.BuildingStreet;
import br.pucrs.totem.entity.Company;
import br.pucrs.totem.entity.Coordinate;
import br.pucrs.totem.entity.Street;
import br.pucrs.totem.repository.BuildingRepository;
import br.pucrs.totem.repository.BuildingCompanyRepository;
import br.pucrs.totem.repository.BuildingStreetRepository;
import br.pucrs.totem.repository.CompanyRepository;
import br.pucrs.totem.repository.StreetRepository;
import br.pucrs.totem.repository.CoordinateRepository;

@Service
public class BuildingService {

    private final BuildingRepository buildingRepository;
    private final BuildingCompanyRepository buildingCompanyRepository;
    private final BuildingStreetRepository buildingStreetRepository;
    private final CompanyRepository companyRepository;
    private final StreetRepository streetRepository;
    private final CoordinateRepository coordinateRepository;

    public BuildingService(BuildingRepository buildingRepository, 
                          BuildingCompanyRepository buildingCompanyRepository,
                          BuildingStreetRepository buildingStreetRepository,
                          CompanyRepository companyRepository,
                          StreetRepository streetRepository,
                          CoordinateRepository coordinateRepository) {
        this.buildingRepository = buildingRepository;
        this.buildingCompanyRepository = buildingCompanyRepository;
        this.buildingStreetRepository = buildingStreetRepository;
        this.companyRepository = companyRepository;
        this.streetRepository = streetRepository;
        this.coordinateRepository = coordinateRepository;
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
                    building.setCoordinate(buildingDetails.getCoordinate());
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

    public List<BuildingStreet> getBuildingStreets(Long buildingId) {
        return buildingStreetRepository.findByBuildingId(buildingId);
    }

    public BuildingStreet addStreetToBuilding(Long buildingId, Long streetId, Long coordinateId) {
        Optional<Building> building = buildingRepository.findById(buildingId);
        Optional<Street> street = streetRepository.findById(streetId);
        Optional<Coordinate> coordinate = coordinateRepository.findById(coordinateId);
        if (building.isPresent() && street.isPresent() && coordinate.isPresent()) {
            BuildingStreet buildingStreet = new BuildingStreet();
            buildingStreet.setBuilding(building.get());
            buildingStreet.setStreet(street.get());
            buildingStreet.setCoordinate(coordinate.get());
            return buildingStreetRepository.save(buildingStreet);
        }
        return null;
    }

    public BuildingStreet updateBuildingStreet(Long buildingStreetId) {
        return buildingStreetRepository.findById(buildingStreetId)
                .map(buildingStreetRepository::save)
                .orElse(null);
    }

    public boolean deleteBuildingStreet(Long buildingStreetId) {
        if (buildingStreetRepository.existsById(buildingStreetId)) {
            buildingStreetRepository.deleteById(buildingStreetId);
            return true;
        }
        return false;
    }
}