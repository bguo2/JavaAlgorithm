package com.example.console.binarytree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

//Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.
//
//For example, given the below binary tree and sum = 22,
//the method returns the following:
//
//[
//   [5,4,11,2],
//   [5,8,4,5]
//]
public class BinaryTreePathSum {
    public class TreeNode {
        private TreeNode left;
        private TreeNode right;
        int value;

        public TreeNode(TreeNode left, TreeNode right, int valueue) {
            this.left = left;
            this.right = right;
            this.value = valueue;
        }

        public TreeNode addNode(int valueue, boolean isLeftNode) {
            if(isLeftNode) {
                this.left = new TreeNode(null, null, valueue);
                return this.left;
            }
            this.right = new TreeNode(null, null, valueue);
            return this.right;
        }
    }

    public List<List<Integer>> getAllPathsSum(TreeNode root, int sum) {
        if(root == null)
            return null;
        List<List<Integer>> result = new LinkedList<>();
        List<Integer> curList = new LinkedList<>();
        pathSum(root, sum, result, curList);
        return result;
    }

    private void pathSum(TreeNode root, int sum, List<List<Integer>> result, List<Integer> tmp) {
        if(root == null)
            return;

        if(root.left == null && root.right == null && root.value == sum) {
            List<Integer> t1 = new ArrayList<>(tmp);
            t1.add(root.value);
            result.add(t1);
            return;
        }

        if(root.left != null) {
            List<Integer> t1 = new ArrayList<>(tmp);
            t1.add(root.value);
            pathSum(root.left, sum-root.value, result, t1);
        }

        if(root.right != null) {
            List<Integer> t1 = new ArrayList<>(tmp);
            t1.add(root.value);
            pathSum(root.right, sum-root.value, result, t1);
        }
    }

    //or
    /*
    private void pathSum(TreeNode root, int sum, List<List<Integer>> result, List<Integer> tmp) {
        if(root.left == null && root.right == null && root.val == sum) {
            List<Integer> t1 = new ArrayList<>(tmp);
            t1.add(root.val);
            result.add(t1);
            return;
        }

        if(root.left != null) {
            tmp.add(root.val);
            pathSum(root.left, sum-root.val, result, tmp);
            tmp.remove(tmp.size()-1);
        }

        if(root.right != null) {
            tmp.add(root.val);
            pathSum(root.right, sum-root.val, result, tmp);
            tmp.remove(tmp.size()-1);
        }
    }
     */

    //Given a binary tree in which each node contains an integer number. Determine if there exists a path
    // (the path can only be from one node to itself or to any of its descendants),
    // the sum of the numbers on the path is the given target number.
    //    5
    //  /   \
    // 2     11
    //      /  \
    //    6     14
    //   /
    //  3
    //If target = 17, There exists a path 11 + 6, the sum of the path is target.
    //If target = 20, There exists a path 11 + 6 + 3, the sum of the path is target.
    //If target = 10, There does not exist any paths sum of which is target.
    //If target = 11, There exists a path only containing the node 11.

    public boolean existsPath(TreeNode root, int target) {
        if(root == null)
            return false;
        List<Integer> prefix = new ArrayList<>();
        boolean[] found = new boolean[1];
        findPath(root, prefix, target, found);
        return found[0];
    }

    private void findPath(TreeNode root, List<Integer> prefix, int target, boolean[] found) {
        if(root == null || found[0])
            return;
        prefix.add(root.value);
        found[0] = findTarget(target, prefix);
        findPath(root.left, prefix, target, found);
        findPath(root.right, prefix, target, found);
        prefix.remove(prefix.size() - 1);
    }

    private boolean findTarget(int target, List<Integer> prefix) {
        int sum = 0;
        for (int i = prefix.size()-1; i>=0; i--) {
            sum += prefix.get(i);
            if (sum == target) {
                return true;
            }
        }
        return false;
    }

    //You are given a binary tree in which each node contains an integer valueue.
    //Find the number of paths that sum to a given valueue.
    //The tree has no more than 1,000 nodes and the valueues are in the range -1,000,000 to 1,000,000.
    //Example:
    //root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8
    //
    //      10
    //     /  \
    //    5   -3
    //   / \    \
    //  3   2   11
    // / \   \
    //3  -2   1
    //
    //Return 3. The paths that sum to 8 are:
    //
    //1.  5 -> 3
    //2.  5 -> 2 -> 1
    //3. -3 -> 11

    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     *     int value;
     *     TreeNode left;
     *     TreeNode right;
     *     TreeNode() {}
     *     TreeNode(int value) { this.value = value; }
     *     TreeNode(int value, TreeNode left, TreeNode right) {
     *         this.value = value;
     *         this.left = left;
     *         this.right = right;
     *     }
     * }
     */
    public int pathSum(TreeNode root, int sum) {
        if(root == null)
            return 0;
        List<Integer> prefix = new ArrayList<>();
        int[] count = new int[] {0};
        findPath(root, sum, prefix, count);
        return count[0];
    }

    private void findPath(TreeNode root, int sum, List<Integer> prefix, int[] count) {
        if(root == null)
            return;
        prefix.add(root.value);
        exists(prefix, sum, count);
        findPath(root.left, sum, prefix, count);
        findPath(root.right, sum, prefix, count);
        prefix.remove(prefix.size() - 1);
    }

    private void exists(List<Integer> prefix, int sum, int[] count) {
        int localSum = 0;
        for(int i = prefix.size() - 1; i >= 0; i--) {
            localSum += prefix.get(i);
            if(localSum == sum)
                count[0]++;
        }
    }
}
