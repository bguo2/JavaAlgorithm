package com.example.console;

import java.util.Collections;
import java.util.PriorityQueue;

//find K smallest in an unsorted array
public class FindKSmallest {

    public int[] find(int[] source, int k) {
        if(source == null || source.length < 2)
            return source;
        //build max heap
        PriorityQueue<Integer> queue = new PriorityQueue<>(Collections.reverseOrder());

        int n = k > source.length ? source.length: k;
        for(int i = 0; i < n; i++) {
            queue.offer(source[i]);
        }

        if(k < source.length - 1) {
            for(int i = k; i < source.length; i++) {
                //if the current one is larger than the max in the heap, do nothing: so only K smallest is kept
                if(source[i] > queue.peek())
                    continue;
                //poll the largest
                queue.poll();
                //insert the current one
                queue.offer(source[i]);
            }
        }

        int[] result = new int[n];
        int i = n - 1;
        while (!queue.isEmpty()) {
            result[i--] = queue.poll();
        }

        return result;
    }
}
