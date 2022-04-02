package com.example.console;
//Determine if a 9 x 9 com.example.console.Sudoku board is valid. Only the filled cells need to be validated according to the following rules:
//
//Each row must contain the digits 1-9 without repetition.
//Each column must contain the digits 1-9 without repetition.
//Each of the nine 3 x 3 sub-boxes of the grid must contain the digits 1-9 without repetition.
//Note:
//
//A com.example.console.Sudoku board (partially filled) could be valid but is not necessarily solvable.
//Only the filled cells need to be validated according to the mentioned rules.

//One could use box_index = (row / 3) * 3 + col / 3 where / is an integer division, row is a row number, and col is a column number.
//small box 3x3 index
// 0 1 2
// 3 4 5
// 6 7 8
public class Sudoku {
    public boolean isValidSudoku(char[][] board) {
        boolean[][] box = new boolean[9][9];
        boolean[][] rowCheck = new boolean[9][9];
        boolean[][] colCheck = new boolean[9][9];
        for(int row = 0; row < 9; row++) {
            for(int col = 0; col < 9; col++) {
                if(board[row][col] == '.')
                    continue;
                int boxIndex = (row / 3) * 3 + col / 3;
                int c = board[row][col] - '1';
                if(rowCheck[row][c] || box[boxIndex][c] || colCheck[c][col])
                    return false;

                rowCheck[row][c] = true;
                box[boxIndex][c] = true;
                colCheck[c][col] = true;
            }
        }

        return true;
    }
}
