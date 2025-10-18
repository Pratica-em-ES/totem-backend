package br.pucrs.totem.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

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
     * Finds the minimum path to a building and returns an ordered list of edges.
     *
     * @param startNodeId The ID of the starting node.
     * @param buildingId  The ID of the destination building.
     * @return An ordered list of edges representing the shortest path.
     */
    public List<Edge> findMinimumPathToBuilding(Long startBuildingId, Long buildingId) {
        // Retrieve the destination building
        Building destinationBuilding = buildingRepository.findById(buildingId)
                .orElseThrow(() -> new IllegalArgumentException("Building not found"));

        Building fromBuilding = buildingRepository.findById(startBuildingId)
                .orElseThrow(() -> new IllegalArgumentException("Building not found"));
        // Get the destination node from the building
        Node destinationNode = destinationBuilding.getNode();
        if (destinationNode == null) {
            throw new IllegalArgumentException("Building does not have an associated node");
        }

        // Build the graph
        Map<Long, List<Edge>> adjacencyList = buildGraph();

        // Find the shortest path using Dijkstra's algorithm
        List<Long> shortestPathNodeIds = dijkstra(fromBuilding.getEdgeNode().getId(), destinationBuilding.getEdgeNode().getId(), adjacencyList);

        // Convert the node path to an edge path
        return convertNodePathToEdgePath(shortestPathNodeIds, adjacencyList);
    }

    public List<Integer> findMinimumPathToBuildingIds(Long startNodeId, Long buildingId) {
        List<Edge> edgePath = findMinimumPathToBuilding(startNodeId, buildingId);
        List<Integer> edgeIds = new ArrayList<>();
        for (Edge edge : edgePath) {
            edgeIds.add(edge.getId().intValue());
        }
        return edgeIds;
    }

    /**
     * Builds the graph as an adjacency list where edges are weighted by Euclidean distance.
     */
    private Map<Long, List<Edge>> buildGraph() {
        Map<Long, List<Edge>> adjacencyList = new HashMap<>();

        // Load all edges and nodes
        List<Edge> edges = edgeRepository.findAll();

        // Populate the adjacency list
        for (Edge edge : edges) {
            Node nodeA = edge.getNodeA();
            Node nodeB = edge.getNodeB();

            // Calculate the Euclidean distance as the weight
            double distance = calculateEuclideanDistance(nodeA, nodeB);
            edge.setWidth(distance); // Update the edge's width with the calculated distance

            adjacencyList.putIfAbsent(nodeA.getId(), new ArrayList<>());
            adjacencyList.putIfAbsent(nodeB.getId(), new ArrayList<>());

            adjacencyList.get(nodeA.getId()).add(edge);
            adjacencyList.get(nodeB.getId()).add(edge);
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

        // Initialize distances
        for (Long nodeId : adjacencyList.keySet()) {
            distances.put(nodeId, Double.MAX_VALUE);
        }
        distances.put(startNodeId, 0.0);

        priorityQueue.add(new NodeDistance(startNodeId, 0.0));

        while (!priorityQueue.isEmpty()) {
            NodeDistance current = priorityQueue.poll();
            Long currentNodeId = current.getNodeId();

            if (currentNodeId.equals(destinationNodeId)) {
                break;
            }

            for (Edge edge : adjacencyList.getOrDefault(currentNodeId, Collections.emptyList())) {
                Long neighborNodeId = edge.getNodeA().getId().equals(currentNodeId) ? edge.getNodeB().getId() : edge.getNodeA().getId();
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
     * Converts a list of node IDs into a list of edges.
     */
    private List<Edge> convertNodePathToEdgePath(List<Long> nodePath, Map<Long, List<Edge>> adjacencyList) {
        List<Edge> edgePath = new ArrayList<>();

        for (int i = 0; i < nodePath.size() - 1; i++) {
            Long currentNodeId = nodePath.get(i);
            Long nextNodeId = nodePath.get(i + 1);

            // Find the edge connecting the current node to the next node
            for (Edge edge : adjacencyList.getOrDefault(currentNodeId, Collections.emptyList())) {
                if ((edge.getNodeA().getId().equals(currentNodeId) && edge.getNodeB().getId().equals(nextNodeId)) ||
                        (edge.getNodeB().getId().equals(currentNodeId) && edge.getNodeA().getId().equals(nextNodeId))) {
                    edgePath.add(edge);
                    break;
                }
            }
        }

        return edgePath;
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