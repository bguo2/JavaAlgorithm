package com.example.console;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Iterator;
import java.util.Stack;

public class BST<T extends Comparable<? super   T>> implements Iterable<T> {
    private TreeNode<T> root;
    private int iterationType = 0;

    public BST(T value) {
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

    public T minimum(TreeNode<T> root) {
        if(root == null)
            return null;
        if(root.left == null)
            return root.data;
        return minimum(root.left);
    }

    public TreeNode<T> deleteNode(TreeNode<T> node, T value) {
        if(node == null)
            return null;
        //go right
        if(node.data.compareTo(value) < 0) {
            node.right = deleteNode(node.right, value);
        }
        //go left
        else if(node.data.compareTo(value) > 0) {
            node.left = deleteNode(node.left, value);
        }
        else{
            if(node.left == null)
                return node.right;
            if(node.right == null)
                return node.left;
            T min = minimum(node.right);
            node.data = min;
            node.right = deleteNode(node.right, min);
        }

        return node;
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

    //Iterator implementation
    public void setIterationType(int type) {
        iterationType = type;
    }

    public Iterator<T> iterator() {
        switch (iterationType)
        {
            case 1: return new BSTInorderIterator();
            case 2: return new BSTPostorderIterator();
            default: return new BSTPreorderIterator();
        }
    }

    private class BSTPreorderIterator implements Iterator<T> {
        private TreeNode<T> current = root;
        private Stack<TreeNode<T>> stack = new Stack<>();

        public BSTPreorderIterator(){
            if(root != null)
                stack.push(root);
        }

        @Override
        public boolean hasNext() {
            return !stack.empty();
        }

        @Override
        public T next() {
            current = stack.pop();
            if(current.right != null){
                stack.push(current.right);
            }
            if(current.left != null) {
                stack.push(current.left);
            }
            return current.data;
        }

        // not implemented
        @Override
        public void remove(){
            throw new NotImplementedException();
        }
    }

    private class BSTInorderIterator implements Iterator<T> {
        private Stack<TreeNode<T>> stack = new Stack<>();

        public BSTInorderIterator(){
            TreeNode<T> current = root;
            while(current != null){
                stack.push(current);
                current = current.left;
            }
        }

        @Override
        public boolean hasNext() {
            return !stack.empty();
        }

        @Override
        public T next() {
            TreeNode<T> node = stack.pop();
            if(node.right != null) {
                TreeNode<T> current = node.right;
                while (current != null){
                    stack.push(current);
                    current = current.left;
                }
            }
            return node.data;
        }

        @Override
        public void remove() {
            throw new NotImplementedException();
        }
    }

    private class BSTPostorderIterator implements Iterator<T> {
        private Stack<TreeNode<T>> stack = new Stack<>();
        private TreeNode<T> popNode = root;

        public BSTPostorderIterator(){
            if(root != null)
                stack.push(root);
        }

        @Override
        public boolean hasNext() {
            return !stack.empty();
        }

        @Override
        public T next() {

            while (!stack.empty()){
                TreeNode<T> node = stack.peek();
                boolean isLeaf = (node.left == null && node.right == null);
                boolean finishedSubtree = (node.left == popNode || node.right == popNode);
                if(isLeaf || finishedSubtree){
                    //pop
                    popNode = stack.pop();
                    return popNode.data;
                }
                else{
                    if(node.right != null)
                        stack.push(node.right);
                    if(node.left != null)
                        stack.push(node.left);
                }
            }

            return null;
        }

        @Override
        public void remove() {
            throw new NotImplementedException();
        }
    }
}
