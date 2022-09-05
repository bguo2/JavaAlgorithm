package com.example.console;

import org.apache.commons.lang3.tuple.Pair;

import java.util.HashSet;
import java.util.Set;

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
//Time to write down the algorithm for the backtrack function backtrack(cell = (0, 0), direction = 0).
//
//Mark the cell as visited and clean it up.
//
//Explore 4 directions : up, right, down, and left (the order is important since the idea is always to turn right) :
//
//Check the next cell in the chosen direction :
//
//If it's not visited yet and there is no obtacles :
//
//Move forward.
//
//Explore next cells backtrack(new_cell, new_direction).
//
//Backtrack, i.e. go back to the previous cell.
//
//Turn right because now there is an obstacle (or a virtual obstacle) just in front.
public class RobotCleaner {
    interface Robot {
        public boolean move();

        public void turnLeft();

        public void turnRight();

        public void clean();
    }

    // going clockwise : 0: 'up', 1: 'right', 2: 'down', 3: 'left'
    int[][] directions = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
    Set<Pair<Integer, Integer>> visited = new HashSet();
    Robot robot;

    public void goBack() {
        robot.turnRight();
        robot.turnRight();
        robot.move();
        robot.turnRight();
        robot.turnRight();
    }

    public void backtrack(int row, int col, int d) {
        visited.add(Pair.of(row, col));
        robot.clean();
        // going clockwise : 0: 'up', 1: 'right', 2: 'down', 3: 'left'
        for (int i = 0; i < 4; ++i) {
            int newD = (d + i) % 4;
            int newRow = row + directions[newD][0];
            int newCol = col + directions[newD][1];
            //not visited and empty space
            if (!visited.contains(Pair.of(newRow, newCol)) && robot.move()) {
                backtrack(newRow, newCol, newD);
                goBack();
            }
            //obstacle
            // turn the robot following chosen direction : clockwise
            robot.turnRight();
        }
    }

    public void cleanRoom(Robot robot) {
        this.robot = robot;
        backtrack(0, 0, 0);
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
