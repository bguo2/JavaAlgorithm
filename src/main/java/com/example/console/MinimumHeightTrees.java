package com.example.console;

import java.util.*;

//For a undirected graph with tree characteristics, we can choose any node as the root. The result graph is then a rooted tree.
// Among all possible rooted trees, those with minimum height are called minimum height trees (MHTs). Given such a graph,
// write a function to find all the MHTs and return a list of their root labels.
//
//Format
//The graph contains n nodes which are labeled from 0 to n - 1. You will be given the number n and a list of undirected edges
// (each edge is a pair of labels).
//
//You can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0, 1] is the same as [1, 0]
// and thus will not appear together in edges.
//
//Example 1 :
//
//Input: n = 4, edges = [[1, 0], [1, 2], [1, 3]]
//
//        0
//        |
//        1
//       / \
//      2   3
//
//Output: [1]
//Example 2 :
//
//Input: n = 6, edges = [[0, 3], [1, 3], [2, 3], [4, 3], [5, 4]]
//
//     0  1  2
//      \ | /
//        3
//        |
//        4
//        |
//        5
//
//Output: [3, 4]
//(1) A tree is an undirected graph in which any two vertices are
//connected by exactly one path.
//
//(2) Any connected graph who has n nodes with n-1 edges is a tree.
//
//(3) The degree of a vertex of a graph is the number of
//edges incident to the vertex.
//
//(4) A leaf is a vertex of degree 1. An internal vertex is a vertex of
//degree at least 2.
//
//(5) A path graph is a tree with two or more vertices that is not
//branched at all.
//
//(6) A tree is called a rooted tree if one vertex has been designated
//the root.
//
//(7) The height of a rooted tree is the number of edges on the longest
//downward path between root and a leaf.
public class MinimumHeightTrees {
    public  static List<Integer> findMinHeightTrees(int n, int[][] edges) {
        List<Integer> result = new ArrayList<Integer>();
        if(n==0){
            return result;
        }
        if(n==1){
            result.add(0);
            return result;
        }

        Set<Integer>[] graph = new HashSet[n];
        for(int i=0; i<n; i++) {
            graph[i] = new HashSet<>();
        }

        for(int[] edge: edges) {
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }

        LinkedList<Integer> leaves = new LinkedList<Integer>();
        for(int i=0; i<n; i++){
            if(graph[i].size()==1) {
                leaves.offer(i);
            }
        }

        if(leaves.size()==0) {
            return result;
        }

        while(n>2) {
            n = n-leaves.size();
            LinkedList<Integer> newLeaves = new LinkedList<Integer>();
            for(int l: leaves) {
                //since l is leaf, it must have only 1 neighbor
                Iterator<Integer> iterator = graph[l].iterator();
                int neighbor = iterator.next();
                graph[neighbor].remove(l);
                if (graph[neighbor].size() == 1) {
                    newLeaves.add(neighbor);
                }
            }
            leaves = newLeaves;
        }

        return leaves;
    }
}
