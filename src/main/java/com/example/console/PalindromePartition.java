package com.example.console;

import java.util.ArrayList;
import java.util.List;

//[LeetCode] Palindrome Partitioning 拆分回文串
//Given a string s, partition s such that every substring of the partition is a palindrome.
//Return all possible palindrome partitioning of s.
//Example:
//Input: "aab"
//Output:
//[
//  ["aa","b"],
//  ["a","a","b"]
//]
public class PalindromePartition {
    public List<List<String>> getPartition(String s) {
        List<List<String>> result = new ArrayList<>();
        if(s == null || s.length() == 0)
            return result;
        List<String> partition = new ArrayList<>();
        addPalindrome(s, 0, partition, result);
        return result;
    }

    private void addPalindrome(String s, int start, List<String> partition, List<List<String>> result) {
        if(start == s.length()) {
            List<String> tmp = new ArrayList<>(partition);
            result.add(tmp);
            return;
        }

        for(int i = start; i < s.length(); i++) {
            String subStr = s.substring(start, i+1);
            if(isPalindrome(subStr)) {
                partition.add(subStr);
                addPalindrome(s, i+1, partition, result);
                partition.remove(partition.size() - 1);
            }
        }
    }

    private boolean isPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;
        while(left < right) {
            if(s.charAt(left) != s.charAt(right))
                return false;
            left++;
            right--;
        }

        return true;
    }
}
