package com.example.console;

//Given two strings s and t, return true if they are both one edit distance apart, otherwise return false.
//
//A string s is said to be one distance apart from a string t if you can:
//
//Insert exactly one character into s to get t.
//Delete exactly one character from s to get t.
//Replace exactly one character of s with a different character to get t.
//
//
//Example 1:
//
//Input: s = "ab", t = "acb"
//Output: true
//Explanation: We can insert 'c' into s to get t.
//Example 2:
//
//Input: s = "", t = ""
//Output: false
//Explanation: We cannot get t from s by only one step.
//Example 3:
//
//Input: s = "a", t = ""
//Output: true
//Example 4:
//
//Input: s = "", t = "A"
//Output: true
//
//
//Constraints:
//
//0 <= s.length <= 104
//0 <= t.length <= 104
//s and t consist of lower-case letters, upper-case letters and/or digits.
public class OneEditDistance {
    public boolean isOneEditDistance(String s, String t) {
        if(s.length() == 0 && t.length() == 0)
            return false;
        int m = s.length(), n = t.length();
        int i = 0, j = 0;
        int count = Math.abs(m - n);
        if(count > 1)
            return false;
        if(count == 0 && s.equals(t))
            return false;
        count = 0;
        while(i < m && j < n) {
            if(s.charAt(i) == t.charAt(j)) {
                i++;
                j++;
                continue;
            }
            count++;
            if(count > 1)
                return false;
            if(m > n)
                i++;
            else if(m < n)
                j++;
                //same length, all skips one
            else {
                i++;
                j++;
            }
        }
        if(i < m || j < n) {
            count++;
        }

        if(count > 1)
            return false;
        return true;
    }
}
