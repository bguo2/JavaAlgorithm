package com.example.console;

import java.util.ArrayList;
import java.util.List;

//count stars between || and input indexes, example *|***|**|**,
//input startindex [1, 1], endindexes [1, 6], then output [0, 3]
//startindex [1, 1], endindexes [1, 10], then output [0, 5]
public class CountStars {

    public static List<Integer> countStarts(String s, List<Integer> startIndex, List<Integer> endIndex) {
        List<Integer> result = new ArrayList<>();
        if(s == null || s.isEmpty() || startIndex == null || endIndex == null) {
            return result;
        }

        int count = Math.min(startIndex.size(), endIndex.size());
        for(int i = 0; i < count; i++) {
            int number = 0;
            if(startIndex.get(i) >= 1 && startIndex.get(i) < s.length()) {
                String targetStr = s.substring(startIndex.get(i) - 1, endIndex.get(i) > s.length() ? s.length() : endIndex.get(i));
                char[] target = targetStr.toCharArray();
                int startPos = -1;
                for (int j = 0; j < target.length; j++) {
                    if (target[j] == '|') {
                        if (startPos == -1) {
                            startPos = j;
                        }
                        else {
                            number += j - startPos - 1;
                            startPos = j;
                        }
                    }
                }
            }

            result.add(number);
        }

        return result;
    }
}
