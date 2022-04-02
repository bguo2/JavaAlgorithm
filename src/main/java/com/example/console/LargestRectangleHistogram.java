package com.example.console;

import java.util.Stack;

//Given n non-negative integers representing the histogram's bar height
// where the width of each bar is 1, find the area of largest rectangle in the histogram.
//Above is a histogram where width of each bar is 1, given height = [2,1,5,6,2,3].
//The largest rectangle is shown in the shaded area, which has area = 10 unit.
public class LargestRectangleHistogram {

    public static int largestRectangle(int[] arr) {
        if(arr == null || arr.length == 0)
            return 0;
        Stack<Integer> stack = new Stack<>();
        int n = arr.length, max = 0, curr;
        for (int i = 0; i <= n; ++i) {
            curr = i == n ? 0 : arr[i];
            while (!stack.isEmpty() && curr <= arr[stack.peek()]) {
                max = Math.max(max, arr[stack.pop()] * (stack.isEmpty() ? i : i - stack.peek() - 1));
            }
            stack.push(i);
        }
        return max;
    }

    //max rectangle
    //Given a rows x cols binary matrix filled with 0's and 1's, find the largest rectangle containing only 1's and return its area.
    //
    //
    //
    //Example 1:
    //
    //
    //Input: matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]
    //Output: 6
    //Explanation: The maximal rectangle is shown in the above picture.
    //Example 2:
    //
    //Input: matrix = []
    //Output: 0
    //Example 3:
    //
    //Input: matrix = [["0"]]
    //Output: 0
    //Example 4:
    //
    //Input: matrix = [["1"]]
    //Output: 1
    //Example 5:
    //
    //Input: matrix = [["0","0"]]
    //Output: 0
    public int maximalRectangle(char[][] matrix) {
        if(matrix.length == 0 || matrix[0].length == 0)
            return 0;
        int n = matrix.length;
        int m = matrix[0].length;
        int[][] height = new int[n][m];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(i==0) {
                    height[i][j] = matrix[i][j] - '0';
                    continue;
                }

                if(matrix[i][j] == '0')
                    height[i][j] = 0;
                else
                    height[i][j] = height[i-1][j] + 1;
            }
        }

        int max = 0;
        for(int i = 0; i < n; i++) {
            max = Math.max(max, largestRectangle(height[i]));
        }

        return max;
    }
}
