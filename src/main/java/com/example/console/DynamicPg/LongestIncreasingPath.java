package com.example.console.DynamicPg;

//Given an integer matrix, find the length of the longest increasing path.
//
//From each cell, you can either move to four directions: left, right, up or down. You may NOT move diagonally
// or move outside of the boundary (i.e. wrap-around is not allowed).
//
//Example 1:
//
//Input: nums =
//[
//  [9,9,4],
//  [6,6,8],
//  [2,1,1]
//]
//Output: 4
//Explanation: The longest increasing path is [1, 2, 6, 9].
//Example 2:
//
//Input: nums =
//[
//  [3,4,5],
//  [3,2,6],
//  [2,2,1]
//]
//Output: 4
//Explanation: The longest increasing path is [3, 4, 5, 6]. Moving diagonally is not allowed.
//O(n*m)
public class LongestIncreasingPath {
    public int longestIncreasingPath(int[][] matrix) {
        if(matrix == null || matrix.length == 0)
            return 0;
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] mem = new int[rows][cols];
        int max = Integer.MIN_VALUE;
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                max = Math.max(max, helper(matrix, mem, i, j));
            }
        }

        return max;
    }

    private int helper(int[][] matrix, int[][]mem, int row, int col) {
        if(mem[row][col] > 0)
            return mem[row][col];
        int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1, 0};
        int mx = 1;
        for(int i = 0; i < 4; i++) {
            int m = row + dx[i];
            int n = col + dy[i];
            if(m >=0 && m < matrix.length && n >=0 && n < matrix[0].length && matrix[m][n] > matrix[row][col]) {
                int len = 1 + helper(matrix, mem, m, n);
                mx = Math.max(len, mx);
            }
        }

        mem[row][col] = mx;
        return mx;
    }
}
