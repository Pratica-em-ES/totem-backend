package br.pucrs.totem.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.pucrs.totem.dto.BuildingDTO;
import br.pucrs.totem.dto.NodeDTO;
import br.pucrs.totem.dto.MapDTO;
import br.pucrs.totem.dto.EdgeDTO;
import br.pucrs.totem.entity.Building;
import br.pucrs.totem.entity.Edge;
import br.pucrs.totem.repository.BuildingRepository;
import br.pucrs.totem.repository.EdgeRepository;

@Service
public class MapService {

    private final BuildingRepository buildingRepository;
    private final EdgeRepository edgeRepository;

    public MapService(BuildingRepository buildingRepository, EdgeRepository edgeRepository) {
        this.buildingRepository = buildingRepository;
        this.edgeRepository = edgeRepository;
    }
    
    public MapDTO getMap() {
        List<Building> buildingsList = buildingRepository.findAll();
        List<Edge> edgesList = edgeRepository.findAll();

        List<BuildingDTO> buildings = buildingsList.stream()
                .map(building -> new BuildingDTO(
                        building.getName(),
                        building.getModelPath(),
                        new NodeDTO(
                                building.getNode().getX(),
                                building.getNode().getY()
                        )))
                .toList();

        List<EdgeDTO> edges = edgesList.stream()
                .map(edge -> new EdgeDTO(
                        edge.getWidth(),
                        new NodeDTO(edge.getNodeA().getX(), edge.getNodeA().getY()),
                        new NodeDTO(edge.getNodeB().getX(), edge.getNodeB().getY())))
                .toList();

        return new MapDTO(buildings, edges);
    }
}