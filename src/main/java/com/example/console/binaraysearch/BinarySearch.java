package com.example.console;

public class BinarySearch {

    //find the target value in the sorted array, if it is not found, return the index where it should be inserted
    //left bound
    public static int Search(int[] input, int target) {
        if(input == null || input.length == 0)
            return 0;
        //target is the last one
        if(target > input[input.length - 1])
            return input.length;
        int start = 0, end = input.length - 1;
        while(start < end) {
            int mid = start + (end - start)/2;
            if(input[mid] < target) {
                start = mid + 1;
            }
            else {
                end = mid;
            }
        }

        return start;
    }

    //right bound for duplicates e.g. 1,2,2,4, target 2, the index will be 2
    public static int Search1(int[] input, int target) {
        if(input == null || input.length == 0)
            return 0;
        //target is the last one
        if(target > input[input.length - 1])
            return input.length;
        int start = 0, end = input.length  -1;
        while(start < end) {
            int mid = start + (end - start + 1)/2;
            if(input[mid] > target) {
                end = mid - 1;
            }
            else {
                start = mid;
            }
        }
        return start;
    }

    //search target value in the sorted array
    public static int Search(int[] input, int target, int start, int end){
        if(input == null || input.length == 0)
            return 0;
        if(start >= end)
            return start;
        int mid = start + (end - start) / 2;
        if(target == input[mid])
            return mid;
        if(target > input[mid]){
            return Search(input, target, mid+1, end);
        }
        else{
            return Search(input, target, start, mid);
        }
    }

    //search target value in the rotated array
    //Suppose a sorted array is rotated at some pivot unknown to you beforehand. (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
    //
    //You are given a target value to search. If found in the array return its index, otherwise return -1. You may assume no
    // duplicate exists in the array.
    //In order to use binary search on the rotated sorted array, we need to determine how to update the left and right pointers.
    // There are two major cases as shown below:
    // 3  4  5  6  1  2 => mid = 5  a[mid] > a[left] left side is sorted
    // 5  6  1  2  3  4 => mid = 1  a[mid] < a[right] right side is sorted
    //Once the two cases are identified, the problem is straightforward to solve. We only need to check if the target element is
    // in the sorted side, and based on that move left or right pointers.
    public int searchRotated(int[] nums, int target) {
        return binarySearch(nums, 0, nums.length-1, target);
    }

    public int binarySearch(int[] nums, int left, int right, int target){
        if(left>right)
            return -1;
        int mid = left + (right-left)/2;
        if(target == nums[mid])
            return mid;
        //left is sorted e.g.  6 7 8 0 1
        if(nums[left] <= nums[mid]){
            if(nums[left]<=target && target<nums[mid]) {
                return binarySearch(nums,left, mid-1, target);
            }else{
                return  binarySearch(nums, mid+1, right, target);
            }
        }
        //right is sorted
        else {
            if(nums[mid]<target && target<=nums[right]){
                return  binarySearch(nums,mid+1, right, target);
            }else{
                return  binarySearch(nums, left, mid-1, target);
            }
        }
    }

    public int searchRotated1(int[] nums, int target) {
        int left = 0;
        int right= nums.length-1;

        while(left<=right){
            int mid = left + (right-left)/2;
            if(target==nums[mid])
                return mid;

            if(nums[left]<=nums[mid]){
                if(nums[left]<=target && target<nums[mid]){
                    right=mid-1;
                }else{
                    left=mid+1;
                }
            }else{
                if(nums[mid]<target && target<=nums[right]){
                    left=mid+1;
                }else{
                    right=mid-1;
                }
            }
        }

        return -1;
    }

    //duplicates is allowed
    public boolean searchDuplicates(int[] nums, int target) {
        int left=0;
        int right=nums.length-1;

        while(left<=right){
            int mid = (left+right)/2;
            if(nums[mid] == target)
                return true;

            if(nums[left] < nums[mid]){
                if(nums[left]<=target && target<nums[mid]){
                    right=mid-1;
                }
                else{
                    left=mid+1;
                }
            }
            else if(nums[left] > nums[mid]){
                if(nums[mid]<target &&target<=nums[right]){
                    left=mid+1;
                }
                else{
                    right=mid-1;
                }
            }
            //move left cursor
            else{
                left++;
            }
        }

        return false;
    }

    //find min in the rotated array: findMin(nums, 0, nums.length-1);
    private int findMin(int[] nums, int left, int right) {
        if(left >= right)
            return nums[left];
        //left is sorted
        if(nums[left] < nums[right])
            return nums[left];
        int mid = left + (right - left) / 2;
        return Math.min(findMin(nums, mid+1, right), findMin(nums, left, mid));
    }

    public int findMin(int[] nums) {
        int i = 0, j = nums.length - 1;
        while(i < j) {
            if(nums[i] < nums[j])
                return nums[i];
            int mid = i + (j - i) / 2;
            //mid should < right: it is rotated
            if(nums[mid] > nums[j])
                i = mid + 1;
            else
                j = mid;
        }

        return nums[i];
    }
}
