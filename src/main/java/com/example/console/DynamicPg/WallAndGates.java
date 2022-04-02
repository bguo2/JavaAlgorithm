package com.example.console.DynamicPg;
//You are given a m x n 2D grid initialized with these three possible values.
//
//-1 - A wall or an obstacle.
//0 - A gate.
//INF - Infinity means an empty room. We use the value 231 - 1 = 2147483647 to represent INF as you may assume that the
// distance to a gate is less than 2147483647.
//Fill each empty room with the distance to its nearest gate. If it is impossible to reach a gate, it should be filled with INF.
//
//For example, given the 2D grid:
//INF  -1  0  INF
//INF INF INF  -1
//INF  -1 INF  -1
//  0  -1 INF INF
//After running your function, the 2D grid should be:
//  3  -1   0   1
//  2   2   1  -1
//  1  -1   2  -1
//  0  -1   3   4

// time complexity: O(mn)
import java.util.LinkedList;
import java.util.Queue;

public class WallAndGates {

    public void wallsAndGates(int[][] rooms) {
        int rs = rooms.length;
        int cs = rooms[0].length;
        Queue<int[]> queue = new LinkedList<>();
        for(int i = 0; i < rs; i++) {
            for(int j = 0; j < cs; j++) {
                if(rooms[i][j] == 0) {
                    queue.offer(new int[] {i, j, 0});
                }
            }
        }

        int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1, 0};
        while (!queue.isEmpty()) {
            int[] info = queue.poll();
            int row = info[0];
            int col = info[1];
            int step = info[2];
            for(int i = 0; i < 4; i++) {
                int nextRow = row + dy[i];
                int nextCol = col + dx[i];
                if(nextCol >= 0 && nextRow >=0 && nextRow < rs && nextCol < cs && rooms[nextRow][nextCol] == Integer.MAX_VALUE) {
                    rooms[nextRow][nextCol] = step+1;
                    queue.offer(new int[] {nextRow, nextCol, step+1});
                }
            }
        }
    }
}
