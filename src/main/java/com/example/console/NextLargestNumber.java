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

//idea:  1) Start from the right most digit and find the first digit that is <= the last digit.
// 2) if the digit is smaller than the last digit, then swap them, if number is not smallerst,
// sort the rest of digits starting from it.
// 3) if the digit is equal to the last digit, find the second digit greater than the last digit and repeat the 2)


//218765 => swap 1 anf 5 => 25 8761 => sort 8761 => merge 25 => 251678
//534976 => swap 4 and 6 => 536 974 => sort 974 => merge 536 => 536479
//534974 => 4= 4: find smallest from right side greater than 4 which is 7 => swap 4 and 7 => 537 944 => sort 944 => merge 537 => 537449
//1234 => swap 4 and 3 => 1243, no others
//53444 => swap 3 and 4, 54 344, 344 is smallest, sort 344, it is still 344 => 54344
//53464 => 4=4, use 6 to find it, it is 4, swap it: 536 44 => 536 44

public class NextLargestNumber {
}
