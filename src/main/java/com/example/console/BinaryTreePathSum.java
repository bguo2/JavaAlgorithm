package com.example.console;

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

        public TreeNode(TreeNode left, TreeNode right, int value){
            this.left = left;
            this.right = right;
            this.value = value;
        }

        public TreeNode addNode(int value, boolean isLeftNode) {
            if(isLeftNode) {
                this.left = new TreeNode(null, null, value);
                return this.left;
            }
            this.right = new TreeNode(null, null, value);
            return this.right;
        }
    }

    public List<List<Integer>> getAllPathsSum(TreeNode root, int sum) {
        if(root == null)
            return null;
        List<List<Integer>> result = new LinkedList<>();
        List<Integer> curList = new LinkedList<>();
        curList.add(root.value);
        dfs(root, sum, result, curList);
        return result;
    }

    private void dfs(TreeNode root, int sum, List<List<Integer>> result, List<Integer> curList){
        if(root == null)
            return;
        if(root.left == null && root.right == null && root.value == sum){
            result.add(curList);
            return;
        }

        //left child is not null: copy the curList
        if(root.left != null) {
            List<Integer> tmpList = new LinkedList<>(curList);
            tmpList.add(root.left.value);
            dfs(root.left, sum - root.value, result, tmpList);
        }

        if(root.right != null) {
            List<Integer> tmpList = new LinkedList<>(curList);
            tmpList.add(root.right.value);
            dfs(root.right, sum-root.value, result, tmpList);
        }
    }
}
