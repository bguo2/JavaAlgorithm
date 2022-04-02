package com.example.console.DynamicPg;

import java.util.Arrays;

//Given a list of words, each word consists of English lowercase letters.
//
//Let's say word1 is a predecessor of word2 if and only if we can add exactly one letter anywhere in word1 to make it equal to word2.
// For example, "abc" is a predecessor of "abac".
//
//A *word chain *is a sequence of words [word_1, word_2, ..., word_k] with k >= 1, where word_1 is a predecessor of word_2,
// word_2 is a predecessor of word_3, and so on.
//
//Return the longest possible length of a word chain with words chosen from the given list of words.
//
//Example 1:
//
//Input: words = ["a","b","ba","bca","bda","bdca"]
//Output: 4
//Explanation: One of the longest word chain is "a","ba","bda","bdca".
//Example 2:
//
//Input: words = ["xbc","pcxbcf","xb","cxbc","pcxbc"]
//Output: 5
//Constraints:
//
//1 <= words.length <= 1000
//1 <= words[i].length <= 16
//words[i] only consists of English lowercase letters.

//首先来定义 dp 数组，这里用一个一维的数组就行了，其中 dp[i] 表示 [0, i] 区间的单词的最长的前任链。下面来推导状态转移方程，
// 对于当前位置的单词，需要遍历前面所有的单词，这里需要先给单词按长度排个序，因为只有长度小1的单词才有可能是前任，
// 所以只需要遍历之前所有长度正好小1的单词，若是前任关系，则用其 dp 值加1来更新当前 dp 值即可。判断前任关系可以放到一个子数组中来做，
// 其实就是检测是否是子序列，没啥太大的难度
public class LongestStringChain {
    public int longestStrChain(String[] words) {
        Arrays.sort(words, (a, b) -> (a.length() - b.length()));
        int n = words.length;
        int[] dp = new int[n];
        int max = 1;
        for(int i=0; i<n; i++) {
            dp[i] = 1;
            for(int j=0; j < i; j++) {
                if(words[j].length() + 1 != words[i].length())
                    continue;
                if(isPredecessor(words[j], words[i])) {
                    dp[i] = Math.max(dp[i], 1 + dp[j]);
                }
            }
            max = Math.max(max,dp[i]);
        }
        return max;
    }

    private boolean isPredecessor(String word1, String word2) {
        int i = 0;
        int n = word2.length();
        for(int j = 0; j < n; j++) {
            if(word1.charAt(i) == word2.charAt(j))
                i++;
            if(i == word1.length())
                return true;
        }
        return i == word1.length();
    }
}
