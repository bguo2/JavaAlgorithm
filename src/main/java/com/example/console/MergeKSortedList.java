package com.example.console;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;

interface SortedListIterator<E> extends Iterator<E> {
    E next();
    boolean hasNext();
}

public class MergeKSortedList<E extends Comparable<E>> {
    private class IteratorContainer<E extends Comparable<E>> implements Comparable<IteratorContainer<E>> {
        private SortedListIterator<E> iterator;
        private E value;

        IteratorContainer(SortedListIterator<E> iterator) {
            this.iterator = iterator;
            this.value = this.iterator.next();
        }

        public int compareTo(IteratorContainer<E> container) {
            return this.value.compareTo(container.value);
        }
    }

    public class ResultWrapper<E> implements SortedListIterator<E> {
        private List<E> result = new ArrayList<E>();
        private int curIndex = 0;

        @Override
        public boolean hasNext() {
            return curIndex != result.size();
        }

        @Override
        public E next(){
            return result.get(curIndex++);
        }

        public void add(E value){
            result.add(value);
        }
    }

    public SortedListIterator<E> mergeSortedList(List<SortedListIterator<E>> input) {
        ResultWrapper<E> result = new ResultWrapper<E>();
        if(input == null || input.size() == 0)
            return result;
        PriorityQueue<IteratorContainer<E>> queue = new PriorityQueue<>();
        for(SortedListIterator<E> e: input) {
            if(e != null && e.hasNext()) {
                queue.offer(new IteratorContainer(e));
            }
        }

        while (!queue.isEmpty()) {
            IteratorContainer<E> container = queue.poll();
            result.add(container.value);
            if(container.iterator.hasNext()){
                queue.offer(new IteratorContainer(container.iterator));
            }
        }
        return result;
    }
}
