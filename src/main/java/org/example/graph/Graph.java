package org.example.graph;

import java.util.*;

public class Graph {
    private final Map<String, List<String>> adjacencyList;

    public Graph() {
        this.adjacencyList = new HashMap<>();
    }

    public void addVertex(String vertex) {
        adjacencyList.putIfAbsent(vertex, new ArrayList<>());
    }

    public void addEdge(String source, String destination) {
        addVertex(source);
        addVertex(destination);
        adjacencyList.get(source).add(destination);
    }

    public List<String> getAdjacentVertices(String vertex) {
        return adjacencyList.getOrDefault(vertex, new ArrayList<>());
    }

    public void printGraph() {
        for (Map.Entry<String, List<String>> entry : adjacencyList.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
    }

    public List<String> findPath(String start, String end) {
        Set<String> visited = new HashSet<>();
        List<String> path = new ArrayList<>();
        if (dfs(start, end, visited, path)) {
            return path;
        }
        return Collections.emptyList();
    }

    private boolean dfs(String current, String end, Set<String> visited, List<String> path) {
        visited.add(current);
        path.add(current);

        if (current.equals(end)) {
            return true;
        }

        for (String neighbor : getAdjacentVertices(current)) {
            if (!visited.contains(neighbor)) {
                if (dfs(neighbor, end, visited, path)) {
                    return true;
                }
            }
        }

        path.removeLast();
        return false;
    }
}

