package com.example.console.recursion;

import java.util.ArrayList;
import java.util.List;

//140. Word Break II
//Hard
//
//2940
//
//443
//
//Add to List
//
//Share
//Given a string s and a dictionary of strings wordDict, add spaces in s to construct a sentence where each word is a
// valid dictionary word. Return all such possible sentences in any order.
//
//Note that the same word in the dictionary may be reused multiple times in the segmentation.
//
//
//
//Example 1:
//
//Input: s = "catsanddog", wordDict = ["cat","cats","and","sand","dog"]
//Output: ["cats and dog","cat sand dog"]
//Example 2:
//
//Input: s = "pineapplepenapple", wordDict = ["apple","pen","applepen","pine","pineapple"]
//Output: ["pine apple pen apple","pineapple pen apple","pine applepen apple"]
//Explanation: Note that you are allowed to reuse a dictionary word.
//Example 3:
//
//Input: s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
//Output: []
//
//
//Constraints:
//
//1 <= s.length <= 20
//1 <= wordDict.length <= 1000
//1 <= wordDict[i].length <= 10
//s and wordDict[i] consist of only lowercase English letters.
//All the strings of wordDict are unique.
public class WordBreak2 {
    List<String> sentences;
    public List<String> wordBreak(String s, List<String> wordDict) {
        sentences = new ArrayList();
        dfs(s, wordDict, new ArrayList(), 0);
        return sentences;
    }

    private void dfs(String s, List<String> wordDict, List<String> words, int k) {
        if (k >= s.length()) {
            StringBuilder sb = new StringBuilder();
            int i = 0;
            sb.append(words.get(i++));
            for (; i < words.size(); i++) {
                sb.append(" ").append(words.get(i));
            }
            sentences.add(sb.toString());
            return;
        }

        for (String word : wordDict) {
            if (s.startsWith(word, k)) {
                List<String> newWords = new ArrayList(words);
                newWords.add(word);
                dfs(s, wordDict, newWords, k + word.length());
            }
        }
    }
}
