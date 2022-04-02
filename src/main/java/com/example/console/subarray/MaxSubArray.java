package com.example.console.subarray;

import java.util.ArrayList;
import java.util.List;

//Find the contiguous subarray within an array (containing at least one number) which has the largest sum.
//
//For example, given the array [−2,1,−3,4,−1,2,1,−5,4], the contiguous subarray [4,−1,2,1] has the largest sum = 6.
//return the sub array
public class MaxSubArray {

    public static int[] maxSubArray(int[] nums) {
        if(nums == null || nums.length == 0)
            return new int[0];
        if(nums.length == 1)
            return new int[] { nums[0] };
        int sum = nums[0];
        int max = nums[0];
        int start = 0, end = 0;
        for(int i = 1; i < nums.length; i++) {
           /*
            sum = Math.max(nums[i], sum+nums[i]);
            max = Math.max(max, sum);*/
           if(nums[i] > sum + nums[i]) {
               sum = nums[i];
               start = i;
           }
           else {
               sum = sum + nums[i];
           }

           if(max < sum) {
               end = i;
               max = sum;
           }
        }

        int[] result = new int[end-start+1];
        int k = 0;
        for(int i = start; i <= end; i++) {
            result[k] = nums[i];
            k++;
        }

        return result;
    }
}
