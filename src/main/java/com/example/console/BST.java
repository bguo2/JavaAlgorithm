package com.example.console;

public class BST<T extends Comparable<? super T>>  {
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
}
