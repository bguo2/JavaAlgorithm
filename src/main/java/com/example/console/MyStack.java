package com.example.console;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;

public class MyStack<T> {
    ArrayList<T> queue = new ArrayList<>();

    public void push(T value) {
        if(queue.isEmpty())
            queue.add(value);
        else{
            ArrayList<T> tmp = new ArrayList<>();
            tmp.add(value);
            while (!queue.isEmpty()){
                tmp.add(queue.remove(0));
            }
            queue = tmp;
        }
    }

    public T pop() {
        return queue.remove(0);
    }

    public T peek(){
        return queue.get(0);
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }
}
