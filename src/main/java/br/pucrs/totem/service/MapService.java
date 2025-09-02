package br.pucrs.totem.service;

import br.pucrs.totem.dto.MapDTO;
import br.pucrs.totem.entity.Building;
import br.pucrs.totem.entity.Street;
import br.pucrs.totem.repository.BuildingRepository;
import br.pucrs.totem.repository.StreetRepository;
import br.pucrs.totem.dto.BuildingDTO;
import br.pucrs.totem.dto.CoordinateDTO;
import br.pucrs.totem.dto.StreetDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MapService {

    private final BuildingRepository buildingRepository;
    private final StreetRepository streetRepository;

    public MapService(BuildingRepository buildingRepository, StreetRepository streetRepository) {
        this.buildingRepository = buildingRepository;
        this.streetRepository = streetRepository;
    }
    
    public MapDTO getMap() {
        List<Building> buildingsList = buildingRepository.findAll();
        List<Street> streetsList = streetRepository.findAll();

        List<BuildingDTO> buildings = buildingsList.stream()
                .map(building -> new BuildingDTO(
                        building.getName(),
                        building.getModelPath(),
                        building.getCoordinate()))
                .toList();

        List<StreetDTO> streets = streetsList.stream()
                .map(street -> new StreetDTO(
                        street.getWidth(),
                        new CoordinateDTO(street.getCoordinateA().getX(), street.getCoordinateA().getY()),
                        new CoordinateDTO(street.getCoordinateB().getX(), street.getCoordinateB().getY())))
                .toList();

        return new MapDTO(buildings, streets);
    }
}