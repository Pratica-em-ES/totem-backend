package br.pucrs.totem.service;

import br.pucrs.totem.entity.Map;
import br.pucrs.totem.dto.MapDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class MapService {

    @Autowired
    private MapRepository mapRepository;

    public List<MapDto> getAll() {
        return mapRepository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public Optional<MapDto> getById(UUID id) {
        return mapRepository.findById(id).map(this::toDto);
    }

    public MapDto toDto(Map entity) {
        // return new MapDto(entity.getId(), entity.getName(), entity.getImageUrl());
        throw new UnsupportedOperationException("Not implemented yet");
    }
}