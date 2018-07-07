package com.example.console;

import java.util.Stack;

public class BST<T extends Comparable<? super T>> implements Iterable<T> {
    private TreeNode<T> root;

    public BST(T value){
        root = new TreeNode<>(value);
    }

    public class TreeNode<T> {
        public TreeNode<T> left, right;
        private T data;
        private TreeNode(T value){
            data = value;
        }
    }

    public TreeNode<T> getRoot() {
        return root;
    }

    public void add(T value) {
        if(root == null) {
            root = new TreeNode<>(value);
            return;
        }

        TreeNode<T> current = root;
        while (current != null) {
            if(value.compareTo(current.data) <= 0) {
                if(current.left == null) {
                    current.left = new TreeNode<>(value);
                    return;
                }
                else
                    current = current.left;
            }
            else {
                if(current.right == null) {
                    current.right = new TreeNode<>(value);
                    return;
                }
                else
                    current = current.right;
            }
        }
    }

    public void add(TreeNode<T> root, T value) {
        if(root == null) {
            return;
        }
        if(value.compareTo(root.data) <= 0) {
            if(root.left == null)
                root.left = new TreeNode<>(value);
            else
                add(root.left, value);
        }
        else {
            if(root.right == null)
                root.right = new TreeNode<>(value);
            else
                add(root.right, value);
        }
    }

    public void preorder(TreeNode<T> node) {
        if(node == null)
            return;
        System.out.println(node.data);
        preorder(node.left);
        preorder(node.right);
    }

    public void preorderNonRec(TreeNode<T> node) {
        if(node == null)
            return;
        Stack<TreeNode<T>> stack = new Stack<>();
        stack.push(node);
        while(!stack.isEmpty()){
            node = stack.pop();
            System.out.println(node.data);
            if(node.right != null)
                stack.push(node.right);
            if(node.left != null)
                stack.push(node.left);
        }
    }

    public void inorder(TreeNode<T> node){
        if(node == null)
            return;
        inorder(node.left);
        System.out.println(node.data);
        inorder(node.right);
    }

    public void inorderNonRec(TreeNode<T> node) {
        if(node == null)
            return;
        Stack<TreeNode<T>> stack = new Stack<>();
        while (true) {
            if(node != null) {
                stack.push(node);
                node = node.left;
            }else {
                if(stack.isEmpty())
                    break;
                node = stack.pop();
                System.out.println(node.data);
                node = node.right;
            }
        }
    }

    public void postorder(TreeNode<T> node) {
        if(node==null)
            return;
        postorder(node.left);
        postorder(node.right);
        System.out.println(node.data);
    }

    public void postorderNonRec(TreeNode<T> node) {
        if(node == null)
            return;
        Stack<TreeNode<T>> stack = new Stack<>();
        stack.push(node);
        while(!stack.isEmpty()){
            TreeNode<T> tmp = stack.peek();
            boolean isLeaf = (tmp.left == null && tmp.right == null);
            boolean isSubtreeFinished = (tmp.left == node || tmp.right == node);
            if(isLeaf || isSubtreeFinished) {
                node = stack.pop();
                System.out.println(node.data);
            }
            else{
                if(tmp.right != null)
                    stack.push(tmp.right);
                if(tmp.left != null)
                    stack.push(tmp.left);
            }
        }
    }
}
