package br.pucrs.totem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.pucrs.totem.entity.Street;
import br.pucrs.totem.entity.BuildingStreet;
import br.pucrs.totem.entity.Building;
import br.pucrs.totem.repository.StreetRepository;
import br.pucrs.totem.repository.BuildingStreetRepository;
import br.pucrs.totem.repository.BuildingRepository;

@Service
public class StreetService {

    private final StreetRepository streetRepository;
    private final BuildingStreetRepository buildingStreetRepository;
    private final BuildingRepository buildingRepository;

    public StreetService(StreetRepository streetRepository, 
                        BuildingStreetRepository buildingStreetRepository,
                        BuildingRepository buildingRepository) {
        this.streetRepository = streetRepository;
        this.buildingStreetRepository = buildingStreetRepository;
        this.buildingRepository = buildingRepository;
    }

    public List<Street> getAllStreets() {
        return streetRepository.findAll();
    }

    public Optional<Street> getStreetById(Long id) {
        return streetRepository.findById(id);
    }

    public Street saveStreet(Street street) {
        return streetRepository.save(street);
    }

    public Street updateStreet(Long id, Street streetDetails) {
        return streetRepository.findById(id)
                .map(street -> {
                    street.setWidth(streetDetails.getWidth());
                    street.setNodeA(streetDetails.getNodeA());
                    street.setNodeB(streetDetails.getNodeB());
                    return streetRepository.save(street);
                })
                .orElse(null);
    }

    public boolean deleteStreet(Long id) {
        if (streetRepository.existsById(id)) {
            streetRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<BuildingStreet> getStreetBuildings(Long streetId) {
        return buildingStreetRepository.findByStreetId(streetId);
    }

    public BuildingStreet addBuildingToStreet(Long streetId, Long buildingId) {
        Optional<Street> street = streetRepository.findById(streetId);
        Optional<Building> building = buildingRepository.findById(buildingId);
        if (street.isPresent() && building.isPresent()) {
            BuildingStreet buildingStreet = new BuildingStreet();
            buildingStreet.setStreet(street.get());
            buildingStreet.setBuilding(building.get());
            return buildingStreetRepository.save(buildingStreet);
        }
        return null;
    }

    public boolean deleteStreetBuilding(Long buildingStreetId) {
        if (buildingStreetRepository.existsById(buildingStreetId)) {
            buildingStreetRepository.deleteById(buildingStreetId);
            return true;
        }
        return false;
    }
}