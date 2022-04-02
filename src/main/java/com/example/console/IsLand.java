package com.example.console;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//A 2d grid map of m rows and n columns is initially filled with water. We may perform an  addLand  operation
// which turns the water at position (row, col) into a land. Given a list of positions to operate, count the number of islands
// after each  addLand  operation. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically.
// You may assume all four edges of the grid are all surrounded by water.
//
//Example:
//
//Input: m = 3, n = 3, positions = [[0,0], [0,1], [1,2], [2,1]]
//Output: [1,1,2,3]
//Explanation:
//
//Initially, the 2d grid grid is filled with water. (Assume 0 represents water and 1 represents land).
//
//0 0 0
//0 0 0
//0 0 0
//Operation 1: addLand(0, 0) turns the water at grid[0][0] into a land.
//
//1 0 0
//0 0 0   Number of islands = 1
//0 0 0
//Operation 2: addLand(0, 1) turns the water at grid[0][1] into a land.
//
//1 1 0
//0 0 0   Number of islands = 1
//0 0 0
//Operation 3: addLand(1, 2) turns the water at grid[1][2] into a land.
//
//1 1 0
//0 0 1   Number of islands = 2
//0 0 0
//Operation 4: addLand(2, 1) turns the water at grid[2][1] into a land.
//
//1 1 0
//0 0 1   Number of islands = 3
//0 1 0
//Follow up:
//
//Can you do it in time complexity O(k log mn), where k is the length of the positions?
//为了解决这种陆地之间会合并的情况，最好能够将每个陆地都标记出其属于哪个岛屿，这样就会方便统计岛屿个数。这种群组类问题，很适合使用联合查找 Union Find 来做，
// 又叫并查集 Disjoint Set，LeetCode 中使用这种解法的题目还不少呢，比如 Friend Circles，Graph Valid Tree，Redundant Connection II 等等。
// 一般来说，UF 算法的思路是每个个体先初始化为不同的群组，然后遍历有关联的两个个体，如果发现其 getRoot 函数的返回值不同，则手动将二者加入一个群组，
// 然后总群组数自减1。这里就要分别说一下 root 数组，和 getRoot 函数。两个同群组的个体，通过 getRoot 函数一定会返回相同的值，但是其在 root
// 数组中的值不一定相同，可以类比成 getRoot 函数返回的是祖先，如果两个人的祖先相同，那么其是属于一个家族的（这里不是指人类共同的祖先哈）。
// root 可以用数组或者 HashMap 来表示，如果个体是数字的话，那么数组就 OK，如果个体是字符串的话，可能就需要用 HashMap 了。root 数组的初始化可以有两种，
// 可以均初始化为 -1，或者都初始化为不同的数字，博主一般喜欢初始化为不同的数字。getRoot 函数的写法也可用递归或者迭代的方式，可参见博主之前的帖子
// Redundant Connection II 中的讨论部分。这么一说感觉 UF 算法的东西还蛮多的，啥时候博主写个 UF 总结贴吧。
//
//那么具体来看这道题吧，此题跟经典的 UF 使用场景有一点点的区别，因为一般的场景中两个个体之间只有两种关系，属于一个群组或者不属于同一个群组，而这道题里面由于
// water 的存在，就多了一种情况，只需要事先检测一下当前位置是不是岛屿就行了，总之问题不大。一般来说 root 数组都是使用一维数组，方便一些，
// 那么这里就可以将二维数组 encode 为一维的，于是需要一个长度为 m*n 的一维数组来标记各个位置属于哪个岛屿，假设每个位置都是一个单独岛屿，岛屿编号可以用其
// 坐标位置表示，但是初始化时将其都赋为 -1，这样方便知道哪些位置尚未变成岛屿。然后开始遍历陆地数组，若某个岛屿位置编码的 root 值不为 -1，说明这是一个重复
// 出现的位置，不需要重新计算了，直接将 cnt 加入结果 res 中。否则将其岛屿编号设置为其坐标位置，然后岛屿计数加1，此时开始遍历其上下左右的位置，遇到越界或者
// 岛屿标号为 -1 的情况直接跳过，现在知道初始化为 -1 的好处了吧，遇到是 water 的地方直接跳过。否则用 getRoot 来查找邻居位置的岛屿编号，同时也用 getRoot
// 来查找当前点的编号，这一步就是经典的 UF 算法的操作了，因为当前这两个 land 是相邻的，它们是属于一个岛屿，所以其 getRoot 函数的返回值 suppose
// 应该是相等的，但是如果返回值不同，说明需要合并岛屿，将两个返回值建立关联，并将岛屿计数 cnt 减1。当遍历完当前点的所有邻居时，该合并的都合并完了，将此时的
// 岛屿计数 cnt 存入结果中
public class IsLand {
    //O(k*lg(m*n)
    public static List<Integer> numberOfIsland(int m, int n, int[][] positions) {
        List<Integer> result = new ArrayList<>();
        int count = 0;
        int[] root = new int[m*n];
        int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1, 0};
        Arrays.fill(root, -1);
        for(int i = 0; i < positions.length; i++) {
            int id = positions[i][0] * n + positions[i][1];
            //this position was calculated before
            if(root[id] != -1) {
                result.add(count);
                continue;
            }

            root[id] = id;
            count++;
            for(int j = 0; j < dx.length; j++) {
                int x = positions[i][0] + dx[j];
                int y = positions[i][1] + dy[j];
                int curId = n*x + y;
                if(x < 0 || x >= m || y < 0 || y >=n || root[curId] == -1)
                    continue;
                int p = getRoot(curId, root), q = getRoot(id, root);
                //id and curId are connected, their parent should be equal, if it is not equal, then we should merge
                if(p != q) {
                    root[p] = q;
                    count--;
                }
            }

            result.add(count);
        }

        return result;
    }

    private static int getRoot(int id, int[] root) {
        if(id == root[id])
            return id;
        return getRoot(root[id], root);
    }
}
