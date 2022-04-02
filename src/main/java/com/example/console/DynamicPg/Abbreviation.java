package com.example.console.DynamicPg;

//You can perform the following operations on the string, :
//
//Capitalize zero or more of 's lowercase letters.
//Delete all of the remaining lowercase letters in .
//Given two strings, a and b, determine if it's possible to make  equal to  as described. If so,
// print YES on a new line. Otherwise, print NO.
//
//For example, given a=AbcDE and b=ABDE, in a we can convert b and delete c to match b. If a=AbcDE and b=AFDE,
// matching is not possible because letters may only be capitalized or discarded, not changed.
//
//Function Description
//
//Complete the function  in the editor below. It must return either YES or NO.
//
//abbreviation has the following parameter(s):
//
//a: the string to modify
//b: the string to match
public class Abbreviation {
    public static String abbreviation(String a, String b) {
        //deletable at i,j
        boolean[][] dp = new boolean[a.length()+1][b.length()+1];
        dp[0][0] = true;

        for (int i= 1; i <= a.length(); i++) {
            //uppercase: not deletable
            if (Character.isUpperCase(a.charAt(i - 1))) {
                dp[i][0] = false;
            }
            //can be deleted and converted
            else 
                dp[i][0] = true;
        }

        // tabulation from start of string
        for (int i = 1; i <= a.length(); i++) {
            for (int j = 1; j <= b.length(); j++) {
                //equal to the previous one
                if (a.charAt(i-1) == b.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1];
                }
                //a can be converted to b
                else if (Character.toUpperCase(a.charAt(i-1))  ==  b.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1] || dp[i-1][j];
                }
                //not equal, not convertible and uppercase: not deletable
                else if (Character.isUpperCase(a.charAt(i-1))) {
                    dp[i][j] = false;
                }
                //previous
                else {
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        return dp[a.length()][b.length()]? "YES" : "NO";
    }
}
