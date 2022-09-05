package com.example.console;

import java.util.HashMap;
import java.util.Map;

// Sherlock considers a string to be valid if all characters of the string appear the same number of times.
// It is also valid if he can remove just 1 character at 1 index in the string, and the remaining characters will occur the same
// number of times. Given a string , determine if it is valid. If so, return YES, otherwise return NO.
//Example
//s=abc
//This is a valid string because frequencies are {a:1 b:1 c:1}.
//s=abcc
//This is a valid string because we can remove one c and have 1 of each character in the remaining string.
//aabbcd => invalid
//aabbccddeefghi => invalid
//This string is not valid as we can only remove  occurrence of . That leaves character frequencies of .
public class ValidString {
    public static String isValid(String s) {
        if(s.length() < 2)
            return "YES";
        Map<Character, Integer> map = new HashMap<>();
        for(int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            map.put(ch, map.getOrDefault(ch, 0)+1);
        }

        Map<Integer, Integer> freqMap = new HashMap<>();
        for(Map.Entry<Character, Integer> entry: map.entrySet()) {
            int num = entry.getValue();
            freqMap.put(num, freqMap.getOrDefault(num, 0)+1);
        }
        //aaabbc 1:1, 2:1, 3:1
        if(freqMap.size() > 2)
            return "NO";
        if(freqMap.size() == 1)
            return "YES";
        int[] keys = new int[2];
        int[] values = new int[2];
        int index = 0;
        for(Integer key : freqMap.keySet()) {
            keys[index] = key;
            values[index] = freqMap.get(key);
            index++;
        }
        //frequency diff must be 1 and its freq must be 1 (can be deleted with 1 char)
        //aaabb 3:1. 2:1, aaabbbb 3:1, 4:1 => yes
        if(map.size() == 2 && (values[0] == 1 || values[1] == 1) && Math.abs(keys[0]-keys[1]) == 1)
            return "YES";
        //aaabbbcc 3:2 2:1 => no  aaabbbcccc 3:2 4:1 =>yes
        if(map.size() > 2) {
            if(Math.abs(keys[0]-keys[1]) != 1)
                return "NO";
            if((values[0] == 1 && keys[0] > keys[1]) || (values[1] == 1 && keys[1] > keys[0])) {
                return "YES";
            }
        }
        return "NO";
        /*
        int first = 0, firstKey=0, second=0, secondKey=0, count = 0;
        for(Integer key: freqMap.keySet()) {
            if(first == 0) {
                first = freqMap.get(key);
                firstKey = key;
                continue;
            }
            second = freqMap.get(key);
            secondKey = key;
            //abcddee 1:3 2:2
            if((first > 1 && second > 1) ||
                    //aaaaddeeff 1:4 3:2
                    (Math.abs(firstKey-secondKey) > 1 && firstKey != 1 && secondKey != 1)
                    //aaaabc 1:2 4:1
                    || (Math.abs(firstKey-secondKey)> 1 && Math.abs(first-second) == 1)) {
                return "NO";
            }
        }
        return "YES";
         */
    }
}
