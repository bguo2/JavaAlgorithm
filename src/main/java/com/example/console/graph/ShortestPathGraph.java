package com.example.console.graph;

import java.util.*;

//shortest path on an unweighed graph
//Given a unweighted graph, a source and a destination,
// we need to find shortest path from source to destination in the graph in most optimal way.
//given [2,1] [1,0] [0, 3] [3,7] [3,4] [3,7] [4,7] [4, 6] [6, 7] [5,6] [4,5]
//Input: source vertex = 0 and destination vertex is = 7.
//Output: Shortest path length is:2
//        Path is::
//        0 3 7
//
//Input: source vertex is = 2 and destination vertex is = 6.
//Output: Shortest path length is:5
//        Path is::
//        2 1 0 3 4 6
public class ShortestPathGraph {
    private int[] vertex;
    private List<Integer>[] edges;
    private int vertices;
    private class Node {
        private int vertex;
        private int dist;
        private Node preNode;
        public Node(int vertex, int dist, Node preNode) {
            this.vertex = vertex;
            this.dist = dist;
            this.preNode = preNode;
        }
    }

    public ShortestPathGraph(int n) {
        this.vertices = n;
        this.vertex = new int[n];
        this.edges = new List[n];
        for(int i = 0; i < n; i++) {
            edges[i] = new ArrayList<>();
            vertex[i] = i;
        }
    }

    public void addEdge(int from, int to) {
        edges[from].add(to);
        edges[to].add(from);
    }

    public int getShortestPath(int start, int end, List<Integer> path) {
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(start, 0, null));
        boolean[] visited = new boolean[this.vertices];
        visited[start] = true;
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            if(node.vertex == end) {
                getPath(node, path);
                return node.dist;
            }

            for(int v: edges[node.vertex]) {
                if(!visited[v]) {
                    visited[v] = true;
                    queue.offer(new Node(v, node.dist + 1, node));
                }
            }
        }

        return -1;
    }

    private void getPath(Node endNode, List<Integer> result) {
        Node node = endNode;
        while (node != null) {
            result.add(node.vertex);
            node = node.preNode;
        }

        Collections.reverse(result);
    }
}
