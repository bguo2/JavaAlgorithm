package com.example.console.DynamicPg;

//The longest common substring problem is to find the longest contiguous sequence of symbols shared by two strings
//e.g. "hippopotamus" and "rhinoceros"
//the longest common substring is "hi", while the longest common subsequence is "hioos"
public class LongestCommonSubString {

    //This is a similar problem like longest common subsequence. The difference of the solution is that for this problem
    // when a[i]!=b[j], dp[i][j] are all zeros by default. However, in the longest common subsequence problem, dp[i][j]
    // values are carried from the previous values, i.e., dp[i-1][j] and dp[i][j-1].
    public static int getLongestCommon(String s1, String s2) {
        if(s1 == null || s1.isEmpty())
            return 0;
        if(s2 == null || s2.isEmpty())
            return 0;
        int dp[][] = new int[s1.length()][s2.length()];
        int max = 0;
        for(int i = 0; i < s1.length(); i++) {
            for(int j = 0; j < s1.length(); j++) {
                if(s1.charAt(i) == s2.charAt(j)) {
                    if(i == 0 || j == 0)
                        dp[i][j] = 1;
                    else {
                        dp[i][j] = dp[i-1][j-1] + 1;
                    }
                    max = Math.max(max, dp[i][j]);
                }
            }
        }

        return max;
    }
}
