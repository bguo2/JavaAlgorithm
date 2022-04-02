package com.example.console;

public class MergeSort {

    public static void sort(int[] input, int l, int r) {
        if(l >= r)
            return;
        int mid = l + (r-l)/2;
        sort(input, l, mid);
        sort(input, mid+1, r);
        merge(input, l, mid, r);
    }

    private static void merge(int[] input, int l, int mid, int r) {
        int n1 = mid - l + 1;
        int n2 = r - mid;
        int[] ltmp = new int[n1];
        int[] rtmp = new int[n2];
        for(int i = 0; i < n1; i++)
            ltmp[i] = input[l+i];
        for(int i = 0; i < n2; i++)
            rtmp[i] = input[mid+1+i];
        int k = l, i = 0, j = 0;
        while(i < n1 && j < n2) {
            if(ltmp[i] <= rtmp[j]) {
                input[k++] = ltmp[i++];
            }
            else {
                input[k++] = rtmp[j++];
            }
        }

        while (i < n1) {
            input[k++] = ltmp[i++];
        }
        while (j < n2) {
            input[k++] = rtmp[j++];
        }
    }
}
