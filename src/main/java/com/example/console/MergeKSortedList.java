package com.example.console;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;

interface SortedListIterator<E> extends Iterator<E> {
    E next();
    boolean hasNext();
}

public class MergeKSortedList<E extends Comparable<? super E>> {
    private class IteratorContainer<E extends Comparable<? super E>> implements Comparable<IteratorContainer<E>> {
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

    public class SortedListIteratorImp<E> implements SortedListIterator<E> {
        private List<E> list = new ArrayList<>();
        private Iterator<E> iterator = list.iterator();

        @Override
        public boolean hasNext() {
            return iterator.hasNext();
        }

        @Override
        public E next() {
            E val = iterator.next();
            return val;
        }

        public void add(E val) {
            list.add(val);
            iterator = list.iterator();
        }
    }

    public SortedListIterator<E> mergeSortedList(List<SortedListIterator<E>> input) {
        SortedListIteratorImp<E> result = new SortedListIteratorImp<E>();
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
