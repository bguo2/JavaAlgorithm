package com.example.console.subarray;

import java.util.Arrays;

//Given an integer array arr[], the task is to split the given array into two contiguous subarrays such that the difference between
// their sum is minimum.
//
//Examples:
//
//Input: arr[] = {7, 9, 5, 10}
//Output: 1
//Explanation: The difference between the sum of the subarrays {7, 9} and {5, 10} is equal to [16 – 15] = 1,
// which is the minimum possible.
//
//Input: arr[] = {6, 6, 6}
//Output: 6
public class MinDiffBetween2Subarrays {
    public static int minDiffSubArray(int[] input) {
        if(input == null || input.length < 1)
            return -1;
/*
        int[] leftSum = new int[input.length];
        leftSum[0] = input[0];
        int sum = input[0];
        for(int i = 1; i < input.length; i++) {
            leftSum[i] = leftSum[i-1] + input[i];
            sum += input[i];
        }

        int result = Integer.MAX_VALUE;
        for(int i = 0; i < input.length - 1; i++) {
            int diff = Math.abs(leftSum[i] - (sum - leftSum[i]));
            result = Math.min(result, diff);
        }
 */
        int result = Integer.MAX_VALUE;
        int sum = Arrays.stream(input).sum();
        int curSum = 0;
        for(int i = 0; i < input.length - 1; i++) {
            curSum += input[i];
            int diff = Math.abs(curSum - (sum - curSum));
            result = Math.min(result, diff);
        }

        return result;
    }
}
