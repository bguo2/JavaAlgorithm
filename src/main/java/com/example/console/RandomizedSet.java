package com.example.console;

import java.util.*;

//Insert Delete GetRandom O(1)
//Implement the RandomizedSet class:
//
//RandomizedSet() Initializes the RandomizedSet object.
//bool insert(int val) Inserts an item val into the set if not present. Returns true if the item was not present, false otherwise.
//bool remove(int val) Removes an item val from the set if present. Returns true if the item was present, false otherwise.
//int getRandom() Returns a random element from the current set of elements (it's guaranteed that at least one element exists when this method is called). Each element must have the same probability of being returned.
//You must implement the functions of the class such that each function works in average O(1) time complexity.
public class RandomizedSet {
    private Map<Integer, Integer> hash;
    private List<Integer> list;
    private Random random;
    public RandomizedSet() {
        hash = new HashMap<>();
        random = new Random();
        list = new ArrayList<>();
    }

    public boolean insert(int val) {
        if(!hash.containsKey(val))
        {
            hash.put(val,list.size());
            list.add(val);
            return true;
        }
        return false;
    }

    public boolean remove(int val) {
        if(hash.containsKey(val))
        {
            int location = hash.get(val);
            if(location < list.size()-1)
            {
                int lastElement = list.get(list.size()-1);
                list.set(location, lastElement);
                hash.put(lastElement, location);
            }
            list.remove(list.size()-1);
            hash.remove(val);
            return true;
        }
        return false;
    }

    public int getRandom() {
        return list.get(random.nextInt(list.size()));
    }
}
