package com.example.console;

import java.util.HashMap;

public class Trie {
    private TrieNode root;
    private class TrieNode {
        String item;
        HashMap<Character, TrieNode> children = new HashMap<>();
    }

    public Trie() {
        root = new TrieNode();
    }

    public void insert(String word) {
        HashMap<Character, TrieNode> node = root.children;
        for(int i = 0; i < word.length(); i++) {
            TrieNode t;
            char c = word.charAt(i);
            if(node.containsKey(c)) {
                t = node.get(c);
            }
            else {
                t = new TrieNode();
                node.put(c, t);
            }
            node = t.children;
            if(i == word.length() - 1)
                t.item = word;
        }
    }

    private TrieNode searchNode(String word) {
        HashMap<Character, TrieNode> node = root.children;
        TrieNode t = null;
        for(int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if(!node.containsKey(c))
                return null;
            t = node.get(c);
            node = t.children;
        }

        return t;
    }

    public boolean prefixSearch(String prefix) {
        TrieNode t = searchNode(prefix);
        if(t != null)
            return true;
        return false;
    }

    public boolean search(String word) {
        TrieNode t = searchNode(word);
        if(t != null && t.item != null && t.item.equals(word))
            return true;
        return false;
    }
}
