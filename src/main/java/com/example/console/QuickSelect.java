package com.example.console;

import java.util.Arrays;

//select Kth largest integer from n unsorted integers, k from 1..n
public class QuickSelect {
    int [] nums;

    public int findKthNumber(int k, int[] numbers) {
        if(numbers == null || numbers.length < k)
            return -1;
        this.nums = numbers;
        return quickselect(0, numbers.length - 1, numbers.length - k);
    }

    public int quickselect(int left, int right, int k_smallest) {
    /*
    Returns the k-th smallest element of list within left..right.
    */

        if (left == right) // If the list contains only one element,
            return this.nums[left];  // return that element

        // select a random pivot_index
        //Random random_num = new Random();
        //int pivot_index = left + random_num.nextInt(right - left);

        int pivot_index = right;
        pivot_index = partition(left, right);

        // the pivot is on (N - k)th smallest position
        if (k_smallest == pivot_index)
            return this.nums[k_smallest];
            // go left side
        else if (k_smallest < pivot_index)
            return quickselect(left, pivot_index - 1, k_smallest);
        // go right side
        return quickselect(pivot_index + 1, right, k_smallest);
    }

    public int partition(int left, int right) {
        int pivot = this.nums[right];
        // 1. move pivot to end
        int store_index = left;
        // 2. move all smaller elements to the left
        for (int i = left; i <= right; i++) {
            if (this.nums[i] < pivot) {
                swap(store_index, i);
                store_index++;
            }
        }
        // 3. move pivot to its final place
        swap(store_index, right);
        return store_index;
    }

    public void swap(int a, int b) {
        int tmp = this.nums[a];
        this.nums[a] = this.nums[b];
        this.nums[b] = tmp;
    }
}
