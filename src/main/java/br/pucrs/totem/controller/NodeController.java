package br.pucrs.totem.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.pucrs.totem.entity.Node;
import br.pucrs.totem.service.NodeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/nodes")
@Tag(name = "Nodes", description = "Endpoints for managing nodes")
public class NodeController {

    private final NodeService nodeService;

    public NodeController(NodeService nodeService) {
        this.nodeService = nodeService;
    }

    @GetMapping
    @Operation(summary = "Get all nodes", description = "Retrieve a list of all nodes")
    public ResponseEntity<List<Node>> getAllNodes() {
        List<Node> nodes = nodeService.getAllNodes();
        return ResponseEntity.ok(nodes);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get node by ID", description = "Retrieve a specific node by its ID")
    public ResponseEntity<Node> getNodeById(@PathVariable Long id) {
        Optional<Node> node = nodeService.getNodeById(id);
        return node.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Create node", description = "Create a new node")
    public ResponseEntity<Node> createNode(@RequestBody Node node) {
        Node savedNode = nodeService.saveNode(node);
        return ResponseEntity.ok(savedNode);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update node", description = "Update an existing node")
    public ResponseEntity<Node> updateNode(@PathVariable Long id, @RequestBody Node nodeDetails) {
        Node updatedNode = nodeService.updateNode(id, nodeDetails);
        if (updatedNode != null) {
            return ResponseEntity.ok(updatedNode);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete node", description = "Delete a node by its ID")
    public ResponseEntity<Void> deleteNode(@PathVariable Long id) {
        boolean deleted = nodeService.deleteNode(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
