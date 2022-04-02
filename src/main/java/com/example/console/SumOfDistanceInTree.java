package com.example.console;

import java.util.*;

// Sum of Distances in Tree 树中距离之和
//
//An undirected, connected tree with `N` nodes labelled `0...N-1` and `N-1` `edges` are given.
//The ith edge connects nodes edges[i][0] and edges[i][1] together.
//
//Return a list ans, where ans[i] is the sum of the distances between node i and all other nodes.
//Example 1:
//
//Input: N = 6, edges = [[0,1],[0,2],[2,3],[2,4],[2,5]]
//Output: [8,12,6,10,10,10]
//Explanation:
//Here is a diagram of the given tree:
//  0
// / \
//1   2
//   /|\
//  3 4 5
//We can see that dist(0,1) + dist(0,2) + dist(0,3) + dist(0,4) + dist(0,5)
//equals 1 + 1 + 2 + 2 + 2 = 8.  Hence, answer[0] = 8, and so on.

//Root the tree. For each node, consider the subtree S_{\text{node}}S node
//of that node plus all descendants. Let count[node] be the number of nodes in S_{\text{node}}S node
//and stsum[node] ("subtree sum") be the sum of the distances from node to the nodes in S_{\text{node}}S node
//We can calculate count and stsum using a post-order traversal, where on exiting some node, the count and stsum of all
//descendants of this node is correct, and we now calculate count[node] += count[child] and stsum[node] += stsum[child] + count[child].
//
public class SumOfDistanceInTree {
    int[] ans, count;
    List<Set<Integer>> graph;
    int N;

    public int[] getSumOfDistancesInTree(int N, int[][] edges) {
        this.N = N;
        graph = new ArrayList<Set<Integer>>();
        ans = new int[N];
        count = new int[N];
        Arrays.fill(count, 1);

        for (int i = 0; i < N; ++i)
            graph.add(new HashSet<Integer>());
        for (int[] edge: edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }
        dfs(0, -1);
        dfs2(0, -1);
        return ans;
    }

    public void dfs(int node, int parent) {
        for (int child: graph.get(node)) {
            if (child != parent) {
                dfs(child, node);
                count[node] += count[child];
                ans[node] += ans[child] + count[child];
            }
        }
    }

    public void dfs2(int node, int parent) {
        for (int child: graph.get(node)) {
            if (child != parent) {
                ans[child] = ans[node] - count[child] + N - count[child];
                dfs2(child, node);
            }
        }
    }
}
