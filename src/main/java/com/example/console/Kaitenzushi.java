package com.example.console;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/*
Question:
There are N dishes in a row on a kaiten belt, with the ith dish being of type D_i. Some dishes may be of the same type as one another.
You're very hungry, but you'd also like to keep things interesting. The N dishes will arrive in front of you, one after another
in order, and for each one you'll eat it as long as it isn't the same type as any of the previous K dishes you've eaten.
You eat very fast, so you can consume a dish before the next one gets to you. Any dishes you choose not to eat as they pass
will be eaten by others. Determine how many dishes you'll end up eating.

Test Case 1:
N = 6
D = [1, 2, 3, 3, 2, 1]
K = 1
Expected Return Value = 5

Test Case 2:
N = 6
D = [1, 2, 3, 3, 2, 1]
K = 2
Expected Return Value = 4

Test Case 3:
N = 7
D = [1, 2, 1, 2, 1, 2, 1]
K = 2
Expected Return Value = 2

Sample Explanation
In the first case, the dishes have types of [1, 2, 3, 3, 2, 1], so you'll eat the first 3 dishes, skip the next one as it's
another type-3 dish, and then eat the last 2.

In the second case, you won't eat a dish if it has the same type as either of the previous 2 dishes you've eaten.
After eating the first, second, and third dishes, you'll skip the fourth and fifth dishes as they're the same type as the
last 2 dishes that you've eaten. You'll then eat the last dish, consuming 4 dishes total.

In the third case, once you eat the first two dishes you won't eat any of the remaining dishes.
 */
public class Kaitenzushi {

    public static int getMaximumEatenDishCount(int N, int[] D, int K) {
        int count = 0;
        Set<Integer> lastEaten = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        for(int i = 0; i < N; i++) {
            if(lastEaten.contains(D[i]))
                continue;
            lastEaten.add(D[i]);
            count++;
            queue.offer(D[i]);
            if(queue.size() > K) {
                lastEaten.remove(queue.poll());
            }
        }

        return count;
    }
}
