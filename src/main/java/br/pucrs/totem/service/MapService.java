package br.pucrs.totem.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.pucrs.totem.dto.BuildingDTO;
import br.pucrs.totem.dto.NodeDTO;
import br.pucrs.totem.dto.MapDTO;
import br.pucrs.totem.dto.EdgeDTO;
import br.pucrs.totem.entity.Building;
import br.pucrs.totem.entity.Edge;
import br.pucrs.totem.entity.Node;
import br.pucrs.totem.repository.BuildingRepository;
import br.pucrs.totem.repository.EdgeRepository;
import br.pucrs.totem.repository.NodeRepository;

@Service
public class MapService {

    private final BuildingRepository buildingRepository;
    private final EdgeRepository edgeRepository;
    private final NodeRepository nodeRepository;

    public MapService(BuildingRepository buildingRepository, EdgeRepository edgeRepository, NodeRepository nodeRepository) {
        this.buildingRepository = buildingRepository;
        this.edgeRepository = edgeRepository;
        this.nodeRepository = nodeRepository;
    }
    
    public MapDTO getMap() {
        List<Node> nodesList = nodeRepository.findAll();
        List<Edge> edgesList = edgeRepository.findAll();
        List<Building> buildingsList = buildingRepository.findAll();

        // Converter todos os nodes
        List<NodeDTO> nodes = nodesList.stream()
                .map(node -> new NodeDTO(
                        node.getId(),
                        node.getX(),
                        node.getY()
                ))
                .toList();

        // Converter edges (sem calcular length, apenas IDs dos nodes)
        List<EdgeDTO> edges = edgesList.stream()
                .map(edge -> {
                    // Calculate length using Euclidean distance
                    double dx = edge.getNodeB().getX() - edge.getNodeA().getX();
                    double dy = edge.getNodeB().getY() - edge.getNodeA().getY();
                    double length = Math.sqrt(dx * dx + dy * dy);

                    return new EdgeDTO(
                            edge.getId(),
                            edge.getNodeA().getId(),
                            edge.getNodeB().getId(),
                            length
                    );
                })
                .toList();

        // Converter buildings (apenas nodeId, não o objeto completo)
        List<BuildingDTO> buildings = buildingsList.stream()
                .map(building -> new BuildingDTO(
                        building.getId(),
                        building.getName(),
                        building.getModelPath(),
                        building.getNode().getId()
                ))
                .toList();

        return new MapDTO(nodes, edges, buildings);
    }
}