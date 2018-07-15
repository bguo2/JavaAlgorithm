package com.example.console;

public class BinarySearch {

    public static int Search(int[] input, int target) {
        if(input == null || input.length == 0)
            return 0;
        int start = 0, end = input.length;
        while(start < end){
            int mid = start + (end - start)/2;
            if(target == input[mid])
                return mid;
            if(target < input[mid]) {
                end = mid - 1;
            }
            else {
                start = mid + 1;
            }
        }

        return start;
    }

    public static int Search(int[] input, int target, int start, int end){
        if(input == null || input.length == 0)
            return 0;
        if(start >= end)
            return start;
        int mid = start + (end - start) / 2;
        if(target == input[mid])
            return mid;
        if(target < input[mid]){
            return Search(input, target, start, mid-1);
        }
        else{
            return Search(input, target, mid+1, end);
        }
    }
}
