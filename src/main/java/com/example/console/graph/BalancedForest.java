package com.example.console.tree;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//Greg has a tree of nodes containing integer data. He wants to insert a node with some non-zero integer value somewhere into
// the tree. His goal is to be able to cut two edges and have the values of each of the three new trees sum to the same amount.
// This is called a balanced forest. Being frugal, the data value he inserts should be minimal. Determine the minimal amount
// that a new node can have to allow creation of a balanced forest. If it's not possible to create a balanced forest, return -1.
//
//For example, you are given node values c=[15,12,8,14,13] and edges=[[1,2],[1,3],1,4],[4,5]]. It is the following tree:
//                 1,15
//             /    |    \
//         2,12     3,8  4,14
//                        |
//                       5,13
//The blue node is root, the first number in a node is node number and the second is its value. Cuts can be made between
// nodes 1 and 3 and nodes 1 and 4 to have three trees with sums 27, 27 and 8. Adding a new node w of c[w]=19 to
// the third tree completes the solution.
//Function Description
//
//Complete the balancedForest function in the editor below. It must return an integer representing the minimum value of c[w]
// that can be added to allow creation of a balanced forest, or -1 if it is not possible.
//
//balancedForest has the following parameter(s):
//
//c: an array of integers, the data values for each node
//edges: an array of 2 element arrays, the node pairs per edge
public class BalancedForest {
    static class Node {
        long sum;
        int value;
        int id;
        boolean visited, v2;
        Set<Node> adjacents = new HashSet();

        @Override
        public int hashCode() {
            return id;
        }
    }

    static long total = 0;
    static long min = Long.MAX_VALUE;

    //collects sums of nodes that are still in the stack of the recursive calls
    static Set<Long> foundSum = new HashSet<>();
    //collects sums of nodes that were left behind from the recursive calls
    static Set<Long> leftBehindSum = new HashSet<>();

    public static long balancedForest(List<Integer> c, List<List<Integer>> edges) {
        // Write your code here

        foundSum = new HashSet<>();
        leftBehindSum = new HashSet<>();

        List<Node> nodes = new ArrayList();
        for (int i = 0; i < c.size(); i++) {
            Node node = new Node();
            node.value = c.get(i);
            node.id = i + 1;
            nodes.add(node);
        }
        for (int i = 0; i < edges.size(); i++) {
            Node a = nodes.get(edges.get(i).get(0) - 1);
            Node b = nodes.get(edges.get(i).get(1) - 1);
            a.adjacents.add(b);
            b.adjacents.add(a);
        }

        //take first node as root, the tree is bidirectional
        Node root = nodes.get(0);
        //calculate sums of childs "under" every node
        total = dfs(root);
        min = total;

        //traverse the "tree" again and compare findings with current node or those that were left behind
        solve(root);
        return (min == total ? -1 : min);
    }

    static void solve(Node node) {
        if (node.v2) return;
        node.v2 =true;

        long leftForTwo = total - node.sum;
        long possibleMin = leftForTwo/2 - node.sum;

        //assuming current node is the smallest subtree that can be produced, explore options to validate that assumption
        if (leftForTwo % 2 == 0
                && (leftBehindSum.contains(leftForTwo/2)
                || foundSum.contains(leftForTwo/2+node.sum)
        ) && possibleMin >= 0) {
            min = Math.min(min, possibleMin);
        }

        //assuming current node is one of the two equal subtrees that can be produced, explore options to validate that assumption
        possibleMin = node.sum - (total - node.sum * 2);
        if ((leftBehindSum.contains(node.sum)
                || leftBehindSum.contains(total - node.sum * 2)
                || foundSum.contains(node.sum * 2)
                || foundSum.contains(total - node.sum)
        )&& possibleMin >= 0) {
            min = Math.min(min, possibleMin);
        }


        foundSum.add(node.sum);
        for(Node next : node.adjacents) {
            solve(next);
        };
        foundSum.remove(node.sum);
        leftBehindSum.add(node.sum);
    }

    static long dfs(Node node) {
        if (node.visited) return 0;
        node.visited = true;
        for (Node next : node.adjacents) {
            if (!next.visited) {
                node.sum += dfs(next);
            }
        }
        node.sum += node.value;
        return node.sum;
    }
}
