package com.example.console;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

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
        Iterator<Integer> it = edges[v].iterator();
        while (it.hasNext()){
            Integer next = it.next();
            if(!visited[next]){
                if(isCyclic(next, visited, v))
                    return true;
            }
            //If an adjacent is visited and not parent of current vertex, then there is a cycle.
            else if(next != parent)
                return true;
        }

        return false;
    }

}
