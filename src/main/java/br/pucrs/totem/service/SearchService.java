package br.pucrs.totem.service;

import br.pucrs.totem.entity.MapEntity;
import br.pucrs.totem.service.MapRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SearchService {

    @Autowired
    private MapRepository mapRepository;

    public List<MapEntity> searchMapsByName(String query) {
        return mapRepository.findAll().stream()
                .filter(map -> map.getName().toLowerCase().contains(query.toLowerCase()))
                .collect(Collectors.toList());
    }
}