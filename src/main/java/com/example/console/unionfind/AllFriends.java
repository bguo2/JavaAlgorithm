package com.example.console;
//The Earliest Moment When Everyone Become Friends
//There are n people in a social group labeled from 0 to n - 1. You are given an array logs where logs[i] = [timestampi, xi, yi] indicates that xi and yi will be friends at the time timestampi.
//
//Friendship is symmetric. That means if a is friends with b, then b is friends with a. Also, person a is acquainted with a person b if a is friends with b, or a is a friend of someone acquainted with b.
//
//Return the earliest time for which every person became acquainted with every other person. If there is no such earliest time, return -1.
//
//
//
//Example 1:
//
//Input: logs = [[20190101,0,1],[20190104,3,4],[20190107,2,3],[20190211,1,5],[20190224,2,4],[20190301,0,3],[20190312,1,2],[20190322,4,5]], n = 6
//Output: 20190301
//Explanation:
//The first event occurs at timestamp = 20190101 and after 0 and 1 become friends we have the following friendship groups [0,1], [2], [3], [4], [5].
//The second event occurs at timestamp = 20190104 and after 3 and 4 become friends we have the following friendship groups [0,1], [2], [3,4], [5].
//The third event occurs at timestamp = 20190107 and after 2 and 3 become friends we have the following friendship groups [0,1], [2,3,4], [5].
//The fourth event occurs at timestamp = 20190211 and after 1 and 5 become friends we have the following friendship groups [0,1,5], [2,3,4].
//The fifth event occurs at timestamp = 20190224 and as 2 and 4 are already friends anything happens.
//The sixth event occurs at timestamp = 20190301 and after 0 and 3 become friends we have that all become friends.
//Example 2:
//
//Input: logs = [[0,2,0],[1,0,1],[3,0,3],[4,1,2],[7,3,1]], n = 4
//Output: 3
//
//
//Constraints:
//
//2 <= n <= 100
//1 <= logs.length <= 104
//logs[i].length == 3
//0 <= timestampi <= 109
//0 <= xi, yi <= n - 1
//xi != yi
//All the values timestamp are unique.
//All the pairs (xi, yi) occur at most one time in the input.
//This problem deals with the relationship or membership of entities. For those of you who are familiar with a data
// structure called Union-Find, this problem might ring a bell. In fact, it is a perfect example that demonstrates the
// advantages of Union-Find data structure (also known as Disjoint-Set).
//
//Union-Find (a.k.a Disjoint-Set) is a data structure that keeps track of the connectivities among interconnected
// individuals efficiently. With Union-Find, one can quickly determine which group a specific individual belongs to.
// In addition, one can quickly merge two individuals together along with the two groups that they belong to.
//
//As suggested by its name, a typical Union-Find data structure usually provides two interfaces as follows:
//
//find(a): this function returns the group that the individual a belongs to.
//
//union(a, b): this function merges the two groups that the individuals a and b belong to respectively, if the groups are not of
// the same group already.

import java.util.Arrays;
import java.util.Comparator;

public class AllFriends {
        public int earliestAcq(int[][] logs, int n) {

            // First, we need to sort the events in chronological order.
            Arrays.sort(logs, (log1, log2) -> {
                    Integer tsp1 = log1[0];
                    Integer tsp2 = log2[0];
                    return tsp1.compareTo(tsp2);
                });

            // Initially, we treat each individual as a separate group.
            int groupCount = n;
            UnionFind uf = new UnionFind(n);

            for (int[] log : logs) {
                int timestamp = log[0], friendA = log[1], friendB = log[2];

                // We merge the groups along the way.
                if (uf.union(friendA, friendB)) {
                    groupCount -= 1;
                }

                // The moment when all individuals are connected to each other.
                if (groupCount == 1) {
                    return timestamp;
                }
            }

            // There are still more than one groups left,
            //  i.e. not everyone is connected.
            return -1;
        }
    }

    class UnionFind {
        private int[] group;
        private int[] rank;

        public UnionFind(int size) {
            this.group = new int[size];
            this.rank = new int[size];
            for (int person = 0; person < size; ++person) {
                this.group[person] = person;
                this.rank[person] = 0;
            }
        }

        /** Return the id of group that the person belongs to. */
        public int find(int person) {
            if (this.group[person] != person)
                this.group[person] = this.find(this.group[person]);
            return this.group[person];
        }

        /**
         * If it is necessary to merge the two groups that x, y belong to.
         * @return true: if the groups are merged.
         */
        public boolean union(int a, int b) {
            int groupA = this.find(a);
            int groupB = this.find(b);
            boolean isMerged = false;

            // The two people share the same group.
            if (groupA == groupB)
                return isMerged;

            // Otherwise, merge the two groups.
            isMerged = true;
            // Merge the lower-rank group into the higher-rank group.
            if (this.rank[groupA] > this.rank[groupB]) {
                this.group[groupB] = groupA;
            } else if (this.rank[groupA] < this.rank[groupB]) {
                this.group[groupA] = groupB;
            } else {
                this.group[groupA] = groupB;
                this.rank[groupB] += 1;
            }

            return isMerged;
        }
}
