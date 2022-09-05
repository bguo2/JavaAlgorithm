package com.example.console.recursion;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

//Examples:
//pattern = "abab", str = "redblueredblue" should return true.
//pattern = "aaaa", str = "asdasdasdasd" should return true.
//pattern = "aabb", str = "xyzabcxzyabc" should return false.
//O (n^m) m: pattern
public class WordPatternMatch {
    public boolean patternMatch(String pattern, String str) {
        if(pattern.length()==0 && str.length()==0)
            return true;
        if(pattern.length()==0)
            return false;
        Map<Character, String> map = new HashMap<Character, String>();
        Set<String> set = new HashSet<>();
        return helper(pattern, str, 0, 0, map, set);
    }

    //time complexity: 2^(n+m)
    //space: O(n+m)
    private boolean helper(String pattern, String str, int i, int j, Map<Character, String> map, Set<String> set){
        if(i==pattern.length() && j==str.length()){
            return true;
        }

        if(i>=pattern.length() || j>=str.length())
            return false;

        char c = pattern.charAt(i);
        for(int k=j; k<str.length(); k++) {
            String sub = str.substring(j, k+1);
            if(map.containsKey(c) && map.get(c).equals(sub)) {
                if(helper(pattern, str, i+1, k+1, map, set))
                    return true;
            }
            else if(!map.containsKey(c)) {
                //ab => aa (a->a, b->a a is in set, should return false) or aba => aaaa (a match a, b match aa, should return true)
                if(set.contains(sub))
                    continue;
                map.put(c, sub);
                set.add(sub);
                if(helper(pattern, str, i+1, k+1, map, set))
                    return true;
                map.remove(c);
                set.remove(sub);
            }
        }

        return false;
    }

    //Given a pattern and a string s, find if s follows the same pattern.
    //
    //Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty word in s.
    //
    //
    //
    //Example 1:
    //
    //Input: pattern = "abba", s = "dog cat cat dog"
    //Output: true
    //Example 2:
    //
    //Input: pattern = "abba", s = "dog cat cat fish"
    //Output: false
    //Example 3:
    //
    //Input: pattern = "aaaa", s = "dog cat cat dog"
    //Output: false
    //Example 4:
    //
    //Input: pattern = "abba", s = "dog dog dog dog"
    //Output: false
    public boolean wordPattern(String pattern, String s) {
        Map<Character, String> charMap = new HashMap<>();
        Map<String, Character> wordMap = new HashMap<>();

        String[] words = s.split(" ");
        if(words.length != pattern.length())
            return false;
        for(int i = 0; i < words.length; i++) {
            char c = pattern.charAt(i);
            //mapped: must exist in both maps
            if(charMap.containsKey(c)) {
                String value = charMap.get(c);
                if(!wordMap.containsKey(value))
                    return false;
                if(wordMap.get(value) == null || !wordMap.get(value).equals(c))
                    return false;
            }
            else {
                //exists in wordMap but not exists in charMap
                if(wordMap.containsKey(words[i]))
                    return false;
                charMap.put(c, words[i]);
                wordMap.put(words[i], c);
            }
        }

        return true;
    }
}
