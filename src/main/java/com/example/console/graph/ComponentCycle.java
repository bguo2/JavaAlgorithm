package com.example.console.graph;

import java.util.*;

//Component A depends on B and C; C depend on D; D depends on A, here it is cycle
//check if it has cycle or not
public class ComponentCycle {
    private Map<String, List<String>> edges;

    public ComponentCycle() {
        edges = new HashMap<>();
    }

    public void addEdge(String from, String to) {
        List<String> edgeList;
        if(edges.containsKey(from)) {
            edgeList = edges.get(from);
            edgeList.add(to);
        }
        else {
            edgeList = new ArrayList<>();
            edgeList.add(to);
            edges.put(from, edgeList);
        }
    }

    public boolean hasCycle() {
        Set<String> visited = new HashSet<>();
        Set<String> rack = new HashSet<>();
        for(Map.Entry<String, List<String>> e : edges.entrySet()) {
            if(hasCycle(e.getKey(), visited, rack))
                return true;
        }

        return false;
    }

    private boolean hasCycle(String vertex, Set<String> visited, Set<String> rack) {
        if(rack.contains(vertex))
            return true;
        if(visited.contains(vertex))
            return false;
        visited.add(vertex);
        rack.add(vertex);

        List<String> edgeList = edges.get(vertex);
        if(edgeList != null && edgeList.size() > 0) {
            for (String e : edgeList) {
                if (hasCycle(e, visited, rack))
                    return true;
            }
        }

        rack.remove(vertex);
        return false;
    }
}
