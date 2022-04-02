package com.example.console;

import java.util.NoSuchElementException;
import java.util.Stack;

public class QueueImpWithStack<T> {
    private Stack<T> stack = new Stack<>();

    public boolean add(T item) {
        return offer(item);
    }

    public int size() {
        return stack.size();
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }

    public boolean offer(T item) {
        if (stack.empty()) {
            stack.push(item);
        } else {
            //stack 3,2,1, offer 4
            Stack<T> s1 = new Stack<>();
            //s1: 1,2,3
            while (!stack.isEmpty()) {
                s1.push(stack.pop());
            }
            //stack: 4
            stack.push(item);
            //stack 4,3,2,1
            while(!s1.isEmpty()) {
                stack.push(s1.pop());
            }
        }

        return true;
    }

    public T peek() {
        if (stack.isEmpty()) {
            return null;
        }

        return stack.peek();
    }

    public T poll() {
        if (stack.isEmpty()) {
            return null;
        }

        return stack.pop();
    }
}

