package com.example.console;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

//Your car starts at position 0 and speed +1 on an infinite number line. Your car can go into negative positions.
// Your car drives automatically according to a sequence of instructions 'A' (accelerate) and 'R' (reverse):
//
//When you get an instruction 'A', your car does the following:
//position += speed
//speed *= 2
//When you get an instruction 'R', your car does the following:
//If your speed is positive then speed = -1
//otherwise speed = 1
//Your position stays the same.
//For example, after commands "AAR", your car goes to positions 0 --> 1 --> 3 --> 3, and your speed goes to 1 --> 2 --> 4 --> -1.
//
//Given a target position target, return the length of the shortest sequence of instructions to get there.
//
//
//
//Example 1:
//
//Input: target = 3
//Output: 2
//Explanation:
//The shortest instruction sequence is "AA".
//Your position goes from 0 --> 1 --> 3.
//Example 2:
//
//Input: target = 6
//Output: 5
//Explanation:
//The shortest instruction sequence is "AAARA".
//Your position goes from 0 --> 1 --> 3 --> 7 --> 7 --> 6.
//
//
//Constraints:
//
//1 <= target <= 104
public class RaceCar {
    public int racecar(int target) {
        Queue<RaceCarData> queue = new LinkedList<RaceCarData>();
        Set<RaceCarData> visitedPositions = new HashSet<RaceCarData>();
        // If we are reaching a target that is negative we can simply add reverse
        // and change the target to a positive value and apply the same logic
        if (target < 1) {
            RaceCarData rev = new RaceCarData(0, -1, 1);
            queue.add(rev);
            target = target*(-1);
        }
        else {
            RaceCarData start = new RaceCarData(0, 1, 0);
            queue.add(start);
        }

        while (!queue.isEmpty()) {
            RaceCarData currData = queue.poll();
            if (visitedPositions.contains(currData)) {
                continue;
            }

            visitedPositions.add(currData);
            // If we reach our target
            if (currData.position==target) {
                return currData.moves;
            } else {
                // We can accelerate both backwards and forwards
                RaceCarData accelerate = new RaceCarData(currData.position+currData.speed, currData.speed*2, currData.moves+1);
                queue.add(accelerate);
                // Force a reverse before we go further away from target
                if ((currData.position + currData.speed > target && currData.speed > 0)
                        || (currData.position+currData.speed <target && currData.speed<0)) {
                    int revSpeed = -1;
                    if (currData.speed < 0) {
                        revSpeed = 1;
                    }
                    RaceCarData reverse = new RaceCarData(currData.position, revSpeed, currData.moves+1);
                    queue.add(reverse);
                }
            }
        }
        // If no moves are necessary
        return 0;
    }

    private class RaceCarData {
        int position;
        int speed;
        int moves;

        public RaceCarData(int pos, int spd, int moves) {
            this.position = pos;
            this.speed = spd;
            this.moves = moves;
        }

        @Override
        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }

            if (!(o instanceof RaceCarData)) {
                return false;
            }

            RaceCarData rc2  = (RaceCarData) o;
            return rc2.position==position && rc2.speed==speed && rc2.moves==moves;
        }

        @Override
        public int hashCode() {
            return 1;
        }

        @Override
        public String toString() {
            return "Race car data: {position:"+position+" ,speed:"+speed+" ,moves:"+moves+"}";
        }
    }

}
