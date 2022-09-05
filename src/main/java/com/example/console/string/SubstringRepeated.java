package com.example.console.string;

//check if a string is constructed by repeating its substring
//Input 1: str = “abcabcabc”
//Output: True
//Explanation: “abcabcabc” can be formed by repeatedly appending “abc” to an empty string.
//Input 2: str = “xyxy”
//Output: True
//Explanation: “xyxy” can be formed by repeatedly appending “xy” to an empty string.
//Input 3: str = “xyzxy”
//Output: False
//Explanation: str has no such substring that can be repeatedly appended to an empty string to form str.
//Input 4: str = “Tutorialcup”
//Output: False
public class SubstringRepeated {
    //1. If the original string has a repeating substring, the repeating substring can be no larger than 1/2 the length of
    // the original string. Ex: “abcabc” would be “abc”.
    //By doubling the input string and removing the first and last character, i.e. “abcabcabcabc” => “bcabcabcab”,
    // if the original string “abcabc” can be found in “bcabcabcab”, it means that “abcabc” is made up by repeating one of
    // its a substring.

    static boolean hasRepeatedSubstring(String str)
    {
        int n = str.length();
        String pattern = str.substring(1) + str.substring(0, n-1);
        return pattern.contains(str);
    }

    //2. dp
    static boolean hasRepeatedSubStr(String str)
    {
        int i = 1, j = 0, n = str.length();

        /* dp[i+1] stores longest proper prefix
        upto index i which also a suffix */
        int[] dp = new int[n+1];

        /* Traverse the string */
        while(i < n)
        {
            /* if the current character (at index = i) is same as the last
            character of longest common prefix obtained up to index i-1 */
            if(str.charAt(i) == str.charAt(j))
                dp[++i] = ++j;

                /* if characters don't match */
            else
            {
                /* when str[0] and str[i] don't match no proper
                prefix which is also a suffix is possible
                so length is 0, simply move on to next character*/
                if(j == 0)
                    i++;

                /* decrease the length (by 1) of longest proper
                prefix (also suffix) possible upto index i-1
                and then match the last character of longest
                proper prefix to character at current index */
                else
                    j = dp[j];
            }
        }

        /* check if there is any such prefix of
        input string that has length that
        completely divides the input string length */
        return dp[n] != 0 && (dp[n]%(n-dp[n]) == 0);
    }
}
