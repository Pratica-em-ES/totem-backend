package br.pucrs.totem.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import br.pucrs.totem.entity.Building;
import br.pucrs.totem.entity.Edge;
import br.pucrs.totem.entity.Node;
import br.pucrs.totem.repository.BuildingRepository;
import br.pucrs.totem.repository.EdgeRepository;
import br.pucrs.totem.repository.NodeRepository;

@Service
public class RouteService {

    private final NodeRepository nodeRepository;
    private final EdgeRepository edgeRepository;
    private final BuildingRepository buildingRepository;

    public RouteService(NodeRepository nodeRepository, EdgeRepository edgeRepository, BuildingRepository buildingRepository) {
        this.nodeRepository = nodeRepository;
        this.edgeRepository = edgeRepository;
        this.buildingRepository = buildingRepository;
    }

    /**
     * Finds the minimum path to a building and returns an ordered list of node IDs.
     *
     * @param startNodeId The ID of the starting node.
     * @param buildingId  The ID of the destination building.
     * @return An ordered list of node IDs representing the shortest path.
     */
    public List<Long> findMinimumPathToBuilding(Long startNodeId, Long buildingId) {

        Building startBuilding = buildingRepository.findById(startNodeId)
                .orElseThrow(() -> new IllegalArgumentException("Start building not found"));

        // Retrieve the destination building
        Building destinationBuilding = buildingRepository.findById(buildingId)
                .orElseThrow(() -> new IllegalArgumentException("Building not found"));

        // Get the destination node from the building
        Node destinationNode = destinationBuilding.getEdgeNode();
        if (destinationNode == null) {
            throw new IllegalArgumentException("Building does not have an associated node");
        }

        // Build the graph
        Map<Long, List<Edge>> adjacencyList = buildGraph();

        // Find the shortest path using Dijkstra's algorithm
        return dijkstra(startBuilding.getEdgeNode().getId(), destinationNode.getId(), adjacencyList);
    }

    /**
     * Builds the graph as an adjacency list where edges are weighted by Euclidean distance.
     */
    private Map<Long, List<Edge>> buildGraph() {
        Map<Long, List<Edge>> adjacencyList = new HashMap<>();

        // Load all edges
        List<Pair<Edge, Node>> pairs = buildingRepository.findAll().stream()
            .map(building -> Pair.of(building.getEdge(), building.getEdgeNode()))
            .toList();

        // Populate the adjacency list
        for (Pair<Edge, Node> pair : pairs) {
            Edge edge = pair.getFirst();
            Node node = pair.getSecond();

            Node nodeA = edge.getNodeA();
            Node nodeB = edge.getNodeB();

            adjacencyList.putIfAbsent(nodeA.getId(), new ArrayList<>());
            adjacencyList.putIfAbsent(nodeB.getId(), new ArrayList<>());
            adjacencyList.putIfAbsent(node.getId(), new ArrayList<>());

            Edge newEdge;
            newEdge = new Edge();
            newEdge.setId(edge.getId());
            newEdge.setNodeA(nodeA);
            newEdge.setNodeB(node);

            adjacencyList.get(nodeA.getId()).add(newEdge);
            adjacencyList.get(node.getId()).add(newEdge);

            // Calculate the Euclidean distance as the weight
            double distance = calculateEuclideanDistance(nodeA, node);
            newEdge.setWidth(distance); // Update the edge's width with the calculated distance

            newEdge = new Edge();
            newEdge.setId(edge.getId());
            newEdge.setNodeA(nodeB);
            newEdge.setNodeB(node);

            adjacencyList.get(nodeB.getId()).add(newEdge);
            adjacencyList.get(node.getId()).add(newEdge);

            // Calculate the Euclidean distance as the weight
            distance = calculateEuclideanDistance(nodeB, node);
            newEdge.setWidth(distance); // Update the edge's width with the calculated distance
        }

        return adjacencyList;
    }

    /**
     * Dijkstra's algorithm to find the shortest path between two nodes.
     */
    private List<Long> dijkstra(Long startNodeId, Long destinationNodeId, Map<Long, List<Edge>> adjacencyList) {
        Map<Long, Double> distances = new HashMap<>();
        Map<Long, Long> previousNodes = new HashMap<>();
        PriorityQueue<NodeDistance> priorityQueue = new PriorityQueue<>(Comparator.comparingDouble(NodeDistance::getDistance));
        for (Long nodeId : adjacencyList.keySet()) {
            distances.put(nodeId, Double.MAX_VALUE);
            previousNodes.put(nodeId, null);
        }
        distances.put(startNodeId, 0.0);
        priorityQueue.add(new NodeDistance(startNodeId, 0.0));

        Set<Long> visited = new HashSet<>();

        while (!priorityQueue.isEmpty()) {
            NodeDistance current = priorityQueue.poll();
            Long currentNodeId = current.getNodeId();

            // Skip if we've already processed this node with a shorter distance
            if (visited.contains(currentNodeId)) continue;
            visited.add(currentNodeId);

            if (currentNodeId.equals(destinationNodeId)) {
                break;
            }

            for (Edge edge : adjacencyList.getOrDefault(currentNodeId, Collections.emptyList())) {
                Long neighborNodeId = edge.getNodeA().getId().equals(currentNodeId)
                    ? edge.getNodeB().getId()
                    : edge.getNodeA().getId();

                double newDistance = distances.get(currentNodeId) + edge.getWidth();

                if (newDistance < distances.get(neighborNodeId)) {
                    distances.put(neighborNodeId, newDistance);
                    previousNodes.put(neighborNodeId, currentNodeId);
                    priorityQueue.add(new NodeDistance(neighborNodeId, newDistance));
                }
            }
        }

        // Reconstruct the path
        List<Long> path = new ArrayList<>();
        for (Long at = destinationNodeId; at != null; at = previousNodes.get(at)) {
            path.add(at);
        }
        Collections.reverse(path);

        return path.isEmpty() || !path.get(0).equals(startNodeId) ? Collections.emptyList() : path;
    }

    /**
     * Calculates the Euclidean distance between two nodes.
     */
    private double calculateEuclideanDistance(Node nodeA, Node nodeB) {
        double dx = nodeA.getX() - nodeB.getX();
        double dy = nodeA.getY() - nodeB.getY();
        return Math.sqrt(dx * dx + dy * dy);
    }

    private static class NodeDistance {
        private final Long nodeId;
        private final double distance;

        public NodeDistance(Long nodeId, double distance) {
            this.nodeId = nodeId;
            this.distance = distance;
        }

        public Long getNodeId() {
            return nodeId;
        }

        public double getDistance() {
            return distance;
        }
    }
}