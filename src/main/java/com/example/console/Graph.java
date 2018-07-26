package com.example.console;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

// undirected graph
public class Graph {
    private int vertex;
    private ArrayList<Integer> edges[];

    public Graph(int n){
        vertex = n;
        edges = new ArrayList[n];
        for(int i = 0; i < n; i++){
            edges[i] = new ArrayList<>();
        }
    }

    public void addEdge(int v, int w) {
        edges[v].add(w);
        edges[w].add(v);
    }

    public boolean isCyclic() {
        boolean[] visited = new boolean[vertex];
        Arrays.fill(visited, false);

        for(int i = 0; i < vertex; i++){
            if(!visited[i] && isCyclic(i, visited, -1))
                return true;
        }

        return false;
    }

    private boolean isCyclic(int v, boolean[] visited, int parent){

        visited[v] = true;

        for(Integer c : edges[v]){

            if(!visited[c]){
                if(isCyclic(c, visited, v))
                    return true;
            }
            //If an adjacent is visited and not parent of current vertex, then there is a cycle.
            else if(c != parent)
                return true;
        }

        return false;
    }

}
