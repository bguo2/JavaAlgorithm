package com.example.console;

//ou have n gardens, labeled from 1 to n, and an array paths where paths[i] = [xi, yi] describes a bidirectional path between
// garden xi to garden yi. In each garden, you want to plant one of 4 types of flowers.
//
//All gardens have at most 3 paths coming into or leaving it.
//
//Your task is to choose a flower type for each garden such that, for any two gardens connected by a path, they have different
// types of flowers.
//
//Return any such a choice as an array answer, where answer[i] is the type of flower planted in the (i+1)th garden.
// The flower types are denoted 1, 2, 3, or 4. It is guaranteed an answer exists.
//
//
//
//Example 1:
//
//Input: n = 3, paths = [[1,2],[2,3],[3,1]]
//Output: [1,2,3]
//Explanation:
//Gardens 1 and 2 have different types.
//Gardens 2 and 3 have different types.
//Gardens 3 and 1 have different types.
//Hence, [1,2,3] is a valid answer. Other valid answers include [1,2,4], [1,4,2], and [3,2,1].
//Example 2:
//
//Input: n = 4, paths = [[1,2],[3,4]]
//Output: [1,2,1,2]
//Example 3:
//
//Input: n = 4, paths = [[1,2],[2,3],[3,4],[4,1],[1,3],[2,4]]
//Output: [1,2,3,4]
//
//
//Constraints:
//
//1 <= n <= 104
//0 <= paths.length <= 2 * 104
//paths[i].length == 2
//1 <= xi, yi <= n
//xi != yi
//Every garden has at most 3 paths coming into or leaving it.

import java.util.ArrayList;
import java.util.List;

//这道题说是有n个花园，标号分别是1到n，现在有个二维数组 paths，标记了哪些花园是相连通的，限定了每个花园最多只能连通三个其他的花园。
// 现在要给每个花园选择一种颜色的花来种，可供选择的颜色只有四种，编号1到4，要求相连的花园不能种相同颜色的花，现在让返回一种选择花颜色的方式。
// 题目说了一定有合理的解，这是为啥呢？因为限定了每个花园最多只能连通其他三个花园，而总共可有四种颜色可以选择，最坏情况就是相连的三个花园各自的颜色都不同，
// 但总还是有一种颜色可以供当前的花园选择。
public class FlowerPlantingWithNoAdjacent {
    public int[] gardenNoAdj(int N, int[][] paths) {
        int[] ans = new int[N];
        List<List<Integer>>graph = new ArrayList<>();
        for(int i=0;i<N;i++) {
            graph.add(new ArrayList<>());
        }

        for(int[] path: paths) {
            graph.get(path[0]-1).add(path[1]-1);
            graph.get(path[1]-1).add(path[0]-1);
        }

        for(int i=0; i<N; i++) {
            boolean[] color = new boolean[5];
            List<Integer> nbrs = graph.get(i);
            for(int nbr: nbrs) {
                color[ans[nbr]] = true;
            }

            for(int j=1; j<=4; j++) {
                if(color[j] == false) {
                    ans[i] = j;
                    break;
                }
            }
        }
        return ans;
    }
}
