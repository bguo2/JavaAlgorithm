package com.example.console;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

// undirected graph
public class UndirectedGraph {
    private int vertex;
    private ArrayList<Integer> edges[];

    public UndirectedGraph(int n){
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

    //is the graph cyclic
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

        for(Integer edgeVertex : edges[v]){

            if(!visited[edgeVertex]){
                if(isCyclic(edgeVertex, visited, v))
                    return true;
            }
            //If an adjacent is visited and not parent of current vertex, then there is a cycle.
            else if(edgeVertex != parent)
                return true;
        }

        return false;
    }

    //get the count of connected components
    public int getConnectedComponents()
    {
        boolean[] visited = new boolean[vertex];
        Arrays.fill(visited, false);
        
        int count = 0;
        for(int i = 0; i< this.vertex; i++){
            if(!visited[i]) {
                count++;
                dfs(i, visited);
            }
        }
        
        return count;
    }
    
    private void dfs(int vertex, boolean[] visit){
        visit[vertex] = true;
        for (Integer edgeVertex: this.edges[vertex]){
            if(!visit[edgeVertex])
                dfs(edgeVertex, visit);
        }
    }

    //Is the graph a valid tree? can convert to check if it is cyclic
    public boolean isGraphValidTree(){
        boolean[] visited = new boolean[vertex];
        Arrays.fill(visited, false);

        if(isCyclic(0, visited, -1))
            return false;

        //still have unvisited vertices: disconnected components
        for(boolean isVisited: visited)
        {
            if(!isVisited)
                return false;
        }

        return true;
    }
}
