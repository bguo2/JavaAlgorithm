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
        int pindex = partition(start, end);
        quickSort(start, pindex-1);
        quickSort(pindex+1, end);
    }

    //after partition, all elements < pivot will be at its left side,
    //all > pivot will be at its right side
    private int partition(int start, int end) {
        int pivot = numbers[end];
        int pindex = start;
        for(int i = start; i < end; i++) {
            if(numbers[i] <= pivot) {
                swap(i, pindex);
                pindex++;
            }
        }

        swap(pindex, end);
        return pindex;
    }

    private void swap(int i, int j) {
        int tmp = numbers[i];
        numbers[i] = numbers[j];
        numbers[j] = tmp;
    }
 }
