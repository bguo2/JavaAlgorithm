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
            stack.push(item);
            Stack<T> stack1 = new Stack<>();
            while (!stack.empty()) {
                stack1.push(stack.pop());
            }

            stack = stack1;
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

