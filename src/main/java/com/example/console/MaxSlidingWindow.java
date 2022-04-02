package com.example.console;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

//Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very right.
//You can only see the k numbers in the window. Each time the sliding window moves right by one position.
//Example:
//
//Input: nums = [1,3,-1,-3,5,3,6,7], and k = 3
//Output: [3,3,5,5,6,7]
public class MaxSlidingWindow {

    public static int[] maxSlindingWindow(int[] nums, int k){
        if(nums == null || nums.length == 0 || k < 0)
            return null;
        int[] result = new int[nums.length - k + 1];;

        int j, max;
        for (int i = 0; i <= nums.length - k; i++) {
            max = nums[i];
            for (j = 1; j < k; j++)
            {
                if (nums[i + j] > max)
                    max = nums[i + j];
            }
            result[i] = max;
        }
        return result;
    }

    //假如优先队列中最大的数字此时不在窗口中了，就要移除，判断方法就是将队首元素的 pair 对儿中的 second（位置坐标）跟 i-k 对比，小于等于就移除。
    // 然后将当前数字和其位置组成 pair 对儿加入优先队列中。此时看若 i >= k-1，说明窗口大小正好是k，就将最大值加入结果 res 中即可
    public static int[] maxSlidingNumber(int[] nums, int k) {
        if(nums == null || nums.length == 0) {
            return null;
        }

        int count = 0;
        int[] result = new int[nums.length - k +1];
        PriorityQueue<int[]> queue = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] x1, int[] x2) {
                return x2[0] - x1[0];
            }
        });

        for(int i = 0; i < nums.length; i++) {
            //remove all not in the window i-k ~ i
            while(!queue.isEmpty() && queue.peek()[1] <= i - k) {
                queue.poll();
            }

            queue.add(new int[] { nums[i], i });
            if(i >= k-1)
                result[count++] = queue.peek()[0];
        }

        return result;
    }
}
