package com.example.console.matrix;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

//We are given a 2-dimensional grid. "." is an empty cell, "#" is a wall, "@" is the starting point, ("a", "b", ...) are keys,
// and ("A", "B", ...) are locks.
//
//We start at the starting point, and one move consists of walking one space in one of the 4 cardinal directions.
// We cannot walk outside the grid, or walk into a wall.  If we walk over a key, we pick it up.  We can't walk over a lock
// unless we have the corresponding key.
//
//For some 1 <= K <= 6, there is exactly one lowercase and one uppercase letter of the first Kletters of the English alphabet
// in the grid.  This means that there is exactly one key for each lock, and one lock for each key; and also that the letters used
// to represent the keys and locks were chosen in the same order as the English alphabet.
//
//Return the lowest number of moves to acquire all keys.  If it's impossible, return -1.
//
//Example 1:
//
//Input: ["@.a.#","###.#","b.A.B"]
//Output: 8
//Example 2:
//
//Input: ["@..aA","..B#.","....b"]
//Output: 6
//Note:
//
//1 <= grid.length <= 30
//1 <= grid[0].length <= 30
//grid[i][j] contains only '.', '#', '@', 'a'-``'f``' and 'A'-'F'
//The number of keys is in [1, 6].  Each key has a different letter and opens exactly one lock.
//这道题好就好在对钥匙进行了一些限定，最多有6把，最少有1把，而且都是按字母顺序出现的，就是说只有一把钥匙的时候，一定是a，两把的话一定是a和b。
// 我们需要保存所有当前已经获得的钥匙，并且还要随时查询是否已经获得了某个特定的钥匙，还需要查询是否已经获得了所有的钥匙。由于之前说了知道了钥匙的个数，
// 就能确定是哪些钥匙，这样就可以对钥匙进行编号，钥匙a编为0，同理，b，c，d，e，f 分别编为 1，2，3，4，5。最简单的实现就是用一个长度为k的 boolean 数组，
// 获得了某个钥匙就标记为 true，查询某个钥匙是否存在就直接在数组中对应位置查询即可，判断是否获得所有钥匙就线性遍历一下数组即可，由于最多就6把钥匙，所以遍历也很快。
// 当然，若我们想秀一波技巧，也可以将钥匙编码成二进制数，对应位上的0和1表示该钥匙是存在，比如二进制数 111111 就表示六把钥匙都有了，而 100111
// 就表示有钥匙 a，d，e 和f，这样查询某个钥匙或查询所有钥匙的时间复杂度都是常数级了，既省了空间又省了时间，岂不美哉？！
//整体框架还是经典的 BFS 写法，再稍加改动即可。这里的队列 queue 不能只放位置信息，还需要放当前的钥匙信息，因为到达不同的位置获得的钥匙个数可能是不同的，
// 二维的位置信息编码成一个整数，再加上钥匙的整数，组成一个 pair 对儿放到队列中。由于参数中没有事先告诉我们起点的位置，所以需要先遍历一遍整个迷宫，找到@符号，
// 将位置和当前钥匙信息加入到 queue 中。为了避免死循环，BFS 遍历是需要记录已经访问过的位置的，这里的状态当然也要加入当前钥匙的信息，为了简单起见，
// 将其编码成一个字符串，前半部分放位置编码成的整数，中间加个下划线，后面放钥匙信息的整数，组成的字符串放到 HashSet 中即可。遍历的过程中同时还要统计钥匙的个数，
// 有了总个数 keyCnt，就能知道拿到所有钥匙后编码成的整数。在 while 循环，采用层序遍历的机制，对于同一层的结点，分别取出位置信息和钥匙信息，
// 此时先判断下是否已经拿到所有钥匙了，是的话直接返回当前步数 res。否则就要检测其四个相邻位置，需要注意的是，对于每个相邻位置，一定要重新取出之前的钥匙信息，
// 否则一旦钥匙信息修改了而没有重置的话，直接到同一层的其他结点可能会引起错误。取出的邻居结点的位置要先判断是否越界，还要判断是否为墙，是的话就直接跳过。
// 若是门的话，要看当前是否有该门对应的钥匙，有的话才能通过。若遇到了钥匙，则需要修改钥匙信息。这些都完成了之后，将当前的位置和钥匙信息编码成一个字符串，
// 看 HashSet 是否已经有了这个状态，没有的话，则加入 HashSet，并同时加入 queue，每当一层结点遍历完成后，结果 res 自增1即可，参见代码如下：
public class ShortestPathGetAllKeys {

    public int shortestPaht(int[][] matrix) {
        int keyCount = 0;
        int rows = matrix.length;
        int cols = matrix[0].length;
        int startRow, startCol;
        Queue<int[]> queue = new LinkedList<>();
        HashSet<String> visited = new HashSet<>();
        for(int i= 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                if(matrix[i][j] == '@') {
                    queue.offer(new int[]{ i, j, 0, 0 });
                    visited.add(String.format("%d:%d:0", i, j));
                }
                else if(matrix[i][j] >= 'a' && matrix[i][j] <= 'f')
                    keyCount++;
            }
        }

        int[] dRow = {-1, 0, 1, 0};
        int[] dCol = {0, -1, 0, 1};
        while (!queue.isEmpty()) {
            //same level
            int[] node = queue.poll();
            int curRow = node[0];
            int curCol = node[1];
            int curKeys = node[2];
            int curStep = node[3];
            //retrieved all keys
            if(curKeys == (1 << keyCount) - 1)
                return curStep;
            for(int j = 0; j < 4; j++) {
                int nextRow = curRow + dRow[j];
                int nextCol = curCol + dCol[j];
                if(nextRow < 0 || nextRow >= rows || nextCol < 0 || nextCol >= cols)
                    continue;
                //wall
                if(matrix[nextRow][nextCol] == '#')
                    continue;
                //lock: check if the key match lock
                if(matrix[nextRow][nextCol] >= 'A' && matrix[nextRow][nextCol] <= 'F' &&
                        (curKeys >> (matrix[nextRow][nextCol] - 'A') & 1) == 0)
                    continue;
                //keys
                int nextKeys = curKeys;
                if(matrix[nextRow][nextCol] >= 'a' && matrix[nextRow][nextCol] <= 'f') {
                    nextKeys |=  (1 << (matrix[nextRow][nextCol] - 'a'));
                }

                String tmp = String.format("%d:%d:%d", nextRow, nextCol, nextKeys);
                if(!visited.contains(tmp)) {
                    queue.offer(new int[] {nextRow, nextCol, nextKeys, curStep+1});
                    visited.add(tmp);
                }
            }
        }

        return -1;
    }
}
