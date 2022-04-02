package com.example.console;

import java.util.HashMap;

//Given a string s, find the length of the longest substring without repeating characters.
//
//
//
//Example 1:
//
//Input: s = "abcabcbb"
//Output: 3
//Explanation: The answer is "abc", with the length of 3.
//Example 2:
//
//Input: s = "bbbbb"
//Output: 1
//Explanation: The answer is "b", with the length of 1.
//Example 3:
//
//Input: s = "pwwkew"
//Output: 3
//Explanation: The answer is "wke", with the length of 3.
//Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.
//Example 4:
//
//Input: s = ""
//Output: 0
//
//
//Constraints:
//
//0 <= s.length <= 5 * 104
//s consists of English letters, digits, symbols and spaces.
public class LongestSubString1 {
    public int lengthOfLongestSubstring(String s) {
        if(s == null || s.length()==0)
            return 0;
        boolean[] flag = new boolean[256];
        int start = 0;
        int len = Integer.MIN_VALUE;
        for(int i=0; i < s.length(); i++) {
            char c = s.charAt(i);
            //repeating now
            if(flag[c]) {
                len = Math.max(len, i - start);
                //reset
                for(int j = start; j < i; j++) {
                    if(s.charAt(j) == c) {
                        start = j + 1;
                        break;
                    }
                    flag[s.charAt(j)] = false;
                }
            }
            else {
                flag[c] = true;
            }
        }

        len = Math.max(len, s.length() - start);
        if(len == Integer.MIN_VALUE)
            return 0;
        return len;
    }

    //340. Longest Substring with At Most K Distinct Characters
    //Medium
    //
    //1474
    //
    //52
    //
    //Add to List
    //
    //Share
    //Given a string s and an integer k, return the length of the longest substring of s that contains at most k distinct characters.
    //
    //
    //
    //Example 1:
    //
    //Input: s = "eceba", k = 2
    //Output: 3
    //Explanation: The substring is "ece" with length 3.
    //Example 2:
    //
    //Input: s = "aa", k = 1
    //Output: 2
    //Explanation: The substring is "aa" with length 2.
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        if(s == null || s.length() == 0 || k < 1)
            return 0;
        HashMap<Character, Integer> map = new HashMap<>();
        int result = 0;
        int start = 0;
        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            map.put(c, map.getOrDefault(c, 0) + 1);
            if(map.size() <= k) {
                result = Math.max(result, i - start + 1);
            }
            else{
                while(map.size() > k) {
                    c = s.charAt(start);
                    if(map.get(c) == 1)
                        map.remove(c);
                    else
                        map.put(c, map.get(c)-1);
                    start++;
                }
            }
        }

        return result;
    }
}
