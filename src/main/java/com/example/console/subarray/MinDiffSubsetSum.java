package com.example.console.subarray;

import java.util.Arrays;

//Given a set of positive integers, the task is to divide it into two sets S1 and S2 such that the absolute difference
// between their sums is minimum.
//If there is a set S with n elements, then if we assume Subset1 has m elements, Subset2 must have n-m elements and the value of
// abs(sum(Subset1) – sum(Subset2)) should be minimum.
//Example:
//
//Input:  arr[] = {1, 6, 11, 5}
//Output: 1
//Explanation:
//Subset1 = {1, 5, 6}, sum of Subset1 = 12
//Subset2 = {11}, sum of Subset2 = 11

//Last Stone Weight II
//You are given an array of integers stones where stones[i] is the weight of the ith stone.
//
//We are playing a game with the stones. On each turn, we choose any two stones and smash them together. Suppose the stones have weights x and y with x <= y. The result of this smash is:
//
//If x == y, both stones are destroyed, and
//If x != y, the stone of weight x is destroyed, and the stone of weight y has new weight y - x.
//At the end of the game, there is at most one stone left.
//
//Return the smallest possible weight of the left stone. If there are no stones left, return 0.
//
//
//
//Example 1:
//
//Input: stones = [2,7,4,1,8,1]
//Output: 1
//Explanation:
//We can combine 2 and 4 to get 2, so the array converts to [2,7,1,8,1] then,
//we can combine 7 and 8 to get 1, so the array converts to [2,1,1,1] then,
//we can combine 2 and 1 to get 1, so the array converts to [1,1,1] then,
//we can combine 1 and 1 to get 0, so the array converts to [1], then that's the optimal value.
//Example 2:
//
//Input: stones = [31,26,33,21,40]
//Output: 5
//Example 3:
//
//Input: stones = [1,2]
//Output: 1
//
//
//Constraints:
//
//1 <= stones.length <= 30
//1 <= stones[i] <= 100

//Last Stone Weight II
//You are given an array of integers stones where stones[i] is the weight of the ith stone.
//
//We are playing a game with the stones. On each turn, we choose any two stones and smash them together. Suppose the stones have weights x and y with x <= y. The result of this smash is:
//
//If x == y, both stones are destroyed, and
//If x != y, the stone of weight x is destroyed, and the stone of weight y has new weight y - x.
//At the end of the game, there is at most one stone left.
//
//Return the smallest possible weight of the left stone. If there are no stones left, return 0.
//Example 1:
//
//Input: stones = [2,7,4,1,8,1]
//Output: 1
//Explanation:
//We can combine 2 and 4 to get 2, so the array converts to [2,7,1,8,1] then,
//we can combine 7 and 8 to get 1, so the array converts to [2,1,1,1] then,
//we can combine 2 and 1 to get 1, so the array converts to [1,1,1] then,
//we can combine 1 and 1 to get 0, so the array converts to [1], then that's the optimal value.
//Example 2:
//
//Input: stones = [31,26,33,21,40]
//Output: 5
//Example 3:
//
//Input: stones = [1,2]
//Output: 1
public class MinDiffSubsetSum {
    //dfs recursion
    public static int getMinDiffSubset(int[] input) {
        int sum = Arrays.stream(input).sum();
        //return findMinDiff(input, input.length - 1, 0, sum);

        Integer[][] mem = new Integer[input.length][sum+1];
        return findMinDiff1(input, input.length - 1, 0, sum, mem);
    }

    //O(2^n)
    private static int findMinDiff(int[] input, int start, int curSum, int sum) {
        //reached the last element:
        //curSum is the sum of one subset, another subset's sum is sum-curSum, the diff is the 2 sum difference
        if(start == 0) {
            return Math.abs((sum-curSum) - curSum);
        }

        //include the current element
        int diff1 = findMinDiff(input, start - 1, curSum + input[start-1], sum);
        //not include the current element
        int diff2 = findMinDiff(input, start - 1, curSum, sum);
        //min difference
        return Math.min(diff1, diff2);
    }

    //use memorizing: O(n*m) m-sum of the array
    private static int findMinDiff1(int[] input, int start, int curSum, int sum, Integer[][] mem) {
        //reached the last element:
        //curSum is the sum of one subset, another subset's sum is sum-curSum, the diff is the 2 sum difference
        if(start == 0) {
            return Math.abs((sum-curSum) - curSum);
        }

        if(mem[start][curSum] != null)
            return mem[start][curSum];

        //include the current element
        int diff1 = findMinDiff1(input, start - 1, curSum + input[start-1], sum, mem);
        //not include the current element
        int diff2 = findMinDiff1(input, start - 1, curSum, sum, mem);
        //min difference
        mem[start][curSum] = Math.min(diff1, diff2);
        return mem[start][curSum];
    }
}
