package br.pucrs.totem.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.pucrs.totem.dto.BuildingDTO;
import br.pucrs.totem.dto.NodeDTO;
import br.pucrs.totem.dto.MapDTO;
import br.pucrs.totem.dto.StreetDTO;
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

        List<BuildingDTO> buildings = buildingsList.stream()
                .map(building -> new BuildingDTO(
                        building.getName(),
                        building.getModelPath(),
                        new NodeDTO(
                                building.getNode().getX(),
                                building.getNode().getY()
                        )))
                .toList();

        List<StreetDTO> streets = streetsList.stream()
                .map(street -> new StreetDTO(
                        street.getWidth(),
                        new NodeDTO(street.getNodeA().getX(), street.getNodeA().getY()),
                        new NodeDTO(street.getNodeB().getX(), street.getNodeB().getY())))
                .toList();

        return new MapDTO(buildings, streets);
    }
}