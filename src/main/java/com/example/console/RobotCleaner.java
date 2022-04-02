package com.example.console;

import java.util.HashSet;

//Given a robot cleaner in a room modeled as a grid.
//
//Each cell in the grid can be empty or blocked.
//
//The robot cleaner with 4 given APIs can move forward, turn left or turn right. Each turn it made is 90 degrees.
//
//When it tries to move into a blocked cell, its bumper sensor detects the obstacle and it stays on the current cell.
//
//Design an algorithm to clean the entire room using only the 4 given APIs shown below.
//interface Robot {
//  // returns true if next cell is open and robot moves into the cell.
//  // returns false if next cell is obstacle and robot stays on the current cell.
//  boolean move();
//
//  // Robot will stay on the same cell after calling turnLeft/turnRight.
//  // Each turn will be 90 degrees.
//  void turnLeft();
//  void turnRight();
//
//  // Clean the current cell.
//  void clean();
//}
//Input:
//room = [
//[1,1,1,1,1,0,1,1],
//[1,1,1,1,1,0,1,1],
//[1,0,1,1,1,1,1,1],
//[0,0,0,1,0,0,0,0],
//[1,1,1,1,1,1,1,1]
//],
//row = 1,
//col = 3
//Explanation:
//All grids in the room are marked by either 0 or 1. 0 means the cell is blocked, while 1 means the cell is accessible.
// The robot initially starts at the position of row=1, col=3. From the top left corner, its position is one row below and
// three columns right.
//在递归函数中，我们首先对起始位置调用 clean 函数，因为题目中说了起始位置是能到达的，即是为1的地方。然后就要把起始位置加入 visited。
// 然后我们循环四次，因为有四个方向，由于递归函数传进来的 dir 是上一次转到的方向，那么此时我们 dir 加上i，为了防止越界，对4取余，
// 就是我们新的方向了，然后算出新的位置坐标 newX 和 newY。此时先要判断 visited 不含有这个新位置，即新位置没有访问过，还要调用 move 函数来确定
// 新位置是否可以到达，若这两个条件都满足的话，我们就对新位置调用递归函数。注意递归函数调用完成后，我们要回到调用之前的状态，因为这里的 robot
// 是带了引用号的，是全局通用的，所以要回到之前的状态。回到之前的状态很简单，因为这里的机器人的运作方式是先转到要前进的方向，才能前进。
// 那么我们后退的方法就是，旋转 180 度，前进一步，再转回到原来的方向。同理，我们在按顺序试上->右->下->左的时候，每次机器人要向右转一下，
// 因为 move 函数只能探测前方是否能到达，所以我们必须让机器人转到正确的方向，才能正确的调用 move 函数。如果用过扫地机器人的童鞋应该会有影响，
// 当前方有障碍物的时候，机器人圆盘会先转个方向，然后再继续前进，这里要实现的机制也是类似的
public class RobotCleaner {
    public class Robot {
        public boolean move() { return true;}
        public void turnLeft() {}
        public void turnRight() {}
        public void clean() {}
    }
    public void clean(Robot robot) {
        HashSet<String> visited = new HashSet<>();
        visited.add("0:0");
        helper(robot, 0, 0, 0, visited);
    }

    private void helper(Robot robot, int row, int col, int dir,  HashSet<String> visited) {
        robot.clean();
        int[] drow = {-1, 0, 0, 1};
        int[] dcol = {0, -1, 1, 0};
        visited.add(String.format("%d:%d", row, col));
        for(int i = 0; i < 4; i++) {
            dir = (dir + i) % 4;
            int curRow = row + drow[dir];
            int curCol = col + dcol[dir];
            //is it a block?
            String tmp = String.format("%d:%d", curRow, curCol);
            if(robot.move() && !visited.contains(tmp)) {
                helper(robot, curRow, curCol, dir, visited);
                //move and turn to the original direction
                robot.turnRight();
                robot.turnRight();
                robot.move();
                robot.turnLeft();
                robot.turnLeft();
            }
            //block: change a direction and try
            else {
                robot.turnLeft();
            }
        }
    }

    //On an infinite plane, a robot initially stands at (0, 0) and faces north. The robot can receive one of three instructions:
    //
    //"G": go straight 1 unit;
    //"L": turn 90 degrees to the left;
    //"R": turn 90 degrees to the right.
    //The robot performs the instructions given in order, and repeats them forever.
    //
    //Return true if and only if there exists a circle in the plane such that the robot never leaves the circle.
    //Example 1:
    //
    //Input: instructions = "GGLLGG"
    //Output: true
    //Explanation: The robot moves from (0,0) to (0,2), turns 180 degrees, and then returns to (0,0).
    //When repeating these instructions, the robot remains in the circle of radius 2 centered at the origin.
    //Example 2:
    //
    //Input: instructions = "GG"
    //Output: false
    //Explanation: The robot moves north indefinitely.
    //Example 3:
    //
    //Input: instructions = "GL"
    //Output: true
    //Explanation: The robot moves from (0, 0) -> (0, 1) -> (-1, 1) -> (-1, 0) -> (0, 0) -> ...
    //
    //
    //Constraints:
    //
    //1 <= instructions.length <= 100
    //instructions[i] is 'G', 'L' or, 'R'.
    public boolean isRobotBounded(String instructions) {
        int[] dx = {0, -1, 1, 0};
        int[] dy = {1, 0, 0, -1};
        //0 - N, 2-S, 1-E, 3-W
        int idx = 0;
        int[] curPos = {0, 0};
        for(int i = 0; i < instructions.length(); i++) {
            char c = instructions.charAt(i);
            if(c == 'G') {
                curPos[0] += dx[idx];
                curPos[1] += dy[idx];
            }
            else if(c == 'L') {  //West
                idx = (idx + 3) % 4;
            }
            else { //East
                idx = (idx + 1) % 4;
            }
        }
        return (curPos[0] == 0 && curPos[1] == 0) || idx > 0;
    }
}
