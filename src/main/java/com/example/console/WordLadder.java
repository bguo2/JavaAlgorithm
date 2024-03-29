package com.example.console;

import java.util.*;

//Given two words (start and end), and a dictionary, find the length of shortest transformation sequence from start to end,
// such that only one letter can be changed at a time and each intermediate word must exist in the dictionary. For example, given:
//
//start = "hit"
//end = "cog"
//dict = ["hot","dot","dog","lot","log", "cog"]
//One shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog", the program should return its length 5.
public class WordLadder {
    private class WordNode {
        private String word;
        private int stepNum;

        private WordNode(String word, int step){
            this.word = word;
            this.stepNum = step;
        }
    }

    public int getShortest(String startWord, String endWord, List<String> words) {
        if(startWord == null || startWord.length() == 0 || endWord == null || endWord.isEmpty() || words == null || words.isEmpty())
            return 0;

        HashSet<String> wordDict = new HashSet<>(words);
        if(!wordDict.contains(endWord))
            return 0;
        Queue<WordNode> queue = new LinkedList<>();
        queue.offer(new WordNode(startWord, 1));

        while (!queue.isEmpty()){
            WordNode node = queue.poll();
            if(node.word.equals(endWord))
                return node.stepNum;
            char[] arr = node.word.toCharArray();
            int curStep = node.stepNum;
            for(int i = 0; i < arr.length; i++) {
                char tmp = arr[i];
                for(char c = 'a'; c <= 'z'; c++) {
                    arr[i] = c;
                    String word = new String(arr);
                    if(wordDict.contains(word)) {
                        curStep++;
                        if(word.equals(endWord))
                            return curStep;
                        wordDict.remove(word);
                        queue.offer(new WordNode(word, curStep));
                    }
                    arr[i] = tmp;
                }
            }
        }

        return 0;
    }
}
