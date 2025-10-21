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
     * @param startBuildingId The ID of the starting building.
     * @param destinationBuildingId  The ID of the destination building.
     * @return An ordered list of node IDs representing the shortest path.
     */
    public List<Long> findMinimumPathToBuilding(Long startBuildingId, Long destinationBuildingId) {
        // Retrieve the start building
        Building startBuilding = buildingRepository.findById(startBuildingId)
                .orElseThrow(() -> new IllegalArgumentException("Start building not found with ID: " + startBuildingId));

        // Retrieve the destination building
        Building destinationBuilding = buildingRepository.findById(destinationBuildingId)
                .orElseThrow(() -> new IllegalArgumentException("Destination building not found with ID: " + destinationBuildingId));

        // Get the edge nodes (entry points) from both buildings
        Node startEdgeNode = startBuilding.getEdgeNode();
        Node destinationEdgeNode = destinationBuilding.getEdgeNode();
        
        if (startEdgeNode == null) {
            throw new IllegalArgumentException("Start building does not have an associated edge node");
        }
        
        if (destinationEdgeNode == null) {
            throw new IllegalArgumentException("Destination building does not have an associated edge node");
        }

        // Build the graph
        Map<Long, List<Edge>> adjacencyList = buildGraph();

        // Find the shortest path using Dijkstra's algorithm
        List<Long> path = dijkstra(startEdgeNode.getId(), destinationEdgeNode.getId(), adjacencyList);

        return path;
    }

    /**
     * Builds the graph as an adjacency list where edges are weighted by Euclidean distance.
     */
    private Map<Long, List<Edge>> buildGraph() {
        Map<Long, List<Edge>> adjacencyList = new HashMap<>();

        // Load all edges from the edge repository
        List<Edge> allEdges = edgeRepository.findAll();

        // Build bidirectional adjacency list from actual graph edges
        // KEEP all original edges - they form the walkable paths
        for (Edge edge : allEdges) {
            Node nodeA = edge.getNodeA();
            Node nodeB = edge.getNodeB();
            
            if (nodeA == null || nodeB == null) {
                continue;
            }

            // Initialize adjacency lists for both nodes
            adjacencyList.putIfAbsent(nodeA.getId(), new ArrayList<>());
            adjacencyList.putIfAbsent(nodeB.getId(), new ArrayList<>());

            // Add edge in both directions (bidirectional graph)
            adjacencyList.get(nodeA.getId()).add(edge);
            adjacencyList.get(nodeB.getId()).add(edge);
        }

        // Now connect buildings to the graph via their edge nodes
        List<Building> allBuildings = buildingRepository.findAll();
        
        for (Building building : allBuildings) {
            Node buildingNode = building.getNode();
            Node edgeNode = building.getEdgeNode();
            
            if (buildingNode == null || edgeNode == null) {
                continue;
            }
            
            // Simply connect the building to its entry point (edgeNode)
            // The edgeNode should already be part of an existing edge in the graph
            Edge buildingConnection = new Edge();
            buildingConnection.setId(-building.getId()); // Negative ID for virtual edges
            buildingConnection.setNodeA(buildingNode);
            buildingConnection.setNodeB(edgeNode);
            double distance = calculateEuclideanDistance(buildingNode, edgeNode);
            buildingConnection.setWidth(distance);
            
            // Add nodes to adjacency list if not present
            adjacencyList.putIfAbsent(buildingNode.getId(), new ArrayList<>());
            adjacencyList.putIfAbsent(edgeNode.getId(), new ArrayList<>());
            
            // Add bidirectional connection
            adjacencyList.get(buildingNode.getId()).add(buildingConnection);
            adjacencyList.get(edgeNode.getId()).add(buildingConnection);
        }

        return adjacencyList;
    }

    /**
     * Dijkstra's algorithm to find the shortest path between two nodes.
     */
    private List<Long> dijkstra(Long startNodeId, Long destinationNodeId, Map<Long, List<Edge>> adjacencyList) {
        // Verify that start and destination nodes exist in the graph
        if (!adjacencyList.containsKey(startNodeId)) {
            return Collections.emptyList();
        }
        if (!adjacencyList.containsKey(destinationNodeId)) {
            return Collections.emptyList();
        }
        
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