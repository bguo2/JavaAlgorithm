package com.example.console;

import java.util.*;
//Given two words (beginWord and endWord), and a dictionary's word list, find all shortest transformation sequence(s) from beginWord to endWord, such that:
//
//Only one letter can be changed at a time
//Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
//Note:
//
//Return an empty list if there is no such transformation sequence.
//All words have the same length.
//All words contain only lowercase alphabetic characters.
//You may assume no duplicates in the word list.
//You may assume beginWord and endWord are non-empty and are not the same.
//Example 1:
//
//Input:
//beginWord = "hit",
//endWord = "cog",
//wordList = ["hot","dot","dog","lot","log","cog"]
//
//Output:
//[
//  ["hit","hot","dot","dog","cog"],
//  ["hit","hot","lot","log","cog"]
//]
//Example 2:
//
//Input:
//beginWord = "hit"
//endWord = "cog"
//wordList = ["hot","dot","dog","lot","log"]
//
//Output: []
//
//Explanation: The endWord "cog" is not in wordList, therefore no possible transformation.
//"red"
//"tax"
//["ted","tex","red","tax","tad","den","rex","pee"]
//[["red","ted","tad","tax"],["red","ted","tex","tax"],["red","rex","tex","tax"]]
public class WordLadder1 {
    private class WordNode {
        String word;
        int step;
        WordNode preNode;
        private WordNode(String word, int step, WordNode preNode) {
            this.word = word;
            this.step = step;
            this.preNode = preNode;
        }
    }

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> result = new ArrayList<>();
        if(beginWord == null || beginWord.isEmpty())
            return result;
        if(endWord == null || endWord.isEmpty())
            return result;
        if(wordList == null || wordList.isEmpty())
            return result;
        HashSet<String> set = new HashSet<>(wordList);
        if(!set.contains(endWord))
            return result;
        Queue<WordNode> queue = new LinkedList<>();
        queue.add(new WordNode(beginWord, 1, null));
        int min = Integer.MAX_VALUE;
        while(!queue.isEmpty()) {
            WordNode node = queue.poll();
            if(node.word.equals(endWord)) {
                return result;
            }
            char[] arrs = node.word.toCharArray();
            for(int i = 0; i < arrs.length; i++) {
                char tmp = arrs[i];
                for(char c = 'a'; c <= 'z'; c++) {
                    if(arrs[i] == c)
                        continue;
                    arrs[i] = c;
                    String newStr = new String(arrs);
                    if(newStr.equals(endWord)) {
                        WordNode tmpNode = new WordNode(newStr, node.step+1, node);
                        if(min == Integer.MAX_VALUE)
                            min = tmpNode.step;
                        else if(tmpNode.step > min)
                            return result;
                        List<String> tmpResult = getResult(tmpNode);
                        result.add(tmpResult);
                    }

                    if(set.contains(newStr)) {
                        if(!newStr.equals(endWord))
                            queue.offer(new WordNode(newStr, node.step+1, node));
                    }
                    arrs[i] = tmp;
                }
            }
        }

        return result;
    }

    private List<String> getResult(WordNode curNode) {
        WordNode node = curNode;
        LinkedList<String> result = new LinkedList<>();
        while(node != null) {
            result.addFirst(node.word);
            node = node.preNode;
        }

        return result;
    }
}
