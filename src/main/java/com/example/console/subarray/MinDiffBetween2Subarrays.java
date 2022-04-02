package com.example.console.subarray;

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
        int[] leftSum = new int[input.length];
        leftSum[0] = input[0];
        for(int i = 1; i < input.length; i++) {
            leftSum[i] = leftSum[i-1] + input[i];
        }

        int[] rightSum = new int[input.length];
        rightSum[input.length - 1] = input[input.length - 1];
        for(int i = input.length - 2; i >= 0; i--) {
            rightSum[i] = rightSum[i+1] + input[i];
        }

        int result = Integer.MAX_VALUE;
        for(int i = 0; i < input.length - 1; i++) {
            int diff = Math.abs(leftSum[i] - rightSum[i+1]);
            result = Math.min(result, diff);
        }
        return result;
    }
}
