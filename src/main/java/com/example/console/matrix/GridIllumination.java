package com.example.console.matrix;

//You are given a grid of size N x N, and each cell of this grid has a lamp that is initially turned off.
//
//You are also given an array of lamp positions lamps, where lamps[i] = [rowi, coli] indicates that the lamp at grid[rowi][coli] is turned on. When a lamp is turned on, it illuminates its cell and all other cells in the same row, column, or diagonal.
//
//Finally, you are given a query array queries, where queries[i] = [rowi, coli]. For the ith query, determine whether grid[rowi][coli] is illuminated or not. After answering the ith query, turn off the lamp at grid[rowi][coli] and its 8 adjacent lamps if they exist. A lamp is adjacent if its cell shares either a side or corner with grid[rowi][coli].
//
//Return an array of integers ans, where ans[i] should be 1 if the lamp in the ith query was illuminated, or 0 if the lamp was not.
//
//
//
//Example 1:
//
//
//Input: N = 5, lamps = [[0,0],[4,4]], queries = [[1,1],[1,0]]
//Output: [1,0]
//Explanation: We have the initial grid with all lamps turned off. In the above picture we see the grid after turning on the lamp at grid[0][0] then turning on the lamp at grid[4][4].
//The 0th query asks if the lamp at grid[1][1] is illuminated or not (the blue square). It is illuminated, so set ans[0] = 1. Then, we turn off all lamps in the red square.
//
//The 1st query asks if the lamp at grid[1][0] is illuminated or not (the blue square). It is not illuminated, so set ans[1] = 0. Then, we turn off all lamps in the red rectangle.
//
//Example 2:
//
//Input: N = 5, lamps = [[0,0],[4,4]], queries = [[1,1],[1,1]]
//Output: [1,1]
//Example 3:
//
//Input: N = 5, lamps = [[0,0],[0,4]], queries = [[0,4],[0,1],[1,4]]
//Output: [1,1,0]
//
//
//Constraints:
//
//1 <= N <= 109
//0 <= lamps.length <= 20000
//lamps[i].length == 2
//0 <= lamps[i][j] < N
//0 <= queries.length <= 20000
//queries[i].length == 2
//0 <= queries[i][j] < N
public class GridIllumination {
    public int[] gridIllumination(int N, int[][] lamps, int[][] queries) {
        int[] result = new int[queries.length];
        int[][] grid = new int[N][N];
        for(int i = 0; i < lamps.length; i++) {
            lightOnOff(grid, lamps[i][0], lamps[i][1], 1);
        }
        //query
        int index = 0;
        for(int j = 0; j < queries.length; j++) {
            int row = queries[j][0];
            int col = queries[j][1];
            result[index++] = grid[row][col] > 0 ? 1 : 0;
            //turn off
            if(grid[row][col] > 0)
                turnOff(grid, row, col, lamps);
        }
        return result;
    }

    private void lightOnOff(int[][] grid, int row, int col, int onOff) {
        for(int i = 0; i < grid.length; i++) {
            grid[row][i] += onOff;
            if(grid[row][i] < 0)
                grid[row][i] = 0;
            grid[i][col] += onOff;
            if(grid[i][col] < 0)
                grid[i][col] = 0;
        }
        if(grid[row][col] > 0)
            grid[row][col] -= onOff;
        if(grid[row][col] < 0)
            grid[row][col] = 0;

        int curRow = row;
        int curCol = col;
        while(curRow >=0 && curCol >= 0) {
            if(curRow != row || curCol != col) {
                grid[curRow][curCol] += onOff;
                if(grid[curRow][curCol] < 0)
                    grid[curRow][curCol] = 0;
            }
            curRow--;
            curCol--;
        }
        curRow = row+1;
        curCol = col+1;
        while(curRow < grid.length && curCol < grid.length) {
            if(curRow != row || curCol != col) {
                grid[curRow][curCol] += onOff;
                if(grid[curRow][curCol] < 0)
                    grid[curRow][curCol] = 0;
            }
            curRow++;
            curCol++;
        }

    }

    private void turnOff(int[][] grid, int row, int col, int[][] lamps) {
        int[][] dirs = new int[][] {
                {0, 0},
                {0, -1},
                {0, 1},
                {-1, 0},
                {1, 0},
                {-1, -1},
                {1, 1},
                {-1, 1},
                {1, -1}
        };

        for(int i = 0; i < dirs.length; i++) {
            int curRow = row + dirs[i][0];
            int curCol = col + dirs[i][1];
            if(curRow < 0 || curRow > grid.length - 1 || curCol < 0 || curCol > grid.length - 1)
                continue;
            if(grid[curRow][curCol] > 0)
                grid[curRow][curCol]--;
            for(int j = 0; j < lamps.length; j++) {
                if(curRow == lamps[j][0] && curCol == lamps[j][1]) {
                    lightOnOff(grid, curRow, curCol, -1);
                }
            }
        }
    }
}
