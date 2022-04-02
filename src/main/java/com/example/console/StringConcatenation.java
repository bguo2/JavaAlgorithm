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
//"aaaaaaaaaaaaaa"
//["aa","aa"]
//[0,1,2,3,4,5,6,7,8,9,10]
//"wordgoodgoodgoodbestword", words = ["word","good","best","word"]
//Output: []
public class StringConcatenation {

    public static List<Integer> getStartingIndex(String s, String[] words) {
        List<Integer> result = new ArrayList<>();
        if(s == null || s.length() == 0 || words == null || words.length == 0)
            return result;
        Map<String, Integer> map = new HashMap<>();
        //the count number of key
        for(String word: words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }

        int len = words[0].length();
        int n = words.length;
        for(int i = 0; i <= s.length() - n*len; i++) {
            int j = 0;
            Map<String, Integer> tmpMap = new HashMap<>();
            //each sub string is in the map: if one of the occurrence is not equal to the one in the map,
            //then it is not match, e.g. map={A, B, C}, sub string can be {D, B, C} {A, A, B}, {A, B, B}
            //{D, B, C} was killed at the !map.containsKey check, {A, A, B} {A, B, B} was killed at tmpMap.get(substr) > map.get(substr) check
            //only exactly match can finish the loop
            for (j = 0; j < n; j++) {
                String substr = s.substring(i+j*len, i+(j+1)*len);
                if(!map.containsKey(substr))
                    break;
                tmpMap.put(substr, tmpMap.getOrDefault(substr, 0) + 1);
                if(tmpMap.get(substr) > map.get(substr))
                    break;
            }
            if(j == n)
                result.add(i);
        }

        return result;
    }
}
