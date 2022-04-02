package com.example.console.binarytree;
//A path in a binary tree is a sequence of nodes where each pair of adjacent nodes in the sequence has an edge connecting them.
// A node can only appear in the sequence at most once. Note that the path does not need to pass through the root.
//
//The path sum of a path is the sum of the node's values in the path.
//
//Given the root of a binary tree, return the maximum path sum of any path.
//
//
//
//Example 1:
//
//
//Input: root = [1,2,3]
//Output: 6
//Explanation: The optimal path is 2 -> 1 -> 3 with a path sum of 2 + 1 + 3 = 6.
//Example 2:
//
//
//Input: root = [-10,9,20,null,null,15,7]
//Output: 42
//Explanation: The optimal path is 15 -> 20 -> 7 with a path sum of 15 + 20 + 7 = 42.
//
//
//Constraints:
//
//The number of nodes in the tree is in the range [1, 3 * 104].
//-1000 <= Node.val <= 1000
public class BinaryTreeMaxPathSum {
    class TreeNode {
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

    int max = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        maxTree(root);
        return max;
    }

    // 在递归函数中，如果当前结点不存在，直接返回0。否则就分别对其左右子节点调用递归函数，由于路径和有可能为负数，这里当然不希望加上负的路径和，
    // 所以和0相比，取较大的那个，就是要么不加，加就要加正数。然后来更新全局最大值结果 res，就是以左子结点为终点的最大 path 之和加上以右子结点为终点的
    // 最大 path 之和，还要加上当前结点值，这样就组成了一个条完整的路径。而返回值是取 left 和 right 中的较大值加上当前结点值，
    // 因为返回值的定义是以当前结点为终点的 path 之和，所以只能取 left 和 right 中较大的那个值，而不是两个都要
    private int maxTree(TreeNode root) {
        if(root == null)
            return 0;
        int leftMax = Math.max(maxTree(root.left), 0);
        int rightMax = Math.max(maxTree(root.right), 0);
        max = Math.max(max, root.val + leftMax + rightMax);
        return root.val + Math.max(leftMax, rightMax);
    }
}
