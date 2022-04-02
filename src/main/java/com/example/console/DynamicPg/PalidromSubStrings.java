package com.example.console.DynamicPg;

//Count All Palindrome Sub-Strings in a String
//Given a string, the task is to count all palindrome sub string in a given string. Length of palindrome sub string
// is greater than or equal to 2.
//
//Examples:
//
//Input : str = "abaab"
//Output: 3
//Explanation :
//All palindrome substring are :
// "aba" , "aa" , "baab"
//
//Input : str = "abbaeae"
//Output: 4
//Explanation :
//All palindrome substring are :
//"bb" , "abba" ,"aea","eae"
public class PalidromSubStrings {
    public static int palidromSubStrCount(String s) {
        char[] str = s.toCharArray();
        boolean[][] dp = new boolean[str.length][str.length];
        int count = 0;

        for(int i = 0; i < dp.length; i++)
            dp[i][i] = true;

        for(int g = 1; g < str.length; g++) {
            for(int i = 0, j = g; j < str.length; i++, j++) {
                //2 chars: if str[i] = str[j]: then true
                if(g == 1) {
                    if(str[i] == str[j])
                        dp[i][j] = true;
                }
                //more chars
                else {
                    if(str[i] == str[j] && dp[i+1][j-1])
                        dp[i][j] = true;
                }

                if(dp[i][j])
                    count++;
            }
        }
        return count;
    }

    //dp
    // g chars selected
    // i: row; j - column
    //   a b  b    a
    // a a ab abb  abba
    // b   b  bb   bba
    // b      b    ba
    // a           a
    public static int countSubstringsDp(String s) {
        char[] str = s.toCharArray();
        boolean[][] dp = new boolean[str.length][str.length];
        int count = 0;

        for(int g = 0; g < str.length; g++) {
            for(int i = 0, j = g; j < str.length; i++, j++) {
                //i==j for g=0, single character: diagonal, it is true
                if(g == 0)
                    dp[i][j] = true;
                //2 chars: if str[i] = str[j]: then true
                else if(g == 1) {
                    if(str[i] == str[j])
                        dp[i][j] = true;
                }
                //more chars
                else {
                    if(str[i] == str[j] && dp[i+1][j-1])
                        dp[i][j] = true;
                }

                if(dp[i][j])
                    count++;
            }
        }
        return count;
    }

    //brute force
    public static int countSubstrings(String s) {
        int result = 0;
        for(int i = 0; i < s.length(); i++) {
            for(int j = i; j < s.length(); j++) {
                if(isPalidrome(s, i, j))
                    result++;
            }
        }

        return result;
    }

    private static boolean isPalidrome(String s, int i, int j) {
        while(i < j) {
            if(s.charAt(i) != s.charAt(j))
                return false;
            i++;
            j--;
        }
        return true;
    }
}
