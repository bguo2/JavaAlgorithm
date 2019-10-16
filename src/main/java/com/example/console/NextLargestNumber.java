package com.example.console;
//Find next greater number with same set of digits
//Given a number n, find the smallest number that has same set of digits as n and is greater than n. If n is the greatest possible number with its set of digits, then print “not possible”.
//Examples:
//For simplicity of implementation, we have considered input number as a string.
//
//Input:  n = "218765"
//Output: "251678"
//
//Input:  n = "1234"
//Output: "1243"
//
//Input: n = "4321"
//Output: "Not Possible"
//
//Input: n = "534976"
//Output: "536479"
//
//input: n = "534974"
//output: "537449"

//idea:  Start from the right most digit and find the first digit that is smaller than the digit next to it.
//534974 => 4th and 3th from right side: 49
//Find the smallest digit on right side of (i-1)'th digit that is greater than number[i-1]
// 7 is the smallest than 4
//swap 4 and 7 => 537 944 , then sort 944 to 449 => result is 537449
//218765 => swap 1 anf 5 => 25 8761 => sort 8761 => merge 25 => 251678
//534976 => swap 4 and 6 => 536 974 => sort 974 => merge 536 => 536479
//534974 => find smallest from right side greater than 4 which is 7 => swap 4 and 7 => 537 944 => sort 944 => merge 537 => 537449
public class NextLargestNumber {
}
