package com.example.console.binarytree;
import java.util.Stack;

/*
Given the root node of a binary search tree and two integers low and high, return the sum of values of all nodes with a value
in the inclusive range [low, high].



Example 1:
  10
  /\
 5  15
 /\  \
3  7  18
Input: root = [10,5,15,3,7,null,18], low = 7, high = 15
Output: 32
Explanation: Nodes 7, 10, and 15 are in the range [7, 15]. 7 + 10 + 15 = 32.
Example 2:
       10
       /\
      5  15
     /\   /\
    3  7 13 18
   /  /
  1   6
Input: root = [10,5,15,3,7,13,18,1,null,6], low = 6, high = 10
Output: 23
Explanation: Nodes 6, 7, and 10 are in the range [6, 10]. 6 + 7 + 10 = 23.


Constraints:

The number of nodes in the tree is in the range [1, 2 * 104].
1 <= Node.val <= 105
1 <= low <= high <= 105
All Node.val are unique.

 */
public class BstRangeSum {
    static int sum = 0;
    public static int rangeSumBST(TreeNode root, int low, int high) {
        if(root == null)
            return 0;
        inorder(root, low, high);
        return sum;
    }

    private static void inorder(TreeNode root, int low, int high) {
        if(root == null)
            return;
        if(root.left != null)
            inorder(root.left, low, high);

        if(root.val >= low && root.val <= high) {
            sum += root.val;
        }

        if(root.right != null)
            inorder(root.right, low, high);
    }

    public static int rangeSumBST1(TreeNode root, int L, int R) {
        int ans = 0;
        if(root == null)
            return ans;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while(!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if(L <= node.val && node.val <= R) {
                ans += node.val;
            }
            if(L < node.val && node.left != null) {
                stack.push(node.left);
            }
            if(R > node.val && node.right != null) {
                stack.push(node.right);
            }
        }

        return ans;
    }

    public static int getRangeSum(TreeNode root, int low, int high) {
        if(root == null)
            return 0;
        int curSum = 0;
        if(low <= root.val && root.val <= high) {
            curSum += root.val;
        }

        if(low < root.val) {
            curSum += getRangeSum(root.left, low, high);
        }

        if(high > root.val) {
            curSum += getRangeSum(root.right, low, high);
        }

        return curSum;
    }
}

