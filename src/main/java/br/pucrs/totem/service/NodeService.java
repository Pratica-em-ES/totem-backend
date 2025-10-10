package br.pucrs.totem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.pucrs.totem.entity.Node;
import br.pucrs.totem.repository.NodeRepository;

@Service
public class NodeService {

    private final NodeRepository nodeRepository;

    public NodeService(NodeRepository nodeRepository) {
        this.nodeRepository = nodeRepository;
    }

    public List<Node> getAllNodes() {
        return nodeRepository.findAll();
    }

    public Optional<Node> getNodeById(Long id) {
        return nodeRepository.findById(id);
    }

    public Node saveNode(Node node) {
        return nodeRepository.save(node);
    }

    public Node updateNode(Long id, Node nodeDetails) {
        return nodeRepository.findById(id)
                .map(node -> {
                    node.setX(nodeDetails.getX());
                    node.setY(nodeDetails.getY());
                    return nodeRepository.save(node);
                })
                .orElse(null);
    }

    public boolean deleteNode(Long id) {
        if (nodeRepository.existsById(id)) {
            nodeRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
