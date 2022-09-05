package com.example.console.matrix;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

//On an N x N grid, each square grid[i][j] represents the elevation at that point (i,j).
//
//Now rain starts to fall. At time t, the depth of the water everywhere is t. You can swim from a square to another
// 4-directionally adjacent square if and only if the elevation of both squares individually are at most t. You can
// swim infinite distance in zero time. Of course, you must stay within the boundaries of the grid during your swim.
//
//You start at the top left square (0, 0). What is the least time until you can reach the bottom right square (N-1, N-1)?
//
//Example 1:
//
//Input: [[0,2],[1,3]]
//Output: 3
//Explanation:
//At time 0, you are in grid location (0, 0).
//You cannot go anywhere else because 4-directionally adjacent neighbors have a higher elevation than t = 0.
//
//You cannot reach point (1, 1) until time 3.
//When the depth of water is 3, we can swim anywhere inside the grid.
//Example 2:
//
//Input: [[0,1,2,3,4],[24,23,22,21,5],[12,13,14,15,16],[11,17,18,19,20],[10,9,8,7,6]]
//Output: 16
//Explanation:
// 0  1  2  3  4
//24 23 22 21  5
//12 13 14 15 16
//11 17 18 19 20
//10  9  8  7  6
//
//The final route is marked in bold. 0->1->2->3->4->5->16->15->14->13->12->11->10->9->8->7->6
//We need to wait until time 16 so that (0, 0) and (4, 4) are connected.
public class SwimInRisingWater {
    //use PriorityQueue instead of Queue to get min result
    private class Node {
        int row, col, minWaitTime;
        public Node(int row, int col, int minWaitTime) {
            this.row = row;
            this.col = col;
            this.minWaitTime = minWaitTime;
        }
    }

    public int getMinWaitTime(int[][] matrix) {
        //validation
        int rows = matrix.length;
        int cols = matrix[0].length;
        boolean[][] visited = new boolean[rows][cols];
        int[] dx = {0, 0, -1, 1};
        int[] dy = {-1, 1, 0, 0};
        PriorityQueue<Node> queue = new PriorityQueue<>((o1, o2) -> {
                return o1.minWaitTime - o2.minWaitTime;
            });

        queue.offer(new Node(0, 0, 0));
        int min = 0;
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            if(node.row == rows - 1 && node.col == cols - 1)
                return node.minWaitTime;
            for(int k = 0; k < 4; k++) {
                //not visited before
                int i = node.row + dy[k];
                int j = node.col + dx[k];
                if(i > -1 && i < rows && j > -1 && j < cols && !visited[i][j]) {
                    int waitTime = (matrix[i][j] - matrix[node.row][node.col]) > 0 ? matrix[i][j] - matrix[node.row][node.col] : 0;
                    visited[i][j] = true;
                    queue.offer(new Node(i, j,waitTime + node.minWaitTime));
                }
            }
        }

        return 0;
    }
}
