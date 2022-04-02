package com.example.console.recursion;

import java.util.HashSet;
import java.util.Set;

//Given a string s and a dictionary of words dict, determine if s can be segmented into a space-separated sequence of
// one or more dictionary words.
//
//For example, given
//s = "leetcode",
//dict = ["leet", "code"].
//
//Return true because "leetcode" can be segmented as "leet code".
//s = "abcd"
//dict = ["a", "abc", "b", "d"] => true
//s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"] => false;
public class WordBreak {
    //Time complexity : O(2^n)
    // ). Given an array of length n, there are n+1 ways to split it into two parts. At each step, we have a choice:
    // to split or not to split. In the worse case, when all choices are to be checked, that results in O(2^n)
    // brute force
    public static boolean wordBreak(String s, Set<String> dict) {
        //return match(s, dict, 0);
        return wordBreak1(s, dict);
    }

    //O(2^^n)
    private static boolean match(String s, Set<String> set, int start) {
        if(start == s.length())
            return true;
        for(int i = start+1; i <= s.length(); i++) {
            String subStr = s.substring(start, i);
            if(set.contains(subStr)) {
                if(match(s, set, i))
                    return true;
            }
        }

        return false;
    }

    //memorize
    public static boolean wordBreak1(String s, Set<String> dict) {
        Boolean[] mem = new Boolean[s.length()];
        return match1(s, dict, 0, mem);
    }

    //O(n^^2). equal to for(i=0; i< n; i++) {
    // for(j=i; j<n; j++)
    //}
    private static boolean match1(String s, Set<String> set, int start, Boolean[] mem) {
        if(start == s.length())
            return true;
        if(mem[start] != null)
            return mem[start];
        for(int i = start; i < s.length(); i++) {
            String subStr = s.substring(start, i+1);
            if(set.contains(subStr)) {
                if(match1(s, set, i+1, mem)) {
                    mem[start] = true;
                    return true;
                }
            }
        }

        mem[start] = false;
        return false;
    }
}
