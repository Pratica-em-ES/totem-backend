package br.pucrs.totem.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.pucrs.totem.entity.Edge;
import br.pucrs.totem.service.EdgeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/edges")
@Tag(name = "Edges", description = "Endpoints for managing edges")
public class EdgeController {

    private final EdgeService edgeService;

    public EdgeController(EdgeService edgeService) {
        this.edgeService = edgeService;
    }

    @GetMapping
    @Operation(summary = "Get all edges", description = "Retrieve a list of all edges")
    public ResponseEntity<List<Edge>> getAllEdges() {
        List<Edge> edges = edgeService.getAllEdges();
        return ResponseEntity.ok(edges);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get edge by ID", description = "Retrieve a specific edge by its ID")
    public ResponseEntity<Edge> getEdgeById(@PathVariable Long id) {
        Optional<Edge> edge = edgeService.getEdgeById(id);
        return edge.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Create edge", description = "Create a new edge")
    public ResponseEntity<Edge> createEdge(@RequestBody Edge edge) {
        Edge savedEdge = edgeService.saveEdge(edge);
        return ResponseEntity.ok(savedEdge);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update edge", description = "Update an existing edge")
    public ResponseEntity<Edge> updateEdge(@PathVariable Long id, @RequestBody Edge edgeDetails) {
        Edge updatedEdge = edgeService.updateEdge(id, edgeDetails);
        if (updatedEdge != null) {
            return ResponseEntity.ok(updatedEdge);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete edge", description = "Delete an edge by its ID")
    public ResponseEntity<Void> deleteEdge(@PathVariable Long id) {
        boolean deleted = edgeService.deleteEdge(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
