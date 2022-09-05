package com.example.console.DynamicPg;

import java.util.Arrays;

//you are given coins of different denominations and a total amount of money amount. Write a function to compute
// the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any
// combination of the coins, return -1.
//
//You may assume that you have an infinite number of each kind of coin.
//
//
//
//Example 1:
//
//Input: coins = [1,2,5], amount = 11
//Output: 3
//Explanation: 11 = 5 + 5 + 1
//Example 2:
//
//Input: coins = [2], amount = 3
//Output: -1
//Example 3:
//
//Input: coins = [1], amount = 0
//Output: 0
//Example 4:
//
//Input: coins = [1], amount = 1
//Output: 1
//Example 5:
//
//Input: coins = [1], amount = 2
//Output: 2
//
//
//Constraints:
//
//1 <= coins.length <= 12
//1 <= coins[i] <= 231 - 1
//0 <= amount <= 104
public class CoinChange {
    public static int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount+1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for(int i = 1; i <= amount; i++) {
            for(int coin: coins) {
                if(coin == i)
                    dp[i] = 1;
                else if(i > coin) {
                    if(dp[i-coin] == Integer.MAX_VALUE)
                        continue;
                    dp[i] = Math.min(dp[i-coin]+1, dp[i]);
                }
            }
        }

        return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
    }

    //Given a value N, if we want to make change for N cents, and we have infinite supply of each of S = { S1, S2, .. , Sm} valued coins,
    // how many ways can we make the change? The order of coins doesn’t matter.
    //For example, for N = 4 and S = {1,2,3}, there are four solutions: {1,1,1,1},{1,1,2},{2,2},{1,3}. So output should be 4.
    // For N = 10 and S = {2, 5, 3, 6}, there are five solutions: {2,2,2,2,2}, {2,2,3,3}, {2,2,6}, {2,3,5} and {5,5}. So the output should be 5.
    public static Long countWaysOfCoinChange(Long[] coins, int amount) {
        long[] dp = new long[amount+1];
        dp[0] = 1L;
        for(int i = 0; i < coins.length; i++) {
            for(int j = coins[i].intValue(); j <= amount; j++) {
                dp[j] += dp[j - coins[i].intValue()];
            }
        }
        return dp[amount];
    }
}
