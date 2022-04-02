package com.example.console.DynamicPg;

import java.util.LinkedList;
import java.util.Queue;

//LeetCode – Shortest Distance from All Buildings (Java)
//matrix with 0: empty space, 1: building, 2: obstacle. build a building in empty space which is shortest
//to all buildings
//use distance[][] to record the current point[x][y] to all buildings
//reach[][] to record the building that the current point[x][y[ can reach
//then get the min
public class ShortestDistToAllBuildings {

    private class PointInfo {
        public int x, y, dist;
        public PointInfo(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }

    public int getShortestDistance(int[][] grid)
    {
        int[][] distance = new int[grid.length][grid[0].length];
        int[][] reach = new int[grid.length][grid[0].length];

        int numBuilding = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    helper(grid, distance, reach, i, j);
                    numBuilding++;
                }
            }
        }

        int result = Integer.MAX_VALUE;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 0 && reach[i][j] == numBuilding) {
                    result = Math.min(result, distance[i][j]);
                }
            }
        }

        return result == Integer.MAX_VALUE ? -1 : result;
    }

    private void helper(int[][] grid, int[][] distance, int[][] reach, int i, int j) {

        int[] dx = new int[] {-1, 0, 1, 0};
        int[] dy = new int[] {0, 1, 0, -1};

        boolean[][] visited = new boolean[grid.length][grid[0].length];
        //two queue, one for direction, one for distance tracking
        LinkedList<PointInfo> q = new LinkedList<>();

        q.offer(new PointInfo(i, j, 1));

        while (!q.isEmpty()) {
            PointInfo point = q.poll();

            for (int k = 0; k < 4; k++) {
                int x = point.x + dx[k];
                int y = point.y + dy[k];

                if (x >= 0 && y >= 0 && x < grid.length && y < grid[0].length && grid[x][y] == 0 && !visited[x][y]) {
                    visited[x][y] = true;
                    q.offer(new PointInfo(x, y, point.dist+1));
                    distance[x][y] += point.dist;
                    reach[x][y]++;
                }
            }
        }
    }
}
