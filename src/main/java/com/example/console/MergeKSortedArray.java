package com.example.console;

import java.util.PriorityQueue;

//merge K sorted arrays
public class MergeKSortedArray {
    private PriorityQueue<ArrayContainer> queue = new PriorityQueue<ArrayContainer>();

    private class ArrayContainer implements Comparable<ArrayContainer> {
        public int[] array;
        public int curIndex;

        public ArrayContainer(int[] array, int index) {
            this.array = array;
            this.curIndex = index;
        }

        public int compareTo(ArrayContainer target) {
            return this.array[curIndex] - target.array[target.curIndex];
        }
    }

    public int[] merge(int[][] arrays) {
        if(arrays == null || arrays.length == 0)
            return null;
        if(arrays.length == 1)
            return arrays[0];

        //add the first element of the arrays to the queue
        int k = arrays.length;
        int total = 0;
        for(int i = 0; i < k; i++) {
            total += arrays[i].length;
            queue.offer(new ArrayContainer(arrays[i], 0));
        }

        //poll the elements
        int[] result = new int[total];
        int i = 0;
        while (!queue.isEmpty()) {
            ArrayContainer p = queue.poll();
            result[i++] = p.array[p.curIndex];
            if(p.curIndex < p.array.length - 1) {
                queue.offer(new ArrayContainer(p.array, p.curIndex+1));
            }
        }

        return result;
    }
}
