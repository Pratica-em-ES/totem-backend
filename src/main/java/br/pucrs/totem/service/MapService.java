package br.pucrs.totem.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.pucrs.totem.dto.MapBuildingDTO;
import br.pucrs.totem.dto.MapCoordinateDTO;
import br.pucrs.totem.dto.MapDTO;
import br.pucrs.totem.dto.MapStreetDTO;
import br.pucrs.totem.entity.Building;
import br.pucrs.totem.entity.Street;
import br.pucrs.totem.repository.BuildingRepository;
import br.pucrs.totem.repository.StreetRepository;

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

        List<MapBuildingDTO> buildings = buildingsList.stream()
                .map(building -> new MapBuildingDTO(
                        building.getName(),
                        building.getModelPath(),
                        new MapCoordinateDTO(
                                building.getCoordinate().getX(),
                                building.getCoordinate().getY()
                        )))
                .toList();

        List<MapStreetDTO> streets = streetsList.stream()
                .map(street -> new MapStreetDTO(
                        street.getWidth(),
                        new MapCoordinateDTO(street.getCoordinateA().getX(), street.getCoordinateA().getY()),
                        new MapCoordinateDTO(street.getCoordinateB().getX(), street.getCoordinateB().getY())))
                .toList();

        return new MapDTO(buildings, streets);
    }
}