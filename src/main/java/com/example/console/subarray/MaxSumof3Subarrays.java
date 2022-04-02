package com.example.console.subarray;
//Maximum Sum of 3 Non-Overlapping Subarrays
//n a given array nums of positive integers, find three non-overlapping subarrays with maximum sum.
//
//Each subarray will be of size k, and we want to maximize the sum of all 3*k entries.
//
//Return the result as a list of indices representing the starting position of each interval (0-indexed).
// If there are multiple answers, return the lexicographically smallest one.
//
//Example:
//
//Input: [1,2,1,2,6,7,5,1], 2
//Output: [0, 3, 5]
//Explanation: Subarrays [1, 2], [2, 6], [7, 5] correspond to the starting indices [0, 3, 5].
//We could have also taken [2, 1], but an answer of [1, 3, 5] would be lexicographically larger.
//
//
//Note:
//
//nums.length will be between 1 and 20000.
//nums[i] will be between 1 and 65535.
//k will be between 1 and floor(nums.length / 3).

//对于这种求子数组和有关的题目时，一般都需要建立累加和数组，为啥呢，因为累加和数组可以快速的求出任意长度的子数组之和，当然也能快速的求出长度为k的子数组之和。
// 因为这道题只让我们找出三个子数组，那么我们可以先确定中间那个子数组的位置，这样左右两边的子数组的位置范围就缩小了，中间子数组的起点不能是从开头到结尾整个区间，
// 必须要在首尾各留出k个位置给其他两个数组。一旦中间子数组的起始位置确定了，那么其和就能通过累加和数组快速确定。那么现在就要在左右两边的区间内分别找出和最大的
// 子数组，遍历所有的子数组显然不是很高效，如何快速求出呢，这里我们需要使用动态规划Dynamic Programming的思想来维护两个DP数组left和right，其中:
//
//left[i]表示在区间[0, i]范围内长度为k且和最大的子数组的起始位置
//
//right[i]表示在区间[i, n - 1]范围内长度为k且和最大的子数组的起始位置
//
//这两个dp数组各需要一个for循环来更新，left数组都初始化为0，前k个数字没办法，肯定起点都是0，变量total初始化为前k个数字之和，然后从第k+1个数字开始，
// 每次向前取k个，利用累加和数组sums快速算出数字之和，跟total比较，如果大于total的话，那么更新total和left数组当前位置值，否则的话left数组的当前值就赋值为
// 前一位的值。同理对right数组的更新也类似，total初始化为最后k个数字之和，然后从前一个数字向前遍历，如果大于total，更新total和right数组的当前位置，
// 否则right数组的当前值就赋值为后一位的值。一旦left数组和right数组都更新好了，那么就可以遍历中间子数组的起始位置了，然后我们可以通过left和right数组快速定
// 位出左边和右边的最大子数组的起始位置，并快速计算出这三个子数组的所有数字之和，用来更新全局最大值mx，如果mx被更新了的话，记录此时的三个子数组的起始位置
// 到结果res中
public class MaxSumof3Subarrays {
    public static int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        if(k < 0 || nums == null || nums.length == 0) {
            return new int[0];
        }
        int n = nums.length;
        int[] sums = new int[n+1];
        int sum = 0;
        for(int i = 0; i < n; i++) {
            sum += nums[i];
            sums[i+1] = sum;
        }

        //calculate left[]
        int total = sums[k] - sums[0];
        int[] left = new int[n];
        //initialization to the first one
        for(int i = 0; i < k; i++)
            left[i] = 0;
        for(int i = k; i < n; i++) {
            if (sums[i + 1] - sums[i + 1 - k] > total) {
                left[i] = i + 1 - k;
                total = sums[i + 1] - sums[i + 1 - k];
            } else {
                left[i] = left[i - 1];
            }
        }

        //calculate right[]
        total = sums[n] - sums[n-k];
        int[] right = new int[n];
        //initialization to the n-k
        for(int i = n-1; i > n-1-k; i--)
            right[i] = n-k;
        for(int i = n-1-k; i >=0; i--) {
            if (sums[i + k] - sums[i] >= total) {
                right[i] = i;
                total = sums[i + k] - sums[i];
            } else {
                right[i] = right[i + 1];
            }
        }

        //result
        int mx = Integer.MIN_VALUE;
        int[] res = new int[] {-1,-1,-1};
        for (int i = k; i <= n - 2 * k; ++i) {
            int l = left[i - 1], r = right[i + k];
            //L  M  R
            total =  (sums[l + k] - sums[l]) + (sums[i + k] - sums[i]) + (sums[r + k] - sums[r]);
            if (mx < total) {
                mx = total;
                res = new int[] {l, i, r};
            }
        }

        return res;
    }
}
