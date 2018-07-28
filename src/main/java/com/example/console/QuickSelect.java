package com.example.console;

import java.util.Arrays;

//select Kth largest integer from n unsorted integers, k from 1..n
public class QuickSelect {
    private int[] numbers;

    public int findKthNumber(int k, int[] numbers) {
        if(numbers == null || numbers.length < k)
            return -1;
        this.numbers = numbers;
        return find(k, 0, numbers.length - 1);
    }

    private int find(int k, int start,  int end){

        int pivot = numbers[end];

        int left = start, right = end;
        while (left < right) {

            while (left < right && numbers[left] < pivot)
                left++;
            while ((left < right && numbers[right] > pivot))
                right--;
            if(left < right)
                swap(left, right);
        }

        swap(left, right);
        if(k == left + 1)
            return pivot;
        if(k < left + 1)
            return find(k, start, left - 1);
        return find(k, left + 1, end);
    }

    private void swap(int i, int right) {
        int tmp = numbers[i];
        numbers[i] = numbers[right];
        numbers[right] = tmp;
    }

    //find the K smallest numners in an array
    public int[] findKSmallestNumber(int k, int[] numbers)
    {
        if(numbers == null || numbers.length < k)
            return null;
        this.numbers = numbers;
        find(k, 0, numbers.length - 1);
        int tmp = k;
        while(k > 1)
        {
            k--;
            find(k,0, k);
        }
        return Arrays.copyOf(this.numbers, tmp);
    }
}
