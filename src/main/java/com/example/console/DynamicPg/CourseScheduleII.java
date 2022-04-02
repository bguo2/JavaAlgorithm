package com.example.console.DynamicPg;

import java.util.*;

//There are a total of n courses you have to take, labeled from 0 to n-1.
//
//Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]
//
//Given the total number of courses and a list of prerequisite pairs, return the ordering of courses you should take to finish all courses.
//
//There may be multiple correct orders, you just need to return one of them. If it is impossible to finish all courses, return an empty array.
//
//Example 1:
//
//Input: 2, [[1,0]]
//Output: [0,1]
//Explanation: There are a total of 2 courses to take. To take course 1 you should have finished
//             course 0. So the correct course order is [0,1] .
//Example 2:
//
//Input: 4, [[1,0],[2,0],[3,1],[3,2]]
//Output: [0,1,2,3] or [0,2,1,3]
//Explanation: There are a total of 4 courses to take. To take course 3 you should have finished both
//             courses 1 and 2. Both courses 1 and 2 should be taken after you finished course 0.
//             So one correct course order is [0,1,2,3]. Another correct ordering is [0,2,1,3] .

//This problem is a classic graph topological sort problem. Each prerequisite has edges to the courses that require it.
//
//We define in degree as the number of edges into a node in the graph. What we do is we remove the nodes that has in degree equals to 0,
// decrease the in degree of the nodes that require the current node, and repeat, until we've removed all the nodes (the successful case),
// or there's no node with in degree equals to 0 (the failed case).
public class CourseScheduleII {

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        if (numCourses == 0)
            return null;
        // Convert graph presentation from edges to indegree of adjacent list.
        int indegree[] = new int[numCourses], order[] = new int[numCourses], index = 0;
        for (int i = 0; i < prerequisites.length; i++) // Indegree - how many prerequisites are needed.
            indegree[prerequisites[i][0]]++;

        Queue<Integer> queue = new LinkedList<Integer>();
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                // Add the course to the order because it has no prerequisites.
                order[index++] = i;
                queue.offer(i);
            }
        }

        // How many courses don't need prerequisites.
        while (!queue.isEmpty()) {
            int prerequisite = queue.poll(); // Already finished this prerequisite course.
            for (int i = 0; i < prerequisites.length; i++)  {
                if (prerequisites[i][1] == prerequisite) {
                    indegree[prerequisites[i][0]]--;
                    if (indegree[prerequisites[i][0]] == 0) {
                        // If indegree is zero, then add the course to the order.
                        order[index++] = prerequisites[i][0];
                        queue.offer(prerequisites[i][0]);
                    }
                }
            }
        }

        return (index == numCourses) ? order : new int[0];
    }

    //tasks and its dependencies, output the scheduling order, if there is cycular, return empty;
    //input is T1:[], T2: [T1, T3], T3: [T2, T1];
    //return the order of sceduled tasks
    public static class Task {
        public String taskToRemove;
        public Set<String> dependencies;
    }

    public static List<String> findOrder(List<Task> tasks) {
        List<String> result = new ArrayList<>();
        if(tasks == null || tasks.size() == 0)
            return result;
        Queue<String> noDepends = new LinkedList<>();
        Iterator<Task> iterator = tasks.iterator();
        while (iterator.hasNext()) {
            Task task = iterator.next();
            if(task.dependencies == null || task.dependencies.isEmpty()) {
                noDepends.offer(task.taskToRemove);
                iterator.remove();
            }
        }

        while (!noDepends.isEmpty()) {
            String taskToRemove = noDepends.poll();
            result.add(taskToRemove);
            Iterator<Task> iter = tasks.iterator();
            while(iter.hasNext()) {
                Task task = iter.next();
                Set<String> depends = task.dependencies;
                if(depends.contains(taskToRemove)) {
                    depends.remove(taskToRemove);
                    if(depends.isEmpty()) {
                        noDepends.offer(task.taskToRemove);
                        iter.remove();
                    }
                }
            }
        }

        if(tasks.isEmpty())
            return result;
        return new ArrayList<>();
    }

    public static List<String> findOrder1(String[] tasks0, List<String>[] dependencies) {
        Map<String, Set<String>> taskDependMap = new HashMap<>();
        List<String> result = new ArrayList<>();
        List<String> tasks = new LinkedList<>(Arrays.asList(tasks0));
        for(int i = 0; i < tasks.size(); i++) {
            String taskToRemove = tasks.get(i);
            List<String> depends = dependencies[i];
            Set<String> set = new HashSet<>();
            if(depends != null) {
                set.addAll(depends);
            }
            taskDependMap.put(taskToRemove, set);
        }

        Queue<String> noDepends = new LinkedList<>();
        for(int i = 0; i < tasks.size(); i++) {
            Set<String> depends = taskDependMap.get(tasks.get(i));
            if(depends == null || depends.isEmpty()) {
                noDepends.offer(tasks.get(i));
                taskDependMap.remove(tasks.get(i));
                tasks.remove(i);
                i--;
            }
        }

        while (!noDepends.isEmpty()) {
            String noDependTask = noDepends.poll();
            taskDependMap.remove(noDependTask);
            result.add(noDependTask);
            for(int i = 0; i < tasks.size(); i++) {
                Set<String> dependedTasks = taskDependMap.get(tasks.get(i));
                if(dependedTasks != null && noDependTask.contains(noDependTask)) {
                    dependedTasks.remove(noDependTask);
                }
                if(dependedTasks == null || dependedTasks.isEmpty()) {
                    noDepends.add(tasks.get(i));
                    tasks.remove(i);
                    i--;
                }
            }
        }

        if(tasks.isEmpty())
            return result;
        return new ArrayList<>();
    }
}
