package com.example.console.binarytree;
//Given a binary tree, find the length of the longest consecutive sequence path.
//
//The path refers to any sequence of nodes from some starting node to any node in the tree along the parent-child connections.
// The longest consecutive path need to be from parent to child (cannot be the reverse).
//
//Example 1:
//
//Input:
//
//   1
//    \
//     3
//    / \
//   2   4
//        \
//         5
//
//Output: 3
//
//Explanation: Longest consecutive sequence path is 3-4-5, so return 3.
//Example 2:
//
//Input:
//
//   2
//    \
//     3
//    /
//   2
//  /
// 1
//
//Output: 2
//
//Explanation: Longest consecutive sequence path is 2-3, not 3-2-1, so return 2.
public class LongestConsecutiveSequence {
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

    public int longestConsecutive(TreeNode root) {
        int[] max = new int[1];
        helper(root, max);
        return max[0];
    }

    private int helper(TreeNode root, int[] max) {
        if(root == null)
            return 0;
        int leftMax = helper(root.left, max) + 1;
        int rightMax = helper(root.right, max) + 1;
        if(root.left != null && root.val != root.left.val - 1) {
            leftMax = 1;
        }

        if(root.right != null && root.val != root.right.val - 1) {
            rightMax = 1;
        }

        int curLen = Math.max(leftMax, rightMax);
        max[0] = Math.max(max[0], curLen);
        return curLen;
    }

    //Binary Tree Longest Consecutive Sequence II
    //Given a binary tree, you need to find the length of Longest Consecutive Path in Binary Tree.
    //
    //Especially, this path can be either increasing or decreasing. For example, [1,2,3,4] and [4,3,2,1] are
    // both considered valid, but the path [1,2,4,3] is not valid. On the other hand, the path can be in the child-Parent-child order,
    // where not necessarily be parent-child order.
    //
    //Example 1:
    //
    //Input:
    //        1
    //       / \
    //      2   3
    //Output: 2
    //Explanation: The longest consecutive path is [1, 2] or [2, 1].
    //
    //
    //Example 2:
    //
    //Input:
    //        2
    //       / \
    //      1   3
    //Output: 3
    //Explanation: The longest consecutive path is [1, 2, 3] or [3, 2, 1].
    int max = 0;
    public int longestConsecutive1(TreeNode root) {
        getLongestConsecutive(root);
        return max;
    }

    private int[] getLongestConsecutive(TreeNode root) {
        // returns [longest_decreasing_length_from_root, longest_increasing_length_from_root]
        if (root == null)
            return new int[] {0, 0}; //inc, desc
        int[] left = getLongestConsecutive(root.left);
        int[] right = getLongestConsecutive(root.right);

        //current node
        if(root.left != null && root.val + 1 == root.left.val) {
            left[0]++;
        }
        else {
            left[0] = 1;
        }

        if(root.left != null && root.val - 1 == root.left.val) {
            left[1]++;
        }
        else {
            left[1] = 1;
        }


        if(root.right != null && root.val + 1 == root.right.val) {
            right[0]++;
        }
        else {
            right[0] = 1;
        }

        if(root.right != null && root.val - 1 == root.right.val) {
            right[1]++;
        }
        else {
            right[1] = 1;
        }
        max = Math.max(max, Math.max(left[0]+right[1]-1, left[1]+right[0])-1);

        return new int[] { Math.max(left[0], right[0]), Math.max(left[1], right[1]) };
    }
}
