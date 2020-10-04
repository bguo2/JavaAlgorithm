package com.example.console;

import java.util.LinkedList;
import java.util.Queue;

public class StackImpWithQueue<T> {

    private Queue<T> queue = new LinkedList<>();

    public T push(T item) {
        if(queue.isEmpty()) {
            queue.offer(item);
        }
        else {
            Queue<T> q1 = new LinkedList<>();
            q1.offer(item);
            while (!queue.isEmpty()) {
                q1.offer(queue.poll());
            }

            queue = q1;
        }
        return item;
    }

    public T pop() {
        if(queue.isEmpty()) {
            return null;
        }

        return queue.poll();
    }
}
