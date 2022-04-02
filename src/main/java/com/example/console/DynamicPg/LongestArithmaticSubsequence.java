package com.example.console.DynamicPg;

//Given an array A of integers, return the length of the longest arithmetic subsequence in A.
//
//Recall that a subsequence of A is a list A[i_1], A[i_2], ..., A[i_k] with 0 <= i_1 < i_2 < ... < i_k <= A.length - 1,
// and that a sequence B is arithmetic if B[i+1] - B[i] are all the same value (for 0 <= i < B.length - 1).
//
//
//
//Example 1:
//
//Input: A = [3,6,9,12]
//Output: 4
//Explanation:
//The whole array is an arithmetic sequence with steps of length = 3.
//Example 2:
//
//Input: A = [9,4,7,2,10]
//Output: 3
//Explanation:
//The longest arithmetic subsequence is [4,7,10].
//Example 3:
//
//Input: A = [20,1,15,3,10,5,8]
//Output: 4
//Explanation:
//The longest arithmetic subsequence is [20,15,10,5].

import java.util.HashMap;
import java.util.Map;

//
public class LongestArithmaticSubsequence {
    public int longestArithSeqLength(int[] A) {
        // ask whether A could be null
        final int N = A.length;
        if (N <= 1) return N;
        int maxLen = 0;
        // key: diff for each two pairs, value: length with such diff
        Map<Integer, Integer> maps[] = new HashMap[N];
        for (int i = 0; i < N; i++) {
            maps[i] = new HashMap<>();
            for (int j = 0; j < i; j++) {
                int diff = A[i] - A[j];
                // length of arithmetic subsequence with same diff at previous element + 1
                int len = maps[j].getOrDefault(diff, 0) + 1;
                maps[i].put(diff, len);
                maxLen = Math.max(maxLen, len);
            }
        }
        return maxLen + 1;
    }
}
