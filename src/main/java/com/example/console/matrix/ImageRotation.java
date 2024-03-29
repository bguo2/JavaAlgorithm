package com.example.console.matrix;

//You are given an n x n 2D matrix representing an image.
//
//Rotate the image by 90 degrees (clockwise).
//
//Note:
//
//You have to rotate the image in-place, which means you have to modify the input 2D matrix directly.
// DO NOT allocate another 2D matrix and do the rotation.
//
//Example 1:
//
//Given input matrix =
//[
//  [1,2,3],
//  [4,5,6],
//  [7,8,9]
//],
//
//rotate the input matrix in-place such that it becomes:
//[
//  [7,4,1],
//  [8,5,2],
//  [9,6,3]
//]
//Example 2:
//
//Given input matrix =
//[
//  [ 5, 1, 9,11],
//  [ 2, 4, 8,10],
//  [13, 3, 6, 7],
//  [15,14,12,16]
//],
//
//rotate the input matrix in-place such that it becomes:
//[
//  [15,13, 2, 5],
//  [14, 3, 4, 1],
//  [12, 6, 8, 9],
//  [16, 7,10,11]
//]
public class ImageRotation {
    //transposed matrix: row=>column column=>row, then swap columns
    public static void rotateClockwise(int[][] matrix) {
        int n = matrix.length;
        for(int i = 0; i < n;  i++) {
            for(int j = i + 1; j < n; j++) {
                swap(matrix, i, j, j, i);
            }
            reverse(matrix[i]);
        }
    }

    //trasponse first, then swap rows
    public static void rotateAntiClockwise(int[][] matrix) {
        int n = matrix.length;
        for(int i = 0; i < n;  i++) {
            for(int j = i + 1; j < n; j++) {
                swap(matrix, i, j, j, i);
            }
        }

        int n1 = n;
        for(int i = 0; i < n;  i++) {
            for(int j = 0; j < n1; j++) {
                swap(matrix, i, j, n1-1-i, j);
            }
            n--;
        }
    }

    private static void swap(int[][] matrix, int i, int j, int i1, int j1) {
        int tmp = matrix[i][j];
        matrix[i][j] = matrix[i1][j1];
        matrix[i1][j1] = tmp;
    }

    private static void reverse(int[] row) {
        int j = row.length - 1;
        int i = 0;
        while(i < j) {
            int tmp = row[i];
            row[i] = row[j];
            row[j] = tmp;
            i++;
            j--;
        }
    }
}
