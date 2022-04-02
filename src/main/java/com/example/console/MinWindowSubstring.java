package com.example.console;

import javax.security.auth.callback.CallbackHandler;
import java.util.HashMap;

//Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).
//Input: S = "ADOBECODEBANC", T = "ABC"
//Output: "BANC"
//If there is no such window in S that covers all characters in T, return the empty string "".
//If there is such window, you are guaranteed that there will always be only one unique minimum window in S.
public class MinWindowSubstring {
    public static  String getMinWindowSubstring(String s, String t) {
        if(s == null || s.length() == 0 || t==null || t.length() ==0 || t.length() > s.length())
            return "";
        HashMap<Character, Integer> map = new HashMap<>();
        for(int i = 0; i < t.length(); i++) {
            map.put(t.charAt(i), map.getOrDefault(t.charAt(i), 0) + 1);
        }

        int total = 0;
        String result = "";
        int start = 0;
        int min = Integer.MAX_VALUE;
        HashMap<Character, Integer> tmpMap = new HashMap<>();
        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(!map.containsKey(c))
                continue;
            int count = tmpMap.getOrDefault(c, 0);
            if(count < map.get(c)) {
                total++;
            }
            tmpMap.put(c, count+1);

            if(total < t.length())
                continue;
            c = s.charAt(start);
            while(!map.containsKey(c) || tmpMap.get(c) > map.get(c)) {
                if(map.containsKey(c) && tmpMap.get(c) > map.get(c)) {
                    if(tmpMap.get(c) == 1)
                        tmpMap.remove(c);
                    else
                        tmpMap.put(c, tmpMap.get(c)-1);
                }
                start++;
                c = s.charAt(start);
            }

            if(min > i - start +1) {
                result = s.substring(start, i+1);
                min = result.length();
                c = s.charAt(start);
                if(tmpMap.get(c) == 1)
                    tmpMap.remove(c);
                else
                    tmpMap.put(c, tmpMap.get(c)-1);
                start++;
                total--;
            }
        }

        return result;
    }
}
