package com.example.console.cache;

import java.util.HashMap;

//lease recently used
public class LeasRecentUsed<K,V> {
    class Node<V>{
        V value;
        Node prev;
        Node next;

        public Node(V value){
            this.value=value;
        }
    }
    Node head;
    Node tail;
    HashMap<K, Node<V>> map = null;
    int cap = 0;

    public LeasRecentUsed(int capacity) {
        this.cap = capacity;
        this.map = new HashMap<>();
    }

    public V get(K key) {
        if(map.get(key)==null){
            return null;
        }

        //move to tail
        Node<V> t = map.get(key);

        removeNode(t);
        offerNode(t);

        return t.value;
    }

    public void put(K key, V value) {
        if(map.containsKey(key)){
            Node t = map.get(key);
            t.value = value;

            //move to tail
            removeNode(t);
            offerNode(t);
        }else{
            if(map.size()>=cap){
                //delete head
                map.remove(key);
                removeNode(head);
            }

            //add to tail
            Node<V> node = new Node(value);
            offerNode(node);
            map.put(key, node);
        }
    }

    private void removeNode(Node<V> n){
        if(n.prev!=null){
            n.prev.next = n.next;
        }else{
            head = n.next;
        }

        if(n.next!=null){
            n.next.prev = n.prev;
        }else{
            tail = n.prev;
        }
    }

    private void offerNode(Node<V> n) {
        if(tail!=null){
            tail.next = n;
        }

        n.prev = tail;
        n.next = null;
        tail = n;

        if(head == null){
            head = tail;
        }
    }
}
