package com.example.console.graph;

import java.util.ArrayList;
import java.util.List;

/*
https://www.geeksforgeeks.org/longest-path-in-a-directed-acyclic-graph-dynamic-programming/
Given a directed graph G with N vertices and M edges. The task is to find the length of the longest directed path in Graph.
Note: Length of a directed path is the number of edges in it.
Examples:
Input: N = 4, M = 5
[1->2, 1->3, 2->4, 3->4, 3-2]
Output: 3
The directed path 1->3->2->4
Input: N = 5, M = 8
[2->3, 2->4, 4->3, 1->3, 1->4, 5->2, 5->3, 5->1]
Output: 3

dp[node] = max(dp[node], 1 + max(dp[child1], dp[child2], dp[child3]..))
 */
public class LongestPathDirectGraph {
    int vertices;
    List<Integer> edge[];

    public LongestPathDirectGraph(int vertices){
        this.vertices = vertices;
        edge = new ArrayList[vertices+1];
        for (int i = 0; i <= vertices; i++)
        {
            edge[i] = new ArrayList<>();
        }
    }

    private void addEdge(int a, int b)
    {
        edge[a].add(b);
    }

    private void dfs(int node, List<Integer> adj[], int dp[], boolean visited[])
    {
        // Mark as visited
        visited[node] = true;

        // Traverse for all its children
        for (int i = 0; i < adj[node].size(); i++)
        {

            // If not visited
            if (!visited[adj[node].get(i)])
                dfs(adj[node].get(i), adj, dp, visited);

            // Store the max of the paths
            dp[node] = Math.max(dp[node], 1 + dp[adj[node].get(i)]);
        }
    }

    public int findLongestPath( int n)
    {
        // Dp array
        int[] dp = new int[n+1];

        // Visited array to know if the node
        // has been visited previously or not
        boolean[] visited = new boolean[n + 1];

        // Call DFS for every unvisited vertex
        for (int i = 1; i <= n; i++)
        {
            if (!visited[i])
                dfs(i, edge, dp, visited);
        }

        int ans = 0;

        // Traverse and find the maximum of all dp[i]
        for (int i = 1; i <= n; i++)
        {
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }
}
