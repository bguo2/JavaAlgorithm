package com.example.console;

import java.util.Stack;

//use stack implement queue
public class MyQueue<T> {
    private Stack<T> stack = new Stack<>();

    public void enqueue(T value) throws Exception{
        if(value == null)
            throw new Exception("null value is not allowed");
        if(stack.empty())
            stack.push(value);
        else
        {
            Stack<T> tmp = new Stack<>();
            while (!stack.empty()){
                tmp.push(stack.pop());
            }
            tmp.push(value);
            while (!tmp.isEmpty()){
                stack.push(tmp.pop());
            }
        }
    }

    public T dequeue() {
        if(stack.empty())
            return null;
        return stack.pop();
    }

    public void enqueue1(T value) throws Exception{
        if(value == null)
            throw new Exception("null value is not allowed");
        stack.push(value);
    }

    public T dequeue1(){
        if(stack.isEmpty())
            return null;
        Stack<T> tmp = new Stack<>();
        while (!stack.isEmpty()){
            tmp.push(stack.pop());
        }
        T value = tmp.pop();
        while (!tmp.isEmpty()){
            stack.push(tmp.pop());
        }

        return value;
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }
}
