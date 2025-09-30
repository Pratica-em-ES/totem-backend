package br.pucrs.totem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.pucrs.totem.entity.Street;
import br.pucrs.totem.entity.BuildingStreet;
import br.pucrs.totem.repository.StreetRepository;
import br.pucrs.totem.repository.BuildingStreetRepository;

@Service
public class StreetService {

    private final StreetRepository streetRepository;
    private final BuildingStreetRepository buildingStreetRepository;

    public StreetService(StreetRepository streetRepository, BuildingStreetRepository buildingStreetRepository) {
        this.streetRepository = streetRepository;
        this.buildingStreetRepository = buildingStreetRepository;
    }

    public List<Street> findAll() {
        return streetRepository.findAll();
    }

    public Optional<Street> findById(Long id) {
        return streetRepository.findById(id);
    }

    public Street save(Street street) {
        return streetRepository.save(street);
    }

    public void deleteById(Long id) {
        streetRepository.deleteById(id);
    }

    public boolean existsById(Long id) {
        return streetRepository.existsById(id);
    }

    public List<BuildingStreet> findBuildingsByStreetId(Long streetId) {
        return buildingStreetRepository.findByStreetId(streetId);
    }

    public BuildingStreet saveBuildingStreet(BuildingStreet buildingStreet) {
        return buildingStreetRepository.save(buildingStreet);
    }
}