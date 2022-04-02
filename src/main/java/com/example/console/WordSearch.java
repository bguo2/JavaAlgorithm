package com.example.console;

//Given a 2D board and a word, find if the word exists in the grid.
//
//The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once.
//
//For example,
//Given board =
//
//[
//  ["ABCE"],
//  ["SFCS"],
//  ["ADEE"]
//]
//word = "ABCCED", -> returns true,
//word = "SEE", -> returns true,
//word = "ABCB", -> returns false.
//
//
//
//这道题是典型的深度优先遍历 DFS 的应用，原二维数组就像是一个迷宫，可以上下左右四个方向行走，我们以二维数组中每一个数都作为起点和给定字符串做匹配，
// 我们还需要一个和原数组等大小的 visited 数组，是 bool 型的，用来记录当前位置是否已经被访问过，因为题目要求一个 cell 只能被访问一次。
// 如果二维数组 board 的当前字符和目标字符串 word 对应的字符相等，则对其上下左右四个邻字符分别调用 DFS 的递归函数，只要有一个返回 true，
// 那么就表示可以找到对应的字符串，否则就不能找到，具体看代码实现如下：
public class WordSearch {
    public boolean exist(char[][] board, String word) {
        int rows = board.length;
        int cols = board[0].length;
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                boolean found = find(board, word, i, j, 0);
                if(found)
                    return true;
            }
        }
        return false;
    }

    private boolean find(char[][] board, String word, int i, int j, int k) {
        int rows = board.length;
        int cols = board[0].length;
        if(i < 0 || i >= rows || j < 0 || j >=cols || board[i][j] != word.charAt(k))
            return false;
        if(board[i][j] == '#')
            return false;
        if(k == word.length() - 1)
            return true;
        char temp = board[i][j];
        board[i][j] = '#';
        boolean found = find(board, word, i-1, j, k+1)
                || find(board, word, i+1, j, k+1)
                || find(board, word, i, j-1, k+1)
                || find(board, word, i, j+1, k+1);
        board[i][j] = temp;
        return found;
    }

    //use for loop
    private boolean find1(char[][] board, String word, int i, int j, int k) {
        int rows = board.length;
        int cols = board[0].length;
        if(board[i][j] != word.charAt(k))
            return false;
        if(board[i][j] == '#')
            return false;
        if(k == word.length() - 1)
            return true;
        char temp = board[i][j];
        board[i][j] = '#';
        int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1, 0};
        boolean found = false;
        for(int l = 0; l < dx.length; l++) {
            int row = i + dx[l];
            int col = j + dy[l];
            if(row>=0 && row < rows && col>=0 && col<cols) {
                found = find(board, word, row, col, k+1);
                if(found)
                    break;
            }
        }

        board[i][j] = temp;
        return found;
    }
}
