package com.example.console;
//Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order, not the kth distinct element.
//
//Example 1:
//
//Input: [3,2,1,5,6,4] and k = 2
//Output: 5
//Example 2:
//
//Input: [3,2,3,1,2,4,5,5,6] and k = 4
//Output: 4
public class KLargestElement {
    public int findKthLargest(int[] nums, int k) {
        if(k < 1 || nums == null)
            return -1;
        return getKth(nums, 0, nums.length-1, nums.length-k);
    }

    private int getKth(int[] nums, int start, int end, int k) {
        if(start == end)
            return nums[start];
        int index = getPivotIndex(nums, start, end);
        if(k == index)
            return nums[k];
        if(k < index)
            return getKth(nums, start, index - 1, k);
        else
            return getKth(nums, index+1, end, k);
    }

    //all numbers smaller than Pivot are at the left-hand side
    //all larger than the pivot are at the right-hand side
    private int getPivotIndex(int[] nums, int start, int end) {
        int pivot = nums[end];
        int index = start;
        for(int i = start; i<end; i++) {
            if(nums[i] < pivot) {
                swap(nums, index, i);
                index++;
            }
        }
        
        swap(nums, index, end);
        return index;
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
