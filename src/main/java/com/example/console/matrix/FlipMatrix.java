package com.example.console.matrix;
//Remove All Ones With Row and Column Flips
//You are given an m x n binary matrix grid.
//
//In one operation, you can choose any row or column and flip each value in that row or column
// (i.e., changing all 0's to 1's, and all 1's to 0's).
//
//Return true if it is possible to remove all 1's from grid using any number of operations or false otherwise.
//
//
//
//Example 1:
//
//
//Input: grid = [[0,1,0],[1,0,1],[0,1,0]]
//Output: true
//Explanation: One possible way to remove all 1's from grid is to:
//- Flip the middle row
//- Flip the middle column
//Example 2:
//
//
//Input: grid = [[1,1,0],[0,0,0],[0,0,0]]
//Output: false
//Explanation: It is impossible to remove all 1's from grid.
//Example 3:
//
//
//Input: grid = [[0]]
//Output: true
//Explanation: There are no 1's in grid.
//
//
//Constraints:
//
//m == grid.length
//n == grid[i].length
//1 <= m, n <= 300
//grid[i][j] is either 0 or 1.
public class FlipMatrix {
    //The big matrix is m * n, no matter how big it is, it is made by 2*2 small matrix.
    //
    //When you have odd number of 0 or 1 in a 2*2 small matrix, you cannot flip all elements in big matrix into 0.
    //e.g.
    //0 1 |||| 0 0
    //1 1 |||| 1 0
    //
    //So all we need to do is check every 2*2 small matrix in the big matrix, to see
    // if there is odd number of 0 or 1 in a small matrix. If there is, we can know the big matrix cannot be flipped.
    //
    //Time Complexity: O(m-1)(n-1) -> O(mn)
    public boolean removeOnes(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        //add special condition handling here: m=1 or n=1
        for (int i = 0; i < m - 1; ++i) {
            for (int j = 0; j < n - 1; ++j) {
                if (check(grid, i, j)) return false;
            }
        }
        return true;
    }

    private boolean check(int[][] grid, int i, int j) {
        int count = 0;
        count += grid[i][j];
        count += grid[i][j + 1];
        count += grid[i + 1][j];
        count += grid[i + 1][j + 1];
        return count % 2 == 1;
    }
}
