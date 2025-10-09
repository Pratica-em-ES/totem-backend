package br.pucrs.totem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.pucrs.totem.entity.Edge;
import br.pucrs.totem.entity.BuildingEdge;
import br.pucrs.totem.entity.Building;
import br.pucrs.totem.repository.EdgeRepository;
import br.pucrs.totem.repository.BuildingEdgeRepository;
import br.pucrs.totem.repository.BuildingRepository;

@Service
public class EdgeService {

    private final EdgeRepository edgeRepository;
    private final BuildingEdgeRepository buildingEdgeRepository;
    private final BuildingRepository buildingRepository;

    public EdgeService(EdgeRepository edgeRepository, 
                        BuildingEdgeRepository buildingEdgeRepository,
                        BuildingRepository buildingRepository) {
        this.edgeRepository = edgeRepository;
        this.buildingEdgeRepository = buildingEdgeRepository;
        this.buildingRepository = buildingRepository;
    }

    public List<Edge> getAllEdges() {
        return edgeRepository.findAll();
    }

    public Optional<Edge> getEdgeById(Long id) {
        return edgeRepository.findById(id);
    }

    public Edge saveEdge(Edge edge) {
        return edgeRepository.save(edge);
    }

    public Edge updateEdge(Long id, Edge edgeDetails) {
        return edgeRepository.findById(id)
                .map(edge -> {
                    edge.setWidth(edgeDetails.getWidth());
                    edge.setNodeA(edgeDetails.getNodeA());
                    edge.setNodeB(edgeDetails.getNodeB());
                    return edgeRepository.save(edge);
                })
                .orElse(null);
    }

    public boolean deleteEdge(Long id) {
        if (edgeRepository.existsById(id)) {
            edgeRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<BuildingEdge> getEdgeBuildings(Long edgeId) {
        return buildingEdgeRepository.findByEdgeId(edgeId);
    }

    public BuildingEdge addBuildingToEdge(Long edgeId, Long buildingId) {
        Optional<Edge> edge = edgeRepository.findById(edgeId);
        Optional<Building> building = buildingRepository.findById(buildingId);
        if (edge.isPresent() && building.isPresent()) {
            BuildingEdge buildingEdge = new BuildingEdge();
            buildingEdge.setEdge(edge.get());
            buildingEdge.setBuilding(building.get());
            return buildingEdgeRepository.save(buildingEdge);
        }
        return null;
    }

    public boolean deleteEdgeBuilding(Long buildingEdgeId) {
        if (buildingEdgeRepository.existsById(buildingEdgeId)) {
            buildingEdgeRepository.deleteById(buildingEdgeId);
            return true;
        }
        return false;
    }
}
