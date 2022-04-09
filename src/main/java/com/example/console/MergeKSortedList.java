package com.example.console;

import java.util.*;

interface SortedListIterator<E> extends Iterator<E> {
    E next();
    boolean hasNext();
}

public class MergeKSortedList<E extends Comparable<? super E>> {
    //E extends any class which is super class of E and implements Comparable
    //PriorityQueue will add IteratorContainer instead of E
    //IteratorContainer implements Comparable, os it is Comparable<IteratorContainer<E>> instead of Comparable<E>
    private class IteratorContainer<E extends Comparable<? super E>> implements Comparable<IteratorContainer<E>> {
        private SortedListIterator<E> curIterator;
        private E curValue;

        public IteratorContainer(SortedListIterator<E> e) {
            this.curIterator = e;
            this.curValue = e.next();
        }

        @Override
        public int compareTo(IteratorContainer<E> that) {
            return this.curValue.compareTo(that.curValue);
        }

        public boolean hasNext() {
            return this.curIterator.hasNext();
        }

    }

    private PriorityQueue<IteratorContainer<E>> queue;

    public MergeKSortedList() {
        queue = new PriorityQueue<>();
    }

    public List<E> mergeSortedList(List<SortedListIterator<E>> input) {
        List<E> result = new ArrayList<>();
        if(input == null || input.size() == 0) {
            return result;
        }

        for(SortedListIterator<E> e : input) {
            if(e.hasNext()) {
                queue.offer(new IteratorContainer<>(e));
            }
        }

        while (!queue.isEmpty()) {
            IteratorContainer<E> iter = queue.poll();
            result.add(iter.curValue);
            if(iter.hasNext()) {
                queue.offer(new IteratorContainer<>(iter.curIterator));
            }
        }

        return result;
    }
}
