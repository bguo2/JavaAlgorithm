package com.example.console;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class MyStack<T> {
    Queue<T> queue = new LinkedList<>();

    public void push(T value) {
        if(queue.isEmpty())
            queue.add(value);
        else{
            Queue<T> tmp = new LinkedList<>();
            tmp.add(value);
            while (!queue.isEmpty()){
                tmp.add(queue.poll());
            }
            queue = tmp;
        }
    }

    public T pop() {
        return queue.poll();
    }

    public T peek(){
        return queue.peek();
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }
}
