package com.example.console.cache;

import java.util.*;

//least frequently used cache
public class LeastFrenquentUsed<K,V> {
    private final Map<K, V> valueMap; //store key/value
    private final Map<K, Integer> keyFreqMap; //store K's frequency
    private final Map<Integer, Set<K>> frequencyMap; //store frequency of the key
    private int cap; //capacity
    private int min;

    public LeastFrenquentUsed(int cap) {
        this.cap = cap;
        this.valueMap = new HashMap<>();
        this.keyFreqMap = new HashMap<>();
        this.frequencyMap = new TreeMap<>();
        this.min = -1;
    }

    public V get(K key) {
        V value = valueMap.get(key);
        //update frequency
        if(value != null) {
            updateFrequency(key);
        }
        return value;
    }

    private void updateFrequency(K key) {
        //exists: update freq map
        int freq = this.keyFreqMap.get(key);
        this.keyFreqMap.put(key, freq + 1);
        //update frequencyMap
        Set<K> list = frequencyMap.get(freq);
        list.remove(key);
        if(this.min == freq && this.frequencyMap.get(freq).isEmpty())
            this.min++;
        //add new freq
        Set<K> list1 = frequencyMap.get(freq+1);
        if(list1 == null)
            list1 = new LinkedHashSet<>();
        list1.add(key);
        frequencyMap.put(freq+1, list1);
    }

    public void set(K key, V value) {
        if(this.valueMap.containsKey(key)) {
            this.valueMap.put(key, value);
            updateFrequency(key);
            return;
        }
        //not in the map
        if(this.valueMap.size() == this.cap) {
            //remove the min
            Set<K> set = this.frequencyMap.get(this.min);
            K minKey = set.iterator().next();
            set.remove(minKey);
            this.keyFreqMap.remove(minKey);
            this.valueMap.remove(minKey);
        }
        this.valueMap.put(key, value);
        this.keyFreqMap.put(key, 1);
        Set<K> set = this.frequencyMap.get(1);
        if(set == null)
            set = new LinkedHashSet<>();
        set.add(key);
        this.frequencyMap.put(1, set);
        this.min = 1;
    }
}
