package com.example.console;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

//There are N workers.  The i-th worker has a quality[i] and a minimum wage expectation wage[i].
//
//Now we want to hire exactly K workers to form a paid group.  When hiring a group of K workers, we must pay them
// according to the following rules:
//
//Every worker in the paid group should be paid in the ratio of their quality compared to other workers in the paid group.
//Every worker in the paid group must be paid at least their minimum wage expectation.
//Return the least amount of money needed to form a paid group satisfying the above conditions.
//
//
//
//Example 1:
//
//Input: quality = [10,20,5], wage = [70,50,30], K = 2
//Output: 105.00000
//Explanation: We pay 70 to 0-th worker and 35 to 2-th worker.
//Example 2:
//
//Input: quality = [3,1,10,10,1], wage = [4,8,2,2,7], K = 3
//Output: 30.66667
//Explanation: We pay 4 to 0-th worker, 13.33333 to 2-th and 3-th workers seperately.
public class MinHiringCost {
    //Suppose we have a team of two workers (w1, q1), (w2, q2). What we need to pay is max(w1, q1*w2/q2) + max(w2, q2*w1/q1).
    // And w1 = q1*w1/q1, w2 = q2*w2/q2. So the cost is q1*max(w1/q1,w2/q2) + q2*max(w1/q1,w2/q2) = max(w1/q1,w2/q2) * (q1+q2)
    //So generally, a team cost is ∑wi = w/q * ∑qi where w/q is the maximum wage/quality ratio in that team .
    //Thus, to find minimal wage to pay for a k-workers team, we need to minimize w/q and ∑qi.
    //
    //So we can iterate a sorted team with the order of w/q, and keep retain k workers who have the lowest qualities.
    // (what a sarcasm that worker with high quality raises wage bar so he/she can't be hired...).
    //We can use a maximum heap to retain k lowest qualities. Once team size reaches K, we update our cost as
    // min(cost, w/q * ∑qi). Once team size exceed K, we pop the highest quality and remove that quality from ∑qi.
    // I used two variable ratio and sumq for w/q and ∑qi.

    private class Worker {
        double ratio;
        int wage;
        int quality;

        public Worker(int w, int q) {
            this.ratio = w*1.0/q;
            this.quality = q;
            this.wage = w;
        }
    }

    public double getMinCost(int w[], int q[], int k)
    {
        //special conditions
        if(k < 1)
            return 0;

        Worker[] workers = new Worker[w.length];
        for(int i = 0; i < w.length; i++) {
            workers[i] = new Worker(w[i], q[i]);
        }

        //sort workers with rate
        Arrays.sort(workers, (w1, w2) -> {
            if(w1.ratio == w2.ratio)
                return w2.quality - w1.quality;
            return w1.ratio > w2.ratio ? 1:-1;
        });

        PriorityQueue<Worker> maxQualityq = new PriorityQueue<>(new Comparator<Worker>() {
            @Override
            public int compare(Worker o1, Worker o2) {
                return o2.quality - o1.quality;
            }
        });

        int sumq = 0;
        double cost = Double.MAX_VALUE;
        for (Worker worker: workers) {
            maxQualityq.offer(worker);
            sumq += worker.quality;
            // reached k: remove the highest quality
            if(maxQualityq.size() > k) {
                sumq -= maxQualityq.poll().quality;
            }
            if(maxQualityq.size() == k)
                cost = Math.min(cost, sumq*worker.ratio);
        }

        return cost;
    }
}
