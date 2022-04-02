package com.example.console;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

//Given an integer array nums, return all possible subsets (the power set).
//
//The solution set must not contain duplicate subsets.
//
//
//
//Example 1:
//
//Input: nums = [1,2,3]
//Output: [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
//Example 2:
//
//Input: nums = [0]
//Output: [[],[0]]
//
//
//Constraints:
//
//1 <= nums.length <= 10
//-10 <= nums[i] <= 10
public class Subset {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        for(int i = 0; i < nums.length; i++) {
            List<List<Integer>> temp = new ArrayList<>();
            for(List<Integer> a: result) {
                temp.add(new ArrayList<Integer>(a));
            }

            //add nums[i] to each list of temp
            for(List<Integer> a: temp) {
                a.add(nums[i]);
            }

            //add nums[i] alone
            List<Integer> alone = new ArrayList<>();
            alone.add(nums[i]);
            temp.add(alone);

            result.addAll(temp);
        }

        //add empty
        result.add(new ArrayList<>());
        return result;
    }

//Given a collection of integers that might contain duplicates, nums, return all possible subsets (the power set).
//
//Note: The solution set must not contain duplicate subsets.
//
//Example:
//
//Input: [1,2,2]
//Output:
//[
//  [2],
//  [1],
//  [1,2,2],
//  [2,2],
//  [1,2],
//  []
//]
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if(nums == null || nums.length == 0)
            return result;
        Arrays.sort(nums);
        int last = -1, lastSize = 0;
        for(int i = 0; i < nums.length; i++) {
            List<List<Integer>> temp = new ArrayList<>();
            for(List<Integer> a: result) {
                temp.add(a);
            }

            int newSize = temp.size();
            if(i > 0 && last == nums[i]) {
                //System.out.println(" " + lastSize + " " + newSize);
                for(int j = lastSize; j < newSize; j++) {
                    List<Integer> a = temp.get(j);
                    List<Integer> t = new ArrayList<>(a);
                    t.add(nums[i]);
                    result.add(t);
                }
            }
            else {
                for(List<Integer> a: temp) {
                    List<Integer> t = new ArrayList<>(a);
                    t.add(nums[i]);
                    result.add(t);
                }

                List<Integer> single = new ArrayList<>();
                single.add(nums[i]);
                result.add(single);
            }

            last = nums[i];
            lastSize = newSize;
        }

        result.add(new ArrayList<Integer>());
        return result;
    }

}
