package com.example.console;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.LinkedList;
import java.util.Queue;

public class ProducerConsumer<E> {
    private final Queue<E> queue;
    private final int capacity;

    public ProducerConsumer(int size) {
        this.capacity = size;
        queue = new LinkedList<>();
    }

    public void producer(E object) {
        if(object == null)
            throw new RuntimeException("null object");
        synchronized (queue) {
            while (this.queue.size() >= this.capacity) {
                try {
                    queue.wait();
                }
                catch (Exception e) {
                }
            }
            queue.add(object);
            queue.notifyAll();
        }
    }

    public E consumer() {
        synchronized (queue) {
            while (queue.isEmpty()) {
                try {
                    queue.wait();
                }
                catch (Exception e) {
                }
            }
            E obj = queue.poll();
            queue.notifyAll();
            return obj;
        }
    }
}
