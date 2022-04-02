package com.example.console;
//Given an array of non-negative integers nums, you are initially positioned at the first index of the array.
//
//Each element in the array represents your maximum jump length at that position.
//
//Your goal is to reach the last index in the minimum number of jumps.
//
//You can assume that you can always reach the last index.
//
//
//
//Example 1:
//
//Input: nums = [2,3,1,1,4]
//Output: 2
//Explanation: The minimum number of jumps to reach the last index is 2. Jump 1 step from index 0 to 1, then 3 steps to the last index.
//Example 2:
//
//Input: nums = [2,3,0,1,4]
//Output: 2
//为了较快的跳到末尾，想知道每一步能跳的范围，这里贪婪并不是要在能跳的范围中选跳力最远的那个位置，因为这样选下来不一定是最优解，
// 这么一说感觉又有点不像贪婪算法了。其实这里贪的是一个能到达的最远范围，遍历当前跳跃能到的所有位置，然后根据该位置上的跳力来预测下一步能跳到的最远距离，
// 贪出一个最远的范围，一旦当这个范围到达末尾时，当前所用的步数一定是最小步数。
public class JumpGame {
    public int jump(int[] nums) {
        if(nums == null || nums.length == 1)
            return 0;
        int res = 0, len = nums.length, last=0, cur = 0;
        for(int i = 0; i < len; i++) {
            cur = Math.max(cur, i + nums[i]);
            if(i == last) {
                last = cur;
                res++;
                if(cur >= len - 1)
                    break;
            }
        }

        return res;
    }

    //can reach end: greedy algorithm
    public boolean canReach(int[] nums) {
        if(nums == null || nums.length == 0)
            return false;
        int reach = 0;
        for(int i = 0; i < nums.length; i++) {
            //cannot reach current position
            if(reach < i || reach >= nums.length-1)
                break;
            reach = Math.max(reach, i+nums[i]);
        }

        return reach >= nums.length - 1;
    }
}
