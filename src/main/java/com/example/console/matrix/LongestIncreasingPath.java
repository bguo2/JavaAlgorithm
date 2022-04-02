package com.example.console.matrix;

//Given an integer matrix, find the length of the longest increasing path.
//
//From each cell, you can either move to four directions: left, right, up or down. You may NOT move diagonally or move outside of the boundary (i.e. wrap-around is not allowed).
//
//Example 1:
//
//nums = [
//  [9,9,4],
//  [6,6,8],
//  [2,1,1]
//]
//
//
//Return 4
//The longest increasing path is [1, 2, 6, 9].
//
//Example 2:
//
//nums = [
//  [3,4,5],
//  [3,2,6],
//  [2,2,1]
//]
//
//
//Return 4
//The longest increasing path is [3, 4, 5, 6]. Moving diagonally is not allowed.
public class LongestIncreasingPath {
    public int longestIncreasingPath(int[][] matrix) {
        int result = 0;
        int m = matrix.length;
        int n = matrix[0].length;

        int[][] mem = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int t = helper(matrix, mem, i, j);
                result = Math.max(result, t);
            }
        }

        return result;
    }

    private int helper(int[][] matrix, int[][] mem, int i, int j) {
        if (mem[i][j] > 0) {
            return mem[i][j];
        }

        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};

        int mx = 1;
        for (int k = 0; k < 4; k++) {
            int x = i + dx[k];
            int y = j + dy[k];

            if (x >= 0 && y >= 0 && x < matrix.length && y < matrix[0].length && matrix[x][y] > matrix[i][j]) {
                int len = helper(matrix, mem, x, y) + 1;
                mx = Math.max(mx, len);
            }
        }

        mem[i][j] = mx;
        return mx;
    }
}
