package com.example.console.binarytree;

//Given the root of a binary tree, find the largest subtree, which is also a Binary Search Tree (BST), where the
// largest means subtree has the largest number of nodes.
//
//A Binary Search Tree (BST) is a tree in which all the nodes follow the below-mentioned properties:
//
//The left subtree values are less than the value of their parent (root) node's value.
//The right subtree values are greater than the value of their parent (root) node's value.
//Note: A subtree must include all of its descendants.
//
//Follow up: Can you figure out ways to solve it with O(n) time complexity?
//
//
//
//Example 1:
//
//
//
//Input: root = [10,5,15,1,8,null,7]
//Output: 3
//Explanation: The Largest BST Subtree in this case is the highlighted one. The return value is the subtree's size, which is 3.
//Example 2:
//
//Input: root = [4,2,7,2,3,5,null,2,null,null,null,null,null,1]
//Output: 2
public class LargestBstInSubTree {
    public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
         this.left = left;
         this.right = right;
      }
    }

    private class SubtreeInfo {
        boolean valid = true;
        int size = 0;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
    }

    public int largestBSTSubtree(TreeNode root) {
        return check(root).size;
    }

    private SubtreeInfo check(TreeNode current) {
        if (current == null) {
            return new SubtreeInfo();
        }
        SubtreeInfo left = check(current.left);
        SubtreeInfo right = check(current.right);

        SubtreeInfo curInfo = new SubtreeInfo();
        if (left.valid && right.valid && left.max < current.val && right.min > current.val) {
            curInfo.valid = true;
            curInfo.size = 1 + left.size + right.size;
        } else {
            curInfo.valid = false;
            curInfo.size = Math.max(left.size, right.size);
        }

        curInfo.min = Math.min(current.val, Math.min(left.min, right.min));
        curInfo.max = Math.max(current.val, Math.max(left.max, right.max));

        return curInfo;
    }
}
