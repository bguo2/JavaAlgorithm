package com.example.console.recursion;

import java.util.Arrays;

//A frog is crossing a river. The river is divided into x units and at each unit there may or may not exist a stone.
// The frog can jump on a stone, but it must not jump into the water.
//
//Given a list of stones' positions (in units) in sorted ascending order, determine if the frog is able to cross the river
// by landing on the last stone. Initially, the frog is on the first stone and assume the first jump must be 1 unit.
//
//If the frog's last jump was k units, then its next jump must be either k - 1, k, or k + 1 units. Note that the frog can only
// jump in the forward direction.
//
//Note:
//
//The number of stones is ≥ 2 and is < 1,100.
//Each stone's position will be a non-negative integer < 231.
//The first stone's position is always 0.
//Example 1:
//
//[0,1,3,5,6,8,12,17]
//
//There are a total of 8 stones.
//The first stone at the 0th unit, second stone at the 1st unit,
//third stone at the 3rd unit, and so on...
//The last stone at the 17th unit.
//
//Return true. The frog can jump to the last stone by jumping
//1 unit to the 2nd stone, then 2 units to the 3rd stone, then
//2 units to the 4th stone, then 3 units to the 6th stone,
//4 units to the 7th stone, and 5 units to the 8th stone.
//Example 2:
//
//[0,1,2,3,4,8,9,11]
//
//Return false. There is no way to jump to the last stone as
//the gap between the 5th and 6th stone is too large.
public class FrogJump {

    //brute force
    public boolean canCross(int[] stones) {
        if(stones == null || stones.length < 2)
            return false;
        if(stones[1] != 1)
            return false;
        //brute force
        //return canCross(stones, 1, 1);

        int[][] memo = new int[stones.length][stones.length];
        Arrays.fill(memo, -1);
        return canCross(stones, 1, 1, memo) == 1;
    }

    //O(2^^n)
    private boolean canCross(int[] stones, int start, int lastStep) {
        for(int i = start+1; i < stones.length; i++) {
            int step = stones[i] - stones[start];
            if(step >= lastStep-1 && step <= lastStep + 1) {
                if(canCross(stones, i, step))
                    return true;
            }
        }
        return start == stones.length - 1;
    }

    //memorize, O(n^^2)
    private int canCross(int[] stones, int start, int lastStep, int[][] memo) {
        if(memo[start][lastStep] >= 0)
            return memo[start][lastStep];
        for(int i = start+1; i < stones.length; i++) {
            int step = stones[i] - stones[start];
            if(step >= lastStep-1 && step <= lastStep + 1) {
                if(canCross(stones, i, step)) {
                    memo[start][step] = 1;
                    return 1;
                }
            }
        }

        memo[start][lastStep] = (start == stones.length - 1 ? 1:0);
        return memo[start][lastStep];
    }
}
