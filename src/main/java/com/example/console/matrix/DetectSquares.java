package com.example.console.matrix;

import java.util.HashMap;
import java.util.Map;

//Detect Squares
//You are given a stream of points on the X-Y plane. Design an algorithm that:
//
//Adds new points from the stream into a data structure. Duplicate points are allowed and should be treated as different points.
//Given a query point, counts the number of ways to choose three points from the data structure such that the three points
// and the query point form an axis-aligned square with positive area.
//An axis-aligned square is a square whose edges are all the same length and are either parallel or perpendicular to
// the x-axis and y-axis.
//
//Implement the DetectSquares class:
//
//DetectSquares() Initializes the object with an empty data structure.
//void add(int[] point) Adds a new point point = [x, y] to the data structure.
//int count(int[] point) Counts the number of ways to form axis-aligned squares with point point = [x, y] as described above.
//
//
//Example 1:
//
//
//Input
//["DetectSquares", "add", "add", "add", "count", "count", "add", "count"]
//[[], [[3, 10]], [[11, 2]], [[3, 2]], [[11, 10]], [[14, 8]], [[11, 2]], [[11, 10]]]
//Output
//[null, null, null, null, 1, 0, null, 2]
//
//Explanation
//DetectSquares detectSquares = new DetectSquares();
//detectSquares.add([3, 10]);
//detectSquares.add([11, 2]);
//detectSquares.add([3, 2]);
//detectSquares.count([11, 10]); // return 1. You can choose:
//                               //   - The first, second, and third points
//detectSquares.count([14, 8]);  // return 0. The query point cannot form a square with any points in the data structure.
//detectSquares.add([11, 2]);    // Adding duplicate points is allowed.
//detectSquares.count([11, 10]); // return 2. You can choose:
//                               //   - The first, second, and third points
//                               //   - The first, third, and fourth points
//
//
//Constraints:
//
//point.length == 2
//0 <= x, y <= 1000
//At most 3000 calls in total will be made to add and count.

//given (x, y) and (x1, y1): we just need to find if (x, y1) and (x1, y) exists or not to construct a square
public class DetectSquares {
    final Map<String, Integer> map;

    public DetectSquares() {
        map = new HashMap<>();
    }

    public void add(int[] point) {
        String key = point[0] + "," + point[1];
        map.put(key, map.getOrDefault(key, 0)+1);
    }

    public int count(int[] point) {
        int x = point[0];
        int y = point[1];
        int count = 0;
        for(Map.Entry<String, Integer> entry: map.entrySet()) {
            String key = entry.getKey();
            String[] parts = key.split(",");
            int x1 = Integer.valueOf(parts[0]);
            int y1 = Integer.valueOf(parts[1]);

            if(x1 == x && y1 == y)
                continue;
            if(Math.abs(x1 - x) != Math.abs(y1 - y))
                continue;
            String key1 = x1 + "," + y;
            String key2 = x + "," + y1;
            if(map.containsKey(key1) && map.containsKey(key2)) {
                count += entry.getValue()*map.getOrDefault(key1, 0)*map.getOrDefault(key2, 0);
            }
        }

        return count;
    }
}
