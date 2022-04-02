package com.example.console;

import java.util.Arrays;

//A string S of lowercase letters is given.  Then, we may make any number of moves.
//
//In each move, we choose one of the first K letters (starting from the left), remove it, and place it at the end of the string.
//
//Return the lexicographically smallest string we could have after any number of moves.
//
//Example 1:
//
//Input: S = "cba", K = 1
//Output: "acb"
//Explanation:
//In the first move, we move the 1st character ("c") to the end, obtaining the string "bac".
//In the second move, we move the 1st character ("b") to the end, obtaining the final result "acb".
//Example 2:
//
//Input: S = "baaca", K = 3
//Output: "aaabc"
//Explanation:
//In the first move, we move the 1st character ("b") to the end, obtaining the string "aacab".
//In the second move, we move the 3rd character ("c") to the end, obtaining the final result "aaabc".
public class OrderlyQueue {
    //if 𝑘=1, we can only rotate the string.
    //if 𝑘>1, we can  sort the string.
    public String orderlyQueue(String S, int K) {
        if (K > 1) {
            char[] chars = S.toCharArray();
            Arrays.sort(chars);
            return new String(chars);
        }

        String ans = S;
        for (int i = 1; i < S.length(); ++i) {
            String tmp = S.substring(i) + S.substring(0, i);
            if (tmp.compareTo(ans) < 0)
                ans = tmp;
        }
        return ans;
    }
}

