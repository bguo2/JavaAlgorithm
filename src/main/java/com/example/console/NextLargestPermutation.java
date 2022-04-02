package com.example.console;
//31. Next Permutation
//Medium
//
//5050
//
//1739
//
//Add to List
//
//Share
//Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.
//
//If such an arrangement is not possible, it must rearrange it as the lowest possible order (i.e., sorted in ascending order).
//
//The replacement must be in place and use only constant extra memory.
//
//
//
//Example 1:
//
//Input: nums = [1,2,3]
//Output: [1,3,2]
//Example 2:
//
//Input: nums = [3,2,1]
//Output: [1,2,3]
//Example 3:
//
//Input: nums = [1,1,5]
//Output: [1,5,1]
//Example 4:
//
//Input: nums = [1]
//Output: [1]
//
//
//Constraints:
//
//1 <= nums.length <= 100
//0 <= nums[i] <= 100
// next Largest number is the same thing

//starting from right, if it is ascending, then no largest permutation e.g. 5,4,3,2,1
//step 1: find the first number which is not ascending from the right
//step 2: then find the one which is less or equal to it until the pos in step 1, swap 1
//step 3: reverse pos+1 to the end
public class NextLargestPermutation {
    public static void nextPermutation(int[] nums) {
        if(nums == null || nums.length == 1)
            return;
        int i = nums.length - 2;
        //step 1: find the first number which is not ascending from the right
        while(i >= 0 && nums[i] >= nums[i+1]) {
            i--;
        }

        //step 2: scanning from last, find the last number less than or equal to num[i]
        if(i >= 0) {
            int j = nums.length - 1;
            while(j >= 0 && nums[j] <= nums[i]) {
                j--;
            }
            swap(nums, i, j);
        }

        //reverse i+1;
        int start = i+1, end = nums.length - 1;
        while(start < end) {
            swap(nums, start, end);
            start++;
            end--;
        }
    }

    private static void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
