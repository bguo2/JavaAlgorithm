package com.example.console;
//Given an array which consists of non-negative integers and an integer m, you can split the array into m non-empty continuous subarrays. Write an algorithm to minimize the largest sum among these m subarrays.
//Note:
//Given m satisfies the following constraint: 1 ≤ m ≤ length(nums) ≤ 14,000.
//Examples:
//Input:
//nums = [7,2,5,10,8]
//m = 2
//
//Output:
//18
//
//Explanation:
//There are four ways to split nums into two subarrays.
//The best way is to split it into [7,2,5] and [10,8],
//where the largest sum among the two subarrays is only 18.

//The problem can be solved by using binary search, which is a quite brilliant way. If m equals length of the array,
// the largest sum should be the maximum among the elements. If m equals 1, then it should be the sum of all elements in the array.
// Now the maximum sum of a subarray should be between these two numbers (left, right+1).
//
//Direct search the answer: Given a candidate C, compute the number of groups k needed
//if k > m: #C too small
//  l = C + 1;
//else
//  r = C;
public class MinLargeSumSubArrays {
    public int splitArray(int[] num, int m)
    {
        if(num.length == 0)
            return 0;
        int left = 0, right = 0;
        for(int n: num) {
            left = Math.max(n, left);
            right += n;
        }

        if(m == 1)
            return right;
        if(m == num.length)
            return left;
        right += 1;
        while (left < right){
            int limit = (right + left) / 2;
            if(min_groups(num, limit) > m) {
                left = limit + 1;
            }
            else
                right = limit;
        }

        return left;
    }

    private int min_groups(int[] num, int limit) {
        int group = 1;
        int sum = 0;
        for(int n: num) {
            if(sum + n > limit) {
                sum = n;
                group++;
            }
            else
                sum += n;
        }

        return group;
    }
}
