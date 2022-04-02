package com.example.console;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//Given a string, we can "shift" each of its letter to its successive letter, for example: "abc" -> "bcd". We can keep "shifting" which forms the sequence:
//
//"abc" -> "bcd" -> ... -> "xyz"
//Given a list of non-empty strings which contains only lowercase alphabets, group all strings that belong to the same shifting sequence.
//
//Example:
//
//Input: ["abc", "bcd", "acef", "xyz", "az", "ba", "a", "z"],
//Output:
//[
//  ["abc","bcd","xyz"],
//  ["az","ba"],
//  ["acef"],
//  ["a","z"]
//]
public class GroupShiftedStrings {
    public static List<List<String>> groupStrings(String[] strings) {
        List<List<String>> result = new ArrayList<>();

        if(strings == null || strings.length == 0)
            return result;
        Map<String, List<String>> map = new HashMap<>();
        for(String s: strings) {
            if(s.isEmpty())
                continue;
            char[] array = s.toCharArray();
            char ch = array[0];
            for(int i = 0; i < array.length; i++) {
                array[i] = (char)((array[i] - ch + 26) % 26);
            }

            String newStr = new String(array);
            if(!map.containsKey(newStr))
                map.put(newStr, new ArrayList<String>());
            List<String> values = map.get(newStr);
            values.add(s);
        }

        for(Map.Entry<String, List<String>> entry: map.entrySet()) {
            result.add(entry.getValue());
        }

        return result;
    }
}
