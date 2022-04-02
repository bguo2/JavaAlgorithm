package com.example.console;

import java.util.Collections;
import java.util.PriorityQueue;

//There are given n ropes of different lengths, we need to connect these ropes into one rope.
// The cost to connect two ropes is equal to sum of their lengths. We need to connect the ropes with minimum cost.
//
//For example if we are given 4 ropes of lengths 4, 3, 2 and 6. We can connect the ropes in following ways.
//1) First connect ropes of lengths 2 and 3. Now we have three ropes of lengths 4, 6 and 5.
//2) Now connect ropes of lengths 4 and 5. Now we have two ropes of lengths 6 and 9.
//3) Finally connect the two ropes and all ropes have connected.
public class MinCostToMerge {

    public static int getMinCost(int[] input)
    {
        if(input == null || input.length == 0)
            return 0;
        int sum = 0;
        PriorityQueue<Integer> minQueue = new PriorityQueue<>();
        for(int i: input)
        {
            minQueue.offer(i);
        }

        while (!minQueue.isEmpty())
        {
            int value = minQueue.poll();
            sum += value;
            if(!minQueue.isEmpty())
            {
                int value1 = minQueue.poll();
                sum += value1;
                value += value1;
            }

            if(minQueue.isEmpty())
                return sum;
            minQueue.offer(value);
        }

        return sum;
    }
}
