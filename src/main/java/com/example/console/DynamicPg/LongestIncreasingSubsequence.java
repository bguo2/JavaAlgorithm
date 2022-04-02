package com.example.console.DynamicPg;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//Given an integer array nums, return the length of the longest strictly increasing subsequence.
//
//A subsequence is a sequence that can be derived from an array by deleting some or no elements without
// changing the order of the remaining elements. For example, [3,6,2,7] is a subsequence of the array [0,3,1,6,2,2,7].
//
//
//
//Example 1:
//
//Input: nums = [10,9,2,5,3,7,101,18]
//Output: 4
//Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.
//Example 2:
//
//Input: nums = [0,1,0,3,2,3]
//Output: 4
//Example 3:
//
//Input: nums = [7,7,7,7,7,7,7]
//Output: 1
//
//
//Constraints:
//
//1 <= nums.length <= 2500
//-104 <= nums[i] <= 104
//首先来看一种动态规划 Dynamic Programming 的解法，这种解法的时间复杂度为 O(n2)，类似 brute force 的解法，维护一个一维 dp 数组，
// 其中 dp[i] 表示以 nums[i] 为结尾的最长递增子串的长度，对于每一个 nums[i]，从第一个数再搜索到i，如果发现某个数小于 nums[i]，
// 更新 dp[i]，更新方法为 dp[i] = max(dp[i], dp[j] + 1)，即比较当前 dp[i] 的值和那个小于 num[i] 的数的 dp 值加1的大小，
// 就这样不断的更新 dp 数组，到最后 dp 数组中最大的值就是要返回的 LIS 的长度
public class LongestIncreasingSubsequence {
    public int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        int result = 0;
        for(int i = 0; i < nums.length; i++) {
            for(int j = 0; j < i; j++) {
                //if any number < nums[i]: update dp[i]
                if(nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j]+1);
                }
            }
            result = Math.max(result, dp[i]);
        }
        return result;
    }

    //use binary search
    public int lengthOfLISII(int[] nums) {
        List<Integer> result = new ArrayList<>();
        for(int num: nums) {
            if(result.isEmpty() || num > result.get(result.size() - 1))
                result.add(num);
            else {
                int start = 0;
                int end = result.size() - 1;
                while(start < end) {
                    int mid = (start + end) / 2;
                    if(result.get(mid) < num)
                        start = mid + 1;
                    else
                        end = mid;
                }
                result.set(start, num);
            }
        }
        return result.size();
    }
}
