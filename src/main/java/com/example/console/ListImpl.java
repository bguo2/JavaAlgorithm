package com.example.console;

import java.util.HashMap;
import java.util.concurrent.Callable;
import java.util.function.Consumer;
import java.util.function.Function;

public class ListImpl<T extends Comparable<? super T>> {

    private Node<T> head = null, listLast = null;
    public class Node<T> {
        public Node<T> next, previous;
        private T data;
        private Node(T value) {
            data = value;
        }
        public Node() {}
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

    public Node<T> add(T value) {
        if(head == null) {
            head = new Node<>(value);
            listLast = head;
        }
        else {
            listLast.next = new Node<>(value);
            listLast = listLast.next;
        }

        return listLast;
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
                //listLast
                if(current == listLast) {
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

    public Node<T> reverse(Node<T> node) {
        if(node == null || node.next == null)
            return node;
        Node<T> tmp = reverse(node.next);
        node.next.next = node;
        node.next = null;
        return tmp;
    }

    public void reversePrint(Node<T> node) {
        if(node == null)
            return;
        reversePrint(node.next);
        System.out.println(node.data);
    }

    //25. Reverse Nodes in k-Group
    //Hard
    //
    //3051
    //
    //383
    //
    //Add to List
    //
    //Share
    //Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.
    //
    //k is a positive integer and is less than or equal to the length of the linked list. If the number of nodes is not a multiple of k then left-out nodes, in the end, should remain as it is.
    //
    //Follow up:
    //
    //Could you solve the problem in O(1) extra memory space?
    //You may not alter the values in the list's nodes, only nodes itself may be changed.
    //
    //
    //Example 1:
    //
    //
    //Input: head = [1,2,3,4,5], k = 2
    //Output: [2,1,4,3,5]
    //Example 2:
    //
    //
    //Input: head = [1,2,3,4,5], k = 3
    //Output: [3,2,1,4,5]
    //Example 3:
    //
    //Input: head = [1,2,3,4,5], k = 1
    //Output: [1,2,3,4,5]
    //Example 4:
    //
    //Input: head = [1], k = 1
    //Output: [1]
    public Node<T> reverse(Node<T> start, Node<T> end) {
        Node<T> last = end.next;
        Node<T> p = start;
        Node<T> pre = null;
        while(p != last) {
            Node<T> next = p.next;
            p.next = pre;
            pre = p;
            p = next;
        }

        start.next = last;
        return pre;
    }

    public Node<T> reverseKGroup(Node<T> head, int k) {
        if(head == null || head.next == null || k < 1)
            return head;
        Node<T> newStart = head;
        Node<T> newEnd = head;
        Node<T> p = head;
        int i = 0;
        Node<T> fake = new Node<T>();
        fake.next = head;
        while(p != null) {
            i++;
            if(i % k == 0) {
                Node<T> next = p.next;
                Node<T> tmp = reverse(newStart, p);
                if(newStart == head) {
                    fake.next = tmp;
                }
                else {
                    newEnd.next = tmp;
                }
                newEnd = newStart;
                p = next;
                newStart = next;
            }
            else {
                p = p.next;
            }
        }

        return fake.next;
    }

    //palidrome linked list
    //Given a singly linked list, determine if it is a palindrome.
    //
    //Example 1:
    //
    //Input: 1->2
    //Output: false
    //Example 2:
    //
    //Input: 1->2->2->1
    //Output: true
   Node<T> left;
    public boolean isPalindrome(Node<T> head) {
        left = head;
        return helper(head);
    }

    private boolean helper(Node<T> right) {
        if(right == null)
            return true;
        boolean ret = helper(right.next);
        if(!ret)
            return false;
        boolean tmp = left.data.equals(right.data);
        left = left.next;
        return tmp;
    }
}
