package com.example.console;

import com.example.console.DynamicPg.CourseScheduleII;
import com.example.console.subarray.MaxSumKSubarray;
import com.example.console.subarray.SubArraySumEqualK;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.assertTrue;


@RunWith(SpringRunner.class)
@SpringBootTest
public class AppTest9 {

    @Test
    public void islandTest() {
        int[][] positions = new int[][] {
                {0, 0},
                {0, 1},
                {0, 2},
                {2, 1}
        };
        List<Integer> result = IsLand.numberOfIsland(3, 3, positions);
        assertTrue(result.size() == 4);
    }

    @Test
    public void schedulerOrder() {
        List<CourseScheduleII.Task> tasks = new ArrayList<CourseScheduleII.Task>() {{
            add(new CourseScheduleII.Task() {{
                taskToRemove = "T1";
            }});
            add(new CourseScheduleII.Task() {{
                taskToRemove = "T2";
                dependencies = new HashSet<String>() {{
                    add("T3");
                    add("T4");
                }};
            }});
            add(new CourseScheduleII.Task() {{
                taskToRemove = "T3";
                dependencies = new HashSet<String>() {{
                   add("T4");
                   add("T1");
                }};
            }});
            add(new CourseScheduleII.Task() {{
                taskToRemove = "T4";
                dependencies = new HashSet<String>() {{
                    add("T1");
                }};
            }});
        }};

        List<String> result = CourseScheduleII.findOrder(tasks);
        assertTrue(!result.isEmpty() && result.size() == 4 && result.get(0).equals("T1") &&
                result.get(1).equals("T4") && result.get(2).equals("T3") && result.get(3).equals("T2"));

        tasks = new ArrayList<CourseScheduleII.Task>() {{
            add(new CourseScheduleII.Task() {{
                taskToRemove = "T1";
            }});
            add(new CourseScheduleII.Task() {{
                taskToRemove = "T2";
                dependencies = new HashSet<String>() {{
                    add("T3");
                    add("T4");
                }};
            }});
            add(new CourseScheduleII.Task() {{
                taskToRemove = "T3";
                dependencies = new HashSet<String>() {{
                    add("T4");
                    add("T1");
                }};
            }});
            add(new CourseScheduleII.Task() {{
                taskToRemove = "T4";
                dependencies = new HashSet<String>() {{
                    add("T2");
                }};
            }});
        }};
        result = CourseScheduleII.findOrder(tasks);
        assertTrue(result.isEmpty());
    }

    @Test
    public void schedulerTest() {
        String[] tasks = {"T1", "T2", "T3", "T4"};
        List<String>[] depends = new ArrayList[tasks.length];
        depends[0] = new ArrayList<>();
        depends[1] = new ArrayList<>(Arrays.asList("T3", "T4"));
        depends[2] = new ArrayList<>(Arrays.asList("T4", "T1"));
        depends[3] = new ArrayList<>(Arrays.asList("T1"));
        List<String> result = CourseScheduleII.findOrder1(tasks, depends);
        assertTrue(!result.isEmpty() && result.size() == 4 && result.get(0).equals("T1") &&
                result.get(1).equals("T4") && result.get(2).equals("T3") && result.get(3).equals("T2"));

        depends[3] = new ArrayList<>(Arrays.asList("T2"));
        result = CourseScheduleII.findOrder1(tasks, depends);
        assertTrue(result.isEmpty());
    }

    @Test
    public void MaxSumKSubarrayTest() {
        int[] input = {1, 15, 3, 9, 2, 5, 10};
        int result = MaxSumKSubarray.maxSumAfterPartitioning(input, 3);
        assertTrue(result == 84);
    }

    @Test
    public void SubArraySumEqualKTest() {
        int[] input = {10, 2, -2, -20, 10};
        int count = SubArraySumEqualK.subarraySum(input, -10);
        assertTrue(count == 3);
    }

    @Test
    public void countSubsetEqualKtest() {
        int count = SubArraySumEqualK.countSubset(new int[] {1, 2, 3, 2}, 5);
        assertTrue(count == 3);
        count = SubArraySumEqualK.countSubset(new int[] {1, 2, 3, 3}, 6);
        assertTrue(count == 3);
        count = SubArraySumEqualK.countSubset(new int[] {3, 3, 3, 3}, 3);
        assertTrue(count == 4);
    }

    @Test
    public void countSubsetEqualKMemtest() {
        int count = SubArraySumEqualK.countSubsetWithMem(new int[] {1, 2, 3, 2}, 5);
        assertTrue(count == 3);
        count = SubArraySumEqualK.countSubsetWithMem(new int[] {1, 2, 3, 3}, 6);
        assertTrue(count == 3);
        count = SubArraySumEqualK.countSubsetWithMem(new int[] {3, 3, 3, 3}, 3);
        assertTrue(count == 4);
    }

    @Test
    public void FindMusicPairsTest() {
        int[] input = {30,20,150,100,40};
        List<int[]> result = FindMusicPairs.getPairs(input);
        assertTrue(result.size() == 3 && result.get(0)[0] == 30 && result.get(0)[1] == 150 &&
                result.get(1)[0]==20 && result.get(1)[1]==100 && result.get(2)[0]==20 && result.get(2)[1]==40);

        input = new int[] {30,20,150,100,40, 20};
        result = FindMusicPairs.getPairs(input);
        assertTrue(result.size() == 5 && result.get(3)[0]==100 && result.get(3)[1]==20
            && result.get(4)[0]==40 && result.get(4)[1]==20);

        input = new int[] {60,60,60};
        result = FindMusicPairs.getPairs(input);
        assertTrue(result.size() == 3);
    }
}
