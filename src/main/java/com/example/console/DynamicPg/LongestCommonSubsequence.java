package com.example.console.DynamicPg;

//longest common subsequence in 2 strings
//The symbols that make up a longest common subsequence need only appear in the same order in each string
//The longest common substring problem is to find the longest contiguous sequence of symbols shared by two strings
//e.g. "hippopotamus" and "rhinoceros"
//the longest common substring is "hi", while the longest common subsequence is "hioos"
public class LongestCommonSubsequence {
    //recursion
    public int getLcs(String src1, String src2, int m, int n) {
        if(m == 0 || n == 0)
            return 0;
        if(src1.charAt(m - 1) == src2.charAt(n-1))
            return getLcs(src1, src2, m-1, n-1) + 1;
        return Math.max(getLcs(src1, src2, m, n-1), getLcs(src1, src2, m-1, n));
    }

    //dp
    public int getLcs(String a, String b) {
        int m = a.length();
        int n = b.length();
        int[][] dp = new int[m+1][n+1];

        for(int i=0; i<=m; i++) {
            for(int j=0; j<=n; j++) {
                if(i==0 || j==0){
                    dp[i][j]=0;
                }else if(a.charAt(i-1)==b.charAt(j-1)){
                    dp[i][j] = 1 + dp[i-1][j-1];
                }else{
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }

        return dp[m][n];
    }

    public String printLcs(String a, String b) {
        int m = a.length();
        int n = b.length();
        int[][] dp = new int[m+1][n+1];

        for(int i=0; i<=m; i++){
            for(int j=0; j<=n; j++){
                if(i==0 || j==0){
                    dp[i][j]=0;
                }else if(a.charAt(i-1)==b.charAt(j-1)){
                    dp[i][j] = 1 + dp[i-1][j-1];
                }else{
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }

        int i = m, j= n, index = dp[m][n];
        char[] lcs = new char[index];
        while (i > 0 & j > 0) {
            if(a.charAt(i-1) == b.charAt(j-1)) {
                lcs[index-1] = a.charAt(i-1);
                i--;
                j--;
                index--;
            }
            if(dp[i-1][j] > dp[i][j-1]) {
                i--;
            }
            else
                j--;
        }

        return new String(lcs);
    }

    //Given two strings str1 and str2, find the shortest string that has both str1 and str2 as subsequences.
    //nput:   str1 = "geek",  str2 = "eke"
    //Output: "geeke"
    //
    //Input:   str1 = "AGGTAB",  str2 = "GXTXAYB"
    //Output:  "AGXGTXAYB
    public int getshortestCommonSupersequence(String src1, String src2) {
        int m = src1.length();
        int n = src2.length();
        int l = getLcs(src1, src2);
        return (m + n - l);
    }

    //print shortest super sequence
    public String printShortestSuperSeq(String X, String Y)
    {
        int m = X.length();
        int n = Y.length();

        // dp[i][j] contains length of
        // shortest supersequence
        // for X[0..i-1] and Y[0..j-1]
        int dp[][] = new int[m + 1][n + 1];

        // Fill table in bottom up manner
        for (int i = 0; i <= m; i++)
        {
            for (int j = 0; j <= n; j++)
            {
                // Below steps follow recurrence relation
                if (i == 0)
                {
                    dp[i][j] = j;
                }
                else if (j == 0)
                {
                    dp[i][j] = i;
                }
                else if (X.charAt(i - 1) == Y.charAt(j - 1))
                {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                }
                else
                {
                    dp[i][j] = 1 + Math.min(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        // Following code is used to print
        // shortest supersequence dp[m][n] s
        // tores the length of the shortest
        // supersequence of X and Y
        int index = dp[m][n];

        // string to store the shortest supersequence
        String str = "";

        // Start from the bottom right corner and one by one
        // push characters in output string
        int i = m, j = n;
        while (i > 0 && j > 0)

        {
            // If current character in X and Y are same, then
            // current character is part of shortest supersequence
            if (X.charAt(i - 1) == Y.charAt(j - 1))

            {
                // Put current character in result
                str += (X.charAt(i - 1));

                // reduce values of i, j and index
                i--;
                j--;
                index--;
            }

            // If current character in X and Y are different
            else if (dp[i - 1][j] > dp[i][j - 1])
            {

                // Put current character of Y in result
                str += (Y.charAt(j - 1));

                // reduce values of j and index
                j--;
                index--;
            }
            else
            {

                // Put current character of X in result
                str += (X.charAt(i - 1));

                // reduce values of i and index
                i--;
                index--;
            }
        }

        // If Y reaches its end, put remaining characters
        // of X in the result string
        while (i > 0)
        {
            str += (X.charAt(i - 1));
            i--;
            index--;
        }

        // If X reaches its end, put remaining characters
        // of Y in the result string
        while (j > 0)
        {
            str += (Y.charAt(j - 1));
            j--;
            index--;
        }

        // reverse the string and return it
        str = new StringBuilder(str).reverse().toString();
        return str;
    }
}
