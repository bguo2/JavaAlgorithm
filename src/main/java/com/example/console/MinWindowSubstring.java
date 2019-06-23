package com.example.console;

import javax.security.auth.callback.CallbackHandler;
import java.util.HashMap;

//Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).
//Input: S = "ADOBECODEBANC", T = "ABC"
//Output: "BANC"
//If there is no such window in S that covers all characters in T, return the empty string "".
//If there is such window, you are guaranteed that there will always be only one unique minimum window in S.
public class MinWindowSubstring {

    public static String getMinWindowSubstring(String S, String T){
        if(S == null || S.isEmpty())
            return "";
        if(T == null || T.isEmpty())
            return S;
        HashMap<Character, Integer> goalMap = new HashMap<>();
        HashMap<Character, Integer> map = new HashMap<>();
        for(int i = 0; i < T.length(); i++)
        {
            goalMap.put(T.charAt(i), goalMap.getOrDefault(T.charAt(i), 0)+1);
        }

        int goalSize = T.length();
        int minSize = S.length();
        String result = "";
        int pre = 0, total = 0;
        for(int i = 0; i < S.length(); i++)
        {
            Character c = S.charAt(i);

            if(!goalMap.containsKey(c))
                continue;
            //contain c
            int count = map.getOrDefault(c, 0);
            if(count < goalMap.get(c))
                total++;
            map.put(c, count+1);
            if(total == goalSize)
            {
                //when total reaches the goal, trim from left until no more chars can be trimmed.
                while (!map.containsKey(S.charAt(pre)) || map.get(S.charAt(pre)) > goalMap.get(S.charAt(pre)))
                {
                    if(map.containsKey(S.charAt(pre)) && map.get(S.charAt(pre)) > goalMap.get(S.charAt(pre)))
                    {
                        if(map.get(S.charAt(pre)) == 1)
                            map.remove(S.charAt(pre));
                        else
                            map.put(S.charAt(pre), map.get(S.charAt(pre)) - 1);
                    }
                    pre++;
                }

                if(minSize > i-pre+1)
                {
                    minSize = i - pre + 1;
                    result = S.substring(pre, i+1);
                    total--;
                    if(map.get(S.charAt(pre)) == 1)
                        map.remove(S.charAt(pre));
                    else
                        map.put(S.charAt(pre), map.get(S.charAt(pre)) - 1);
                    pre++;
                }
            }
        }

        return result;
    }
}
