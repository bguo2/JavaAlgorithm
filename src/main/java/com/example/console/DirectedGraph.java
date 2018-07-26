package com.example.console;

import java.util.ArrayList;
import java.util.Iterator;

public class DirectedGraph {
    private int vertex;
    private ArrayList<Integer>[] edges;

    public DirectedGraph(int n) {
        this.vertex = n;
        this.edges = new ArrayList[n];
        for(int i = 0; i < n; i++){
            edges[i] = new ArrayList<>();
        }
    }

    public void addEdge(int from, int to) {
        edges[from].add(to);
    }

    //if the vertex is visited and
    public boolean isCyclic(){
        boolean[] visited = new boolean[vertex];
        boolean[] recStack = new boolean[vertex];

        for(int i = 0; i < vertex; i++){
            if(isCyclic(i, visited, recStack))
                return true;
        }

        return false;
    }

    private boolean isCyclic(int v, boolean[] visited, boolean[] recStack){
        if(recStack[v])
            return true;
        if(visited[v])
            return false;
        visited[v] = true;
        recStack[v] = true;

        for(Integer c : edges[v])
        {
            if(isCyclic(c, visited, recStack))
                return true;
        }

        recStack[v] = false;
        return false;
    }

}
