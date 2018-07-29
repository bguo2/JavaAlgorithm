package com.example.console;

public class QuickSort {
    private int[] numbers;

    public int[] quickSort(int[] numbers){
        if(numbers == null || numbers.length < 2)
            return numbers;
        this.numbers = numbers;
        quickSort(0, numbers.length - 1);
        return this.numbers;
    }

    private void quickSort(int start, int end){
        if(start >= end)
            return;
        int mid = (start + end) / 2;
        int pivot = numbers[mid];

        int low= start, high = end;
        while (low <= high) {
            while (numbers[low]< pivot)
                low++;
            while (numbers[high] > pivot)
                high--;
            if(low <= high){
                int tmp = numbers[low];
                numbers[low]= numbers[high];
                numbers[high] = tmp;
                low++;
                high--;
            }
        }

        if(start < high)
            quickSort(start, high);
        if(end > low)
            quickSort(low, end);
    }
}
