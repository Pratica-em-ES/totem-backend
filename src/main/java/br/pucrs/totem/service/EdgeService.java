package br.pucrs.totem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.pucrs.totem.entity.Edge;
import br.pucrs.totem.repository.EdgeRepository;

@Service
public class EdgeService {

    private final EdgeRepository edgeRepository;

    public EdgeService(EdgeRepository edgeRepository) {
        this.edgeRepository = edgeRepository;
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
                    edge.setWeight(edgeDetails.getWeight());
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
}
