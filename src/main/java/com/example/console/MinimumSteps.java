package com.example.console;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
    given a matrix nxm, 1 means flat, 0 mean trench, 9 means a obstacle. A robot starts from [0][0] to
    remove the obstacle, it can move left, right , up and down, get the minimum steps to remove obstacle
    e.g. [[1,0,0]
          [1,0,0]
          [1,9,0]] it moves from [0,0], [1,0], [2,0], [2,1] => 3 steps.
 */
public class MinimumSteps {

    private class Node {
        private int x, y, dist;
        public Node(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }

    //algorithm:
    //create a queue and enqueue the start point
    //pop front node from the queue
    //if the popped node is 9, then it is done
    //else for each 4 adjacent nodes, enqueue each valid node to the queue with dist+1 and mark it as visited
    //until the queue is empty
    public int LeeAlgorithm(int[][] lots)
    {
        final int[] dx = {-1, 0, 0, 1};
        final int[] dy = {0, 1, -1, 0};
        int rows = lots.length;
        int columns = lots[0].length;
        Queue<Node> queue = new LinkedList<>();
        boolean[][] visited = new boolean[rows][columns];

        queue.offer(new Node(0,0, 0));
        visited[0][0] = true;
        while (!queue.isEmpty())
        {
            Node node = queue.poll();
            int value = lots[node.x][node.y];
            if(value == 9) {
                return node.dist;
            }

            for(int k = 0; k < 4; k++)
            {
                int i = node.x + dx[k];
                int j = node.y + dy[k];
                if(i >=0 && i < rows && j >=0 && j < columns && lots[i][j] != 0 && !visited[i][j])
                {
                    if(lots[i][j] == 9)
                        return node.dist + 1;
                    visited[i][j] = true;
                    queue.offer(new Node(i, j, node.dist+1));
                }
            }
        }

        return -1;
    }

    public int minSteps(int[][] lots)
    {
        int[] min = { Integer.MAX_VALUE};
        dfs(0, 0, 0, min, lots);
        return min[0];
    }

    private void dfs(int i, int j, int curSteps, int[] min, int[][] lots) {
        int rows = lots.length;
        int columns = lots[0].length;

        int tmp = lots[i][j];
        if(tmp == 2)
            return;
        if(tmp == 9) {
            min[0] = Math.min(curSteps, min[0]);
            return;
        }

        if(tmp == 1)
            lots[i][j] = 2;
        if(j - 1 >= 0 && lots[i][j-1] != 0)
            dfs(i, j - 1, curSteps+1, min, lots);
        if(j+1 < columns && lots[i][j+1] !=0)
            dfs(i, j + 1, curSteps+1, min, lots);
        if(i-1 >= 0 && lots[i-1][j] != 0)
            dfs(i - 1, j, curSteps+1, min, lots);
        if(i+1 < rows && lots[i+1][j] != 0)
            dfs(i + 1, j, curSteps+1, min, lots);
        if(tmp == 1)
            lots[i][j] = tmp;
        return;
    }

}
