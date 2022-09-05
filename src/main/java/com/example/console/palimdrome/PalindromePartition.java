package com.example.console.palimdrome;

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

    //Given a string s, partition s such that every substring of the partition is a palindrome.
    //
    //Return the minimum cuts needed for a palindrome partitioning of s.
    //
    //
    //
    //Example 1:
    //
    //Input: s = "aab"
    //Output: 1
    //Explanation: The palindrome partitioning ["aa","b"] could be produced using 1 cut.
    //Example 2:
    //
    //Input: s = "a"
    //Output: 0
    //Example 3:
    //
    //Input: s = "ab"
    //Output: 1
    //
    //
    //Constraints:
    //
    //1 <= s.length <= 2000
    //s consists of lowercase English letters only.
    public int minCut(String s) {
        int n = s.length();
        int dp[] = new int[n+1];

        if(isPalidrome(s, 0, n-1))
            return 0;
        for(int i = n-1; i > -1; i--) {
            int min = Integer.MAX_VALUE;
            for(int j = i; j < n; j++) {
                if(isPalidrome(s, i, j)) {
                    min = Math.min(min, 1+dp[j+1]);
                }
            }
            dp[i] = min;
        }
        return dp[0]-1;
    }

    private boolean isPalidrome(String s, int i, int j) {
        if(i > j)
            return false;
        while(i < j) {
            if(s.charAt(i) != s.charAt(j))
                return false;
            i++;
            j--;
        }
        return true;
    }
}
