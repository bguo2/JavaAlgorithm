package com.example.console;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//You are given a string, s, and a list of words, words, that are all of the same length.
// Find all starting indices of substring(s) in s that is a concatenation of each word in words exactly once and without any intervening characters.
//
//For example, given: s="barfoothefoobarman" & words=["foo", "bar"], return [0,9].
//Input : S: "catbatatecatatebat"
//        L: ["cat", "ate", "bat"]
//Output : 0 3 9
//Explanation :
//// at index 0 : catbatate
//// at index 3 : batatecat
//// at index 9 : catatebat
//Input : S : "abcdababcd"
//        L : ["ab", "ab", "cd"]
//Output : 0 2 4
//Explanation :
//// at index 0 : abcdab
//// at index 2 : cdabab
//// at index 4 : ababcd
//
//Input : S : "abcdababcd"
//        L : ["ab", "ab"]
//Output : 4
public class StringConcatenation {

    public static List<Integer> getStartingIndex(String s, String[] words){
        List<Integer> result = new ArrayList<>();
        if(s == null || s.length() == 0 || words == null || words.length == 0)
            return result;
        Map<String, Integer> map = new HashMap<>();
        //the count number of key
        for(String word: words) {
            if(map.containsKey(word)){
                map.put(word, map.get(word) + 1);
            }
            else
                map.put(word, 1);
        }

        int wordLen = words[0].length();
        Map<String, Integer> tmpMap = new HashMap<>();
        for(int i = 0; i < s.length() - wordLen; i++) {
            tmpMap.clear();
            boolean noMatch = false;
            //must contain all words
            for(int j = 0; j < words.length; j++) {
                if(i + (j+1)*wordLen > s.length()){
                    noMatch = true;
                    break;
                }
                //get the sub string one by one
                String sub = s.substring(i + j*wordLen, i + (j+1)*wordLen);
                //find the sub string in words
                if(map.containsKey(sub)) {
                    //check tmpMap
                    if(tmpMap.containsKey(sub)){
                        tmpMap.put(sub, tmpMap.get(sub)+1);
                        //doesn't match: duplicates count number
                        if(tmpMap.get(sub) > map.get(sub)) {
                            noMatch = true;
                            break;
                        }
                    }
                    else
                        tmpMap.put(sub, 1);
                }
                else {
                    noMatch = true;
                    break;
                }
            }
            //check match or not?
            if(!noMatch && tmpMap.size() == map.size()) {
                //matched
                for(String key: tmpMap.keySet()) {
                    if(tmpMap.get(key) != map.get(key)) {
                        noMatch = true;
                        break;
                    }
                }
                if(!noMatch)
                    result.add(i);
            }
        }

        return result;
    }
}
