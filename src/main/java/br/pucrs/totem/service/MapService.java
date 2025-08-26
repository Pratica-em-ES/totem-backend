package br.pucrs.totem.service;

import br.pucrs.totem.entity.MapEntity;
import br.pucrs.totem.dto.MapDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MapService {

    @Autowired
    private MapRepository mapRepository;

    public List<MapDto> getAllMaps() {
        return mapRepository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public Optional<MapDto> getMapById(Long id) {
        return mapRepository.findById(id).map(this::toDto);
    }

    public MapDto toDto(MapEntity entity) {
        return new MapDto(entity.getId(), entity.getName(), entity.getImageUrl());
    }
}