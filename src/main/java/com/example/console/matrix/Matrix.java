package com.example.console.matrix;

import java.util.ArrayList;
import java.util.List;
//print matrix diagonally from left to top
//1,2
//3,4
//5,6
//or 1,2,3,4
//   5,6,7,8
public class Matrix {
    //from left to top
    public List<Integer> printDiagonally(int[][] m) {
        List<Integer> result = new ArrayList<>();
        if(m == null || m.length == 0)
            return result;
        int rows = m.length;
        int cols = m[0].length;
        int row = 0, col = 0;
        for(int i = 0; i < rows+cols-1; i++) {
            //move along first column
            if(i < rows) {
                row = i;
                col = 0;
            }
            //move along last row
            else {
                row = rows - 1;
                col = i - rows + 1;
            }

            while(row >= 0 && col < cols) {
                result.add(m[row][col]);
                row--;
                col++;
            }
        }

        return result;
    }

    //print matrix diagonally from top to left
    public List<Integer> printDiagonallyFromTop(int[][] m) {
        int rows = m.length;
        int cols = m[0].length;
        List<Integer> result = new ArrayList<>();
        int row = 0, col = 0;
        for(int i = 0; i < rows+cols-1; i++) {
            //move along first row
            if(i < cols) {
                row = 0;
                col = i;
            }
            //move along last column
            else {
                row = i - cols + 1;
                col = cols - 1;
            }
            while(row < rows && col >= 0) {
                result.add(m[row][col]);
                row++;
                col--;
            }
        }

        return result;
    }

    //Given a matrix of M x N elements (M rows, N columns), return all elements of the matrix in diagonal order as
    // shown in the below image.
    //
    //
    //
    //Example:
    //
    //Input:
    //[
    // [ 1, 2, 3 ],
    // [ 4, 5, 6 ],
    // [ 7, 8, 9 ]
    //]
    //
    //Output:  [1,2,4,7,5,3,6,8,9]
    //
    //Explanation:
    //
    //
    //
    //Note:
    //
    //The total number of elements of the given matrix will not exceed 10,000.
    public int[] findDiagonalOrder(int[][] matrix) {
        if(matrix == null || matrix.length == 0)
            return new int[0];

        int rows = matrix.length;
        int cols = matrix[0].length;
        int count = rows+cols - 1;
        int row = 0, col = 0;
        int index = 0;
        int[] result = new int[rows*cols];
        for(int i = 0; i < count; i++) {
            //even number: move along first column, last row
            if(i % 2 == 0) {
                if( i < rows) {
                    row = i;
                    col = 0;
                }
                else {
                    row = rows - 1;
                    col = i - rows + 1;
                }
                while(row >= 0 && col < cols){
                    result[index] = matrix[row][col];
                    index++;
                    row--;
                    col++;
                }
            }
            //odd number: move along first row, last column
            else {
                if(i < cols) {
                    row = 0;
                    col = i;
                }
                else {
                    row = i - cols + 1;
                    col = cols - 1;
                }
                while(row < rows && col >= 0) {
                    result[index] = matrix[row][col];
                    index++;
                    row++;
                    col--;
                }
            }
        }

        return result;
    }
}
