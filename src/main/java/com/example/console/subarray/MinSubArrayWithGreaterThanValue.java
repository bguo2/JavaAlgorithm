package com.example.console.subarray;

//Smallest subarray with sum greater than a given value
//Given an array of integers and a number x, find the smallest continuous subarray with sum greater than the given value.
//If there isn't one, return 0 instead.
//Examples:
//arr[] = {1, 4, 45, 6, 0, 19}
//   x  =  51
//Output: 3
//Minimum length subarray is {4, 45, 6}
//
//arr[] = {1, 10, 5, 2, 7}
//   x  = 9
//Output: 1
//Minimum length subarray is {10}
//
//arr[] = {1, 11, 100, 1, 0, 200, 3, 2, 1, 250}
//    x = 280
//Output: 4
//Minimum length subarray is {100, 1, 0, 200}
//
//arr[] = {1, 2, 4}
//    x = 8
//Output : Not Possible
//Whole array sum is smaller than 8.
public class MinSubArrayWithGreaterThanValue {
    public static int getMinSubArray(int[] nums, int s)
    {
        if(nums == null || nums.length == 0)
            return 0;
        int min = Integer.MAX_VALUE;
        int sum = 0;
        int start = 0;
        int i = 0;
        while(i <= nums.length) {
            if(sum > s) {
                min = Math.min(min, i - start);
                sum -= nums[start];
                start++;
            }
            else {
                if(i == nums.length)
                    break;
                sum += nums[i];
                i++;
            }
        }

        if(min == Integer.MAX_VALUE)
            return 0;
        return min;
    }
}
