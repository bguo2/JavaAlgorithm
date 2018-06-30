package com.example.console;

import java.util.concurrent.Callable;
import java.util.function.Consumer;
import java.util.function.Function;

public class ListImpl<T> {
    private Node<T> head = null, last = null;
    public class Node<T> {
        public Node<T> next, previous;
        private T data;
        private Node(T value) {
            data = value;
        }
    }

    public Node<T> getHead() {
        if(head == null)
            return null;
        return head;
    }

    public void setHead(Node<T> value) {
        head = value;
    }

    public void iterate(Consumer<T> consumer) {
        Node<T> current = head;
        while(current != null) {
            consumer.accept(current.data);
            current = current.next;
        }
    }

    public void add(T value) {
        if(head == null) {
            head = new Node<>(value);
            last = head;
        }
        else {
            last.next = new Node<>(value);
            last = last.next;
        }
    }

    public void remove(T value) {
        if(head == null)
            return;
        Node<T> current = head, pre = null;
        while (current != null){
            if(current.data.equals(value)) {
                //head
                if(current == head) {
                    head = current.next;
                    return;
                }
                //last
                if(current == last) {
                    pre.next = null;
                    return;
                }
                //middle
                pre.next = current.next;
                return;
            }
            pre = current;
            current = current.next;
        }
    }

    public void reverse() {
        if(head.next == null)
            return;
        Node<T> current = head, pre=null, next;
        while(current != null) {
            next = current.next;
            current.next = pre;
            pre = current;
            current = next;
        }
        head = pre;
    }

    public Node<T> reverse(Node<T> node){
        if(node == null || node.next == null)
            return node;
        Node<T> tmp = reverse(node.next);
        node.next.next = node;
        node.next = null;
        return tmp;
    }
}
