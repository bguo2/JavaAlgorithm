package com.example.console;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

//Given n balloons, indexed from 0 to n-1. Each balloon is painted with a number on it represented by array nums. You are asked to burst all the balloons. If the you burst balloon i you will get nums[left] * nums[i] * nums[right] coins. Here left and right are adjacent indices of i. After the burst, the left and right then becomes adjacent.
//
//Find the maximum coins you can collect by bursting the balloons wisely.
//
//Note:
//
//You may imagine nums[-1] = nums[n] = 1. They are not real therefore you can not burst them.
//0 ≤ n ≤ 500, 0 ≤ nums[i] ≤ 100
//Example:
//
//Input: [3,1,5,8]
//Output: 167
//Explanation: nums = [3,1,5,8] --> [3,5,8] -->   [3,8]   -->  [8]  --> []
//             coins =  3*1*5      +  3*5*8    +  1*3*8      + 1*8*1   = 167
public class BurstBallons {
    //DP: https://www.cnblogs.com/grandyang/p/5006441.html

    public int maxCoins(int[] numbers) {
        int n = numbers.length;
        int[] temp = new int[n+2];
        //add 1 at the front and 1 to end
        temp[0] = 1;
        temp[n-1] = 1;
        System.arraycopy(numbers, 0, temp, 1, n-1);
        int[][] dp = new int[n + 2][n + 2];
        return burst(temp, dp, 1 , numbers.length);
    }

    private int burst(int[] nums, int[][] dp, int i, int j) {
        if(i > j)
            return 0;
        if(dp[i][j] > 0)
            return dp[i][j];
        int res = 0;
        for(int k = i; k<=j; k++) {
            res = Math.max(res, nums[i-1]*nums[k]*nums[j+1] + burst(nums, dp, i, k-1) + burst(nums,dp , k+1, j));
        }

        dp[i][j] = res;
        return res;
    }
}
