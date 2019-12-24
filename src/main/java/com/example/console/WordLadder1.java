package com.example.console;

import java.util.*;

public class WordLadder1 {
    private class Node {
        Node pre;
        int depth;
        String content;

        Node(String content, int depth, Node pre) {
            this.content = content;
            this.depth = depth;
            this.pre = pre;
        }
    }

    public List<List<String>> getResult(String start, String end, List<String> dict) {
        List<List<String>> result = new ArrayList<>();
        if(dict == null || dict.isEmpty())
            return result;
        HashSet<String> unvisited = new HashSet<>();
        for(String e: dict) {
            unvisited.add(e);
        }

        LinkedList<Node> queue = new LinkedList<>();
        queue.offer(new Node(start, 0, null));

        int minLen = Integer.MAX_VALUE;
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            if(result.size() > 0 && node.depth > minLen)
                return result;
            char[] chars = node.content.toCharArray();
            for(int i = 0; i < chars.length; i++) {
                for(char c = 'a'; c < 'z'; c++) {
                    char temp = chars[i];
                    chars[i] = c;
                    String newStr = new String(chars);
                    //matched
                    if(newStr.equals(end)) {
                        List<String> tmpResult = new ArrayList<>();
                        tmpResult.add(newStr);
                        Node p = node;
                        while (p != null) {
                            tmpResult.add(p.content);
                            p = p.pre;
                        }
                        Collections.reverse(tmpResult);
                        result.add(tmpResult);
                        if(node.depth <= minLen) {
                            minLen = node.depth;
                        }
                        else
                            return result;
                    }

                    if(unvisited.contains(newStr)) {
                        queue.add(new Node(newStr, node.depth+1, node));
                        unvisited.remove(newStr);
                    }

                    chars[i] = temp;
                }
            }
        }

        return result;
    }
}
