package com.example.console;

import java.util.*;

//Given an m x n board of characters and a list of strings words, return all words on the board.
//
//Each word must be constructed from letters of sequentially adjacent cells, where adjacent cells are horizontally or vertically
// neighboring. The same letter cell may not be used more than once in a word.
//Input: board = [["o","a","a","n"],["e","t","a","e"],["i","h","k","r"],["i","f","l","v"]], words = ["oath","pea","eat","rain"]
//Output: ["eat","oath"]
//Input: board = [["a","b"],["c","d"]], words = ["abcb"]
//Output: []
//[["a","a"]]
//["a"]
public class WordSearch1 {
    private Set<String> result = new HashSet<>();
    private boolean[][] visited;
    private Trie trie = new Trie();

    public List<String> findWords(char[][] board, String[] words) {
        for (String word : words) {
            trie.insert(word);
        }

        int rows = board.length;
        int cols = board[0].length;
        visited = new boolean[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                findWords(board, i, j, "");
            }
        }
        return new ArrayList<String>(result);
    }

    private void findWords(char[][] board, int i, int j, String temp) {
        int rows = board.length;
        int cols = board[0].length;
        if (i < 0 || i > rows - 1 || j < 0 || j > cols - 1)
            return;
        if (visited[i][j])
            return;
        String newStr = temp + board[i][j];
        if (!trie.startWith(newStr))
            return;
        if (trie.search(newStr) && !result.contains(newStr)) {
            result.add(newStr);
        }

        visited[i][j] = true;
        findWords(board, i, j - 1, newStr);
        findWords(board, i, j + 1, newStr);
        findWords(board, i - 1, j, newStr);
        findWords(board, i + 1, j, newStr);
        visited[i][j] = false;
    }

    private class TrieNode {
        HashMap<Character, TrieNode> children = new HashMap();
        String item;
    }

    class Trie {
        private TrieNode root = new TrieNode();

        public void insert(String word) {
            HashMap<Character, TrieNode> node = root.children;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                TrieNode t;
                if (!node.containsKey(c)) {
                    t = new TrieNode();
                    node.put(c, t);
                } else
                    t = node.get(c);
                node = t.children;
                if (i == word.length() - 1)
                    t.item = word;
            }
        }

        public TrieNode searchNode(String search) {
            HashMap<Character, TrieNode> node = root.children;
            TrieNode t = null;
            for (int i = 0; i < search.length(); i++) {
                char c = search.charAt(i);
                if (!node.containsKey(c))
                    return null;
                t = node.get(c);
                node = t.children;
            }

            return t;
        }

        public boolean search(String word) {
            TrieNode t = searchNode(word);
            if (t != null && t.item != null && t.item.equals(word))
                return true;
            return false;
        }

        public boolean startWith(String prefix) {
            TrieNode t = searchNode(prefix);
            if (t == null)
                return false;
            return true;
        }
    }
}
