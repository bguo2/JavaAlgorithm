package com.example.console.DynamicPg;

//Given an array of integers, find the subset of non-adjacent elements with the maximum sum. Calculate the sum of that subset.
// It is possible that the maximum sum is 0, the case when all elements are negative.
//arr = [-2,1,3,-4,5], return 8
//[-2, -3, -1], return 0
//[3 7 4 6 5], return 13
//similar to house robber
public class SubSetMax {
    public static int maxSubsetSum(int[] arr) {
        if(arr == null || arr.length == 0)
            return 0;
        if(arr.length == 1)
            return arr[0];
        int[] dp = new int[arr.length];
        dp[0] = arr[0];
        dp[1] = Math.max(arr[0], arr[1]);
        for(int i = 2; i < arr.length; i++) {
            //current one and i-2 (i-1 is adjacent)
            int curSum = Math.max(arr[i], dp[i-2]+arr[i]);
            //choose the max of dp[i-1] and curSum
            dp[i] = Math.max(dp[i-1], curSum);
        }
        return dp[arr.length-1] < 0 ? 0 : dp[arr.length-1];
    }

    //You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed.
    // All houses at this place are arranged in a circle. That means the first house is the neighbor of the last one. Meanwhile,
    // adjacent houses have security system connected and it will automatically contact the police if two adjacent houses were
    // broken into on the same night.
    //
    //Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money
    // you can rob tonight without alerting the police.
    //
    //Example 1:
    //
    //Input: [2,3,2]
    //Output: 3
    //Explanation: You cannot rob house 1 (money = 2) and then rob house 3 (money = 2),
    //             because they are adjacent houses.
    //Example 2:
    //
    //Input: [1,2,3,1]
    //Output: 4
    //Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
    //             Total amount you can rob = 1 + 3 = 4.
    public static int houseRobber2(int[] nums) {
        if(nums==null || nums.length==0)
            return 0;

        if(nums.length==1)
            return nums[0];

        int max1 = robHelper(nums, 0, nums.length-2);
        int max2 = robHelper(nums, 1, nums.length-1);

        return Math.max(max1, max2);
    }

    private static int robHelper(int[] nums, int i, int j) {
        if(i==j){
            return nums[i];
        }

        int[] dp = new int[nums.length];
        dp[i]=nums[i];
        dp[i+1]=Math.max(nums[i+1], dp[i]);

        for(int k=i+2; k<=j; k++) {
            dp[k]=Math.max(dp[k-1], dp[k-2]+nums[k]);
        }

        return dp[j];
    }

    public static int houseRobber1(int[] nums) {
        if(nums==null || nums.length==0)
            return 0;

        if(nums.length==1)
            return nums[0];

        int[] dp = new int[nums.length];
        dp[0]=nums[0];
        dp[1]=Math.max(nums[0], nums[1]);

        for(int i=2; i<nums.length; i++) {
            dp[i] = Math.max(dp[i-2]+nums[i], dp[i-1]);
        }

        return dp[nums.length-1];
    }
}
