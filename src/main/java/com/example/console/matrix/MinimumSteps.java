package com.example.console.matrix;

import java.util.*;

/*
    given a matrix nxm, 1 means flat, 0 mean trench, 9 means a obstacle. A robot starts from [0][0] to
    remove the obstacle, it can move left, right , up and down, get the minimum steps to remove obstacle
    e.g. [[1,0,0]
          [1,0,0]
          [1,9,0]] it moves from [0,0], [1,0], [2,0], [2,1] => 3 steps.
 */
public class MinimumSteps {

    private class Node {
        private int x, y, dist;
        public Node(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }

    //algorithm:
    //create a queue and enqueue the start point
    //pop front node from the queue
    //if the popped node is 9, then it is done
    //else for each 4 adjacent nodes, enqueue each valid node to the queue with dist+1 and mark it as visited
    //until the queue is empty
    public int LeeAlgorithm(int[][] lots)
    {
        final int[] dx = {-1, 0, 0, 1};
        final int[] dy = {0, 1, -1, 0};
        int rows = lots.length;
        int columns = lots[0].length;
        Queue<Node> queue = new LinkedList<>();
        boolean[][] visited = new boolean[rows][columns];

        queue.offer(new Node(0,0, 0));
        visited[0][0] = true;
        while (!queue.isEmpty())
        {
            Node node = queue.poll();
            int value = lots[node.x][node.y];
            if(value == 9) {
                return node.dist;
            }

            for(int k = 0; k < 4; k++)
            {
                int i = node.x + dx[k];
                int j = node.y + dy[k];
                if(i >=0 && i < rows && j >=0 && j < columns && lots[i][j] != 0 && !visited[i][j])
                {
                    if(lots[i][j] == 9)
                        return node.dist + 1;
                    visited[i][j] = true;
                    queue.offer(new Node(i, j, node.dist+1));
                }
            }
        }

        return -1;
    }

    public int minSteps(int[][] lots)
    {
        int[] min = { Integer.MAX_VALUE};
        dfs(0, 0, 0, min, lots);
        return min[0];
    }

    private void dfs(int i, int j, int curSteps, int[] min, int[][] lots) {
        int rows = lots.length;
        int columns = lots[0].length;

        int tmp = lots[i][j];
        if(tmp == 2)
            return;
        if(tmp == 9) {
            min[0] = Math.min(curSteps, min[0]);
            return;
        }

        if(tmp == 1)
            lots[i][j] = 2;
        if(j - 1 >= 0 && lots[i][j-1] != 0)
            dfs(i, j - 1, curSteps+1, min, lots);
        if(j+1 < columns && lots[i][j+1] !=0)
            dfs(i, j + 1, curSteps+1, min, lots);
        if(i-1 >= 0 && lots[i-1][j] != 0)
            dfs(i - 1, j, curSteps+1, min, lots);
        if(i+1 < rows && lots[i+1][j] != 0)
            dfs(i + 1, j, curSteps+1, min, lots);
        if(tmp == 1)
            lots[i][j] = tmp;
        return;
    }

    //Shortest Path in a Grid with Obstacles Elimination
    //You are given an m x n integer matrix grid where each cell is either 0 (empty) or 1 (obstacle). You can move up, down, left, or right from and to an empty cell in one step.
    //
    //Return the minimum number of steps to walk from the upper left corner (0, 0) to the lower right corner (m - 1, n - 1) given that you can eliminate at most k obstacles. If it is not possible to find such walk return -1.
    //
    //
    //
    //Example 1:
    //
    //
    //Input: grid = [[0,0,0],[1,1,0],[0,0,0],[0,1,1],[0,0,0]], k = 1
    //Output: 6
    //Explanation:
    //The shortest path without eliminating any obstacle is 10.
    //The shortest path with one obstacle elimination at position (3,2) is 6. Such path is (0,0) -> (0,1) -> (0,2) -> (1,2) -> (2,2) -> (3,2) -> (4,2).
    //Example 2:
    //
    //
    //Input: grid = [[0,1,1],[1,1,1],[1,0,0]], k = 1
    //Output: -1
    //Explanation: We need to eliminate at least two obstacles to find such a walk.
    //
    //
    //Constraints:
    //
    //m == grid.length
    //n == grid[i].length
    //1 <= m, n <= 40
    //1 <= k <= m * n
    //grid[i][j] is either 0 or 1.
    //grid[0][0] == grid[m - 1][n - 1] == 0
    public int shortestPath(int[][] grid, int k) {
        int n = grid.length;
        int m = grid[0].length;

        if (k >= n+m-2) return m+n-2;

        // possible directions
        int[][] directions = new int[][] { {-1,0}, {1,0}, {0,-1}, {0,1} };

        // q of [row, col, eliminations used]
        Deque<int[]> q = new ArrayDeque<int[]>();

        // visited for each row,col,k
        boolean[][][] visited = new boolean[n][m][k+1];

        q.offer(new int[] {0,0,0,0});

        while (!q.isEmpty()){

            int[] curr = q.poll();
            int row = curr[0], col = curr[1], ks = curr[2], steps = curr[3];

            // if its the end, return it
            if (row == n-1 && col == m-1)
                return steps;

            // if already visited this situation, skip
            if (visited[row][col][ks])
                continue;

            // set this to visited
            visited[row][col][ks] = true;

            for (int[] dir : directions) {
                int r1 = row+dir[0], c1 = col+dir[1];
                if (r1<0 || r1>=n || c1<0 || c1>=m)
                    continue;

                if (grid[r1][c1] == 0 && !visited[r1][c1][ks]){
                    q.addLast(new int[] {r1,c1,ks,steps+1} );
                    continue;
                }

                //obstacle
                if (ks < k && !visited[r1][c1][ks+1])
                    q.addLast(new int[] {r1,c1,ks+1,steps+1} );
            }

        }

        return -1;
    }
}
