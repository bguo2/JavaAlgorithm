package com.example.console;

import java.lang.reflect.Array;
import java.util.*;

//Given an m x n matrix of non-negative integers representing the height of each unit cell in a continent, the "Pacific ocean"
// touches the left and top edges of the matrix and the "Atlantic ocean" touches the right and bottom edges.
//
//Water can only flow in four directions (up, down, left, or right) from a cell to another one with height equal or lower.
//
//Find the list of grid coordinates where water can flow to both the Pacific and Atlantic ocean.
//
//Note:
//
//The order of returned grid coordinates does not matter.
//Both m and n are less than 150.
//
//
//Example:
//
//Given the following 5x5 matrix:
//
//  Pacific ~   ~   ~   ~   ~
//       ~  1   2   2   3  (5) *
//       ~  3   2   3  (4) (4) *
//       ~  2   4  (5)  3   1  *
//       ~ (6) (7)  1   4   5  *
//       ~ (5)  1   1   2   4  *
//          *   *   *   *   * Atlantic
//
//Return:
//
//[[0, 4], [1, 3], [1, 4], [2, 2], [3, 0], [3, 1], [4, 0]] (positions with parentheses in above matrix).
public class PacificAtlanticWaterFlow {
    //Add all boundary of Pacific ocean into queue. Do bfs and then reuse the same queue,
    // if this point hasn't been visited and the value is bigger or equal to the current point value,
    // then put it into queue and make it visited. Put all Atlantic ocean boundary into queue do the same bfs.
    // We can't add one point to the queue twice, it will cause duplicate. If the point can be touched from Pacific ocean, add it into pac,
    // if the point can be touched from Atlantic ocean, add it into alt. If one point is both in pac and alt, add it to res.

    public List<int[]> getResult(int[][] matrix) {
        List<int[]> res = new ArrayList<>();
        int rows = matrix.length;
        int cols = matrix[0].length;
        boolean[][] pac = new boolean[rows][cols];
        boolean[][] atl = new boolean[rows][cols];

        Queue<int[]> queue = new LinkedList<>();
        //top
        for(int i = 0; i < cols; i++) {
            queue.offer(new int[] {0, i});
            pac[i][0] = true;
        }
        //left
        for(int j = 0; j < rows; j++) {
            queue.offer(new int[] {j, 0});
            pac[j][0] = true;
        }
        bfs(matrix, res, pac, atl, queue);

        //bottom
        for(int i = 0; i < cols; i++) {
            queue.offer(new int[] { rows - 1, i});
            atl[rows-1][i] = true;
        }
        //right
        for(int j = 0; j < rows; j++) {
            queue.offer(new int[] {j, cols-1});
            atl[j][cols-1] = true;
        }
        bfs(matrix, res, atl, pac, queue);
        Collections.sort(res, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[0] == o2[0])
                    return o1[1] - o2[1];
                return o1[0] - o2[0];
            }
        });
        return res;
    }

    private void bfs(int[][] matrix, List<int[]> res, boolean[][] source, boolean[][]target, Queue<int[]>queue) {
        int[] dRow = {0, 0, -1, 1};
        int[] dCol = {-1, 1, 0, 0};
        int rows = matrix.length;
        int cols = matrix[0].length;
        while (!queue.isEmpty()) {
            int[] curPos = queue.poll();
            int curRow = curPos[0];
            int curCol = curPos[1];
            //cann be reached via both
            if(source[curRow][curCol] && target[curRow][curCol])
                res.add(curPos);
            for(int i = 0; i < 4; i++) {
                int nextRow = curRow + dRow[i];
                int nextCol = curCol + dCol[i];
                if(nextRow >=0 && nextRow < rows && nextCol >=0 && nextCol < cols && source[nextRow][nextCol] == false &&
                    matrix[curRow][curCol] <= matrix[nextRow][nextCol]) {
                    source[nextRow][nextCol] = true;
                    queue.offer(new int[] {nextRow, nextCol});
                }
            }
        }
    }
}
