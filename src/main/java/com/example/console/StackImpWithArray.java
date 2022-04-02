package com.example.console;

import java.util.EmptyStackException;
import java.util.Stack;

public class StackImpWithArray<E>  {
    private final int size;
    private final E[] items;
    private int top;

    public StackImpWithArray(int size) {
        this.size = size;
        this.items = (E[]) new Object[size];
        this.top = - 1;
    }

    public synchronized E pop() throws EmptyStackException {
        if(this.top == -1)
            throw new EmptyStackException();
        E e = this.items[this.top];
        this.items[this.top] = null;
        this.top--;
        return e;
    }

    public synchronized E push(E e) throws Exception {
        if(this.top == this.size - 1)
            throw new Exception("stack is full");
        this.items[++this.top] = e;
        return e;
    }

    public E peek() {
        if(this.top == -1)
            return null;
        return this.items[this.top];
    }
}
