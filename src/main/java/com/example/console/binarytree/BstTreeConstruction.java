package com.example.console.binarytree;

import java.util.Stack;

public class BstTreeConstruction {
    //reconstruct Btree with preorder
    //Input: preorder = [8,5,1,7,10,12]
    //Output: [8,5,10,1,7,null,12]
    //Input: preorder = [1,3]
    //Output: [1,null,3]
    public TreeNode reconstructWithPreOrder(int[] preorder) {
        if(preorder == null || preorder.length == 0)
            return null;
        TreeNode root = new TreeNode(preorder[0]);
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        for(int i = 1; i < preorder.length; i++) {
            TreeNode t = null;
            //if the current value > stack's top one, then pop up it
            while (!stack.isEmpty() && preorder[i] > stack.peek().val) {
                t = stack.pop();
            }

            if(t != null) {
                t.right = new TreeNode(preorder[i]);
                stack.push(t.right);
            }
            else {
                t = new TreeNode(preorder[i]);
                stack.peek().left = t;
                stack.push(t);
            }
        }

        return root;
    }

    //reconstruct BST from sorted array
    public static TreeNode reconstructBstInorder(int[] inorder) {
        if(inorder.length == 0)
            return null;
        return reconstructInorder(inorder, 0, inorder.length - 1);
    }

    private static TreeNode reconstructInorder(int[] inorder, int start, int end) {
        if(start > end)
            return null;
        int mid = (end-start)/2;
        TreeNode node = new TreeNode(inorder[mid]);
        node.left = reconstructInorder(inorder, start, mid-1);
        node.right = reconstructInorder(inorder, mid+1, end);
        return node;
    }
}
