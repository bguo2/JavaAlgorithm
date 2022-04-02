package com.example.console;
//Count of total anagram substrings
//Given a string of lower alphabet characters, count total substring of this string which are anagram to each other.
//
//Examples:
//
//Input  : str = “xyyx”
//Output : 4
//Total substrings of this string which
//are anagram to each other are 4 which
//can be enumerated as,
//{“x”, “x”}, {"y", "y"}, {“xy”, “yx”}, {“xyy”, “yyx”}
//Input  : str = "geeg"
//Output : 4

import java.util.*;

//abba
//{a,a},{ab,ba}, {b,b}, {abb,bba}
//output 4
public class AnagramCountInString {
    public static int sherlockAndAnagrams(String s) {
        Map<String, Integer> map = new HashMap<>();
        for(int i = 0; i < s.length(); i++) {
            for(int j = i; j < s.length(); j++) {
                char[] tmp = s.substring(i, j+1).toCharArray();
                //Arrays.sort(tmp);
                //String key= new String(tmp);
                String key = getAnagram(tmp);
                map.put(key, map.getOrDefault(key, 0)+1);
            }
        }

        int count = 0;
        //return pairs count: select 2 of the count number = n*(n-1)/2.
        for(String key: map.keySet()) {
            int n = map.get(key);
            count += n*(n-1)/2;
        }
        return count;
    }

    private static String getAnagram(char[] input) {
        char[] tmp = new char[26];
        for(int i = 0; i < input.length; i++) {
            tmp[input[i] - 'a']++;
        }
        return new String(tmp);
    }

    //Given two strings,  and , that may or may not be of the same length, determine the minimum number of character deletions
    // required to make  and  anagrams. Any characters can be deleted from either of the strings.
    // for Anangram, the position doesn't matter: so the 2 length - commons*2
    public static int makeAnagram(String a, String b) {
        Map<Character, Integer> map1 = new HashMap<>();
        Map<Character, Integer> map2 = new HashMap<>();
        for(int i = 0; i < a.length(); i++) {
            char ch = a.charAt(i);
            map1.put(ch, map1.getOrDefault(ch, 0)+1);
        }
        for(int i = 0; i < b.length(); i++) {
            char ch = b.charAt(i);
            map2.put(ch, map2.getOrDefault(ch, 0)+1);
        }
        int shared = 0;
        for(Character c: map1.keySet()) {
            if(map2.containsKey(c)) {
                int n1 = map1.get(c);
                int n2 = map2.get(c);
                shared += Math.min(n1, n2);
            }
        }

        return a.length() + b.length() - shared*2;
    }

    //print all anagrams .e.g abc => abc, acb, bac, bca, cba, cab
    public static List<String> getAllAnagrams(String input) {
        int len = input.length();
        List<String> result = new ArrayList<>();
        getAnangrams(input, 0, len, result);
        return result;
    }

    private static void getAnangrams(String input, int start, int end, List<String> result) {
        if(start == end - 1) {
            result.add(input);
            return;
        }

        for(int i = start; i < end; i++) {
            input = swap(input, start, i);
            getAnangrams(input, start+1, end, result);
            input = swap(input, start, i);
        }
    }

    private static String swap(String s, int i, int j) {
        char[] chars =  s.toCharArray();
        char t = chars[i];
        chars[i] = chars[j];
        chars[j] = t;
        return new String(chars);
    }
}
