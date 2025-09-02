package br.pucrs.totem.service;

import br.pucrs.totem.dto.MapDTO;
import br.pucrs.totem.entity_old.MapEntity;

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
        List<BuildingEntity> buildings_list = buildingRepository.findAll();
        List<StreetEntity> streets_list = streetRepository.findAll();

        List<BuildingDTO> buildings = buildings_list.stream()
                .map(building -> new BuildingDTO(building.getId(), building.getName(), building.getCoordinate(), building.getModelPath()))
                .collect(Collectors.toList());

        List<StreetDTO> streets = streets_list.stream()
                .map(street -> new StreetDTO(street.getId(), street.getName(), street.getImageUrl()))
                .collect(Collectors.toList());

        return new MapDTO(buildings, streets);
    }

        return new MapModel(buildings, streets);
    }

    public Optional<MapDTO> getMapById(Long id) {
        return mapRepository.findById(id).map(this::toDto);
    }

    public MapDTO toDto(MapEntity entity) {
        return new MapDTO(entity.getId(), entity.getName(), entity.getImageUrl());
    }
}