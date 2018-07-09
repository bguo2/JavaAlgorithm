package com.example.console;

public class StringPattern {

    //* --> Matches with 0 or more instances of any character or set of characters.
    //? --> Matches with any one character.
    //For example, “g*ks” matches with “geeks” match. And string “ge?ks*” matches with “geeksforgeeks”
    //(note ‘*’ at the end of first string). But “g*k” doesn’t match with “gee” as character ‘k’ is not present in second string
    public static boolean isMatch(String pattern, String source){
        // match
        if((source == null && pattern == null) || (pattern.length() == 0 && source.length() == 0))
            return true;
        //pattern ends but source has more
        if(pattern.length() == 0 && source.length() > 0)
            return false;
        //source ends, but pattern has more
        if(source.length() == 0 && pattern.length() > 0) {
            //if pattern is * and no other more
            if(pattern.charAt(0) == '*' && pattern.length() == 1) {
                    return true;
            }
            return false;
        }

        //patten is ? or first char matches, continues next char
        if(pattern.charAt(0) == '?' || pattern.charAt(0) == source.charAt(0))
            return isMatch(pattern.substring(1), source.substring(1));

        //If there is *, then there are two possibilities
        // a) We consider current character of second string
        // b) We ignore current character of second string.
        if(pattern.charAt(0) == '*'){
            return isMatch(pattern.substring(1), source) || isMatch(pattern, source.substring(1));
        }

        return false;
    }
}
