package com.example.console;
//There are a row of n houses, each house can be painted with one of the k colors. The cost of painting each house with a
// certain color is different. You have to paint all the houses such that no two adjacent houses have the same color.
//
//The cost of painting each house with a certain color is represented by a n x k cost matrix. For example,
// costs[0][0] is the cost of painting house 0 with color 0; costs[1][2] is the cost of painting house 1 with color 2,
// and so on... Find the minimum cost to paint all houses.
//
//Note:
//All costs are positive integers.
//
//Example:
//
//Input: [[1,5,3],[2,9,4]]
//Output: 5
//Explanation: Paint house 0 into color 0, paint house 1 into color 2. Minimum cost: 1 + 4 = 5;
//             Or paint house 0 into color 2, paint house 1 into color 0. Minimum cost: 3 + 2 = 5.
public class PaintHouseII {
    public int minCostII(int[][] costs) {
        if(costs == null || costs.length == 0)
            return 0;

        int preMinIndex = -1;
        int preMin = 0;
        int preSecond = 0;

        for(int i  = 0; i < costs.length; i++) {
            int curMinIndex = 0;
            int curMin = Integer.MAX_VALUE;
            int curSecond = Integer.MAX_VALUE;
            for(int j = 0; j < costs[0].length; j++) {
                if(j == preMinIndex) {
                    costs[i][j] += preSecond;
                }
                else {
                    costs[i][j] += preMin;
                }

                if(curMin > costs[i][j]) {
                    curSecond = curMin;
                    curMin = costs[i][j];
                    curMinIndex = j;
                }
                else if(curSecond > costs[i][j]) {
                    curSecond = costs[i][j];
                }
            }

            preMin = curMin;
            preMinIndex = curMinIndex;
            preSecond = curSecond;
        }

        int result = Integer.MAX_VALUE;
        for(int j = 0; j < costs[0].length; j++) {
            if(result > costs[costs.length - 1][j])
                result = costs[costs.length - 1][j];
        }

        return result;
    }
}
