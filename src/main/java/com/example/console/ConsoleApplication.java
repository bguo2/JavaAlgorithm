package com.example.console;

import com.example.console.binarytree.BST;
import org.modelmapper.internal.util.Assert;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.function.Consumer;

@SpringBootApplication
public class ConsoleApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(ConsoleApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
    }

    private void test() throws Exception {
        String str1= new String("ABCD");
        String test = str1.substring(1,1);

        ListImpl<Integer> list = new ListImpl<Integer>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.reverse();

        Consumer<Integer> myconsumer = i -> {
            System.out.println(i);
        };
        list.iterate(myconsumer);
        ListImpl.Node<Integer> head = list.getHead();
        head = list.reverse(head);
        list.setHead(head);
        System.out.println("iterate");
        list.iterate(myconsumer);
        System.out.println("print reversely");
        list.reversePrint(head);

        System.out.println("BST preorder");
        BST<Integer> bst = new BST<>(5);
        BST<Integer>.TreeNode<Integer> root = bst.getRoot();
        bst.add(root, 4);
        bst.add(root, 2);
        bst.add(root, 3);
        bst.add(root, 1);
        bst.add(root, 6);

        bst.preorder(root);
        System.out.println("non-recursive ");
        bst.preorderNonRec(root);

        //preorder iterator
        System.out.println("preorder interator ");
        bst.setIterationType(0);
        Iterator<Integer> iterator = bst.iterator();
        while (iterator.hasNext()){
            Integer data = iterator.next();
            System.out.println(data);
        }

        System.out.println("BST inorder");
        bst.inorder(root);
        System.out.println("BST ineorder Non recursive");
        bst.inorderNonRec(root);

        //iterator
        System.out.println("BST inorder iterator");
        bst.setIterationType(1);
        iterator = bst.iterator();
        while (iterator.hasNext()){
            Integer data = iterator.next();
            System.out.println(data);
        }

        System.out.println("BST postorder");
        bst.postorder(root);
        System.out.println("BST postorder Non recursive");
        bst.postorderNonRec(root);

        //iterator
        System.out.println("BST Postorder iterator");
        bst.setIterationType(2);
        iterator = bst.iterator();
        while (iterator.hasNext()){
            Integer data = iterator.next();
            System.out.println(data);
        }

        System.out.println("BST delete 1");
        BST<Integer>.TreeNode<Integer> t = bst.deleteNode(bst.getRoot(), 1);
        bst.inorder(bst.getRoot());
        System.out.println("BST delete 2");
        bst.add(bst.getRoot(), 1);
        bst.deleteNode(bst.getRoot(), 2);
        bst.inorder(bst.getRoot());
        System.out.println("BST delete 5");
        bst.add(bst.getRoot(), 2);
        bst.deleteNode(bst.getRoot(), 5);
        bst.inorder(bst.getRoot());

        int result = Reverse.reverseNumber(-1234, 0);
        System.out.println("1234 => " + result);
        result = Reverse.reverseNumber(-1234);
        String tmp = Reverse.reverseString("12345");

        boolean match = StringPattern.isMatch("ab", ".*c");
        match = StringPattern.isMatch("aab", "c*a*b");
        match = StringPattern.isMatch("mississippi", "mis*is*p*.");

        int[] arr = {10, 10, 20, 20, 20, 30};
        int index = BinarySearch.Search(arr, 20);
        Assert.isTrue(index == 2);
        index = BinarySearch.Search1(arr, 20);
        Assert.isTrue(index == 4);

        index = BinarySearch.Search(arr, 5);
        Assert.isTrue(index == 0);
        index = BinarySearch.Search(arr, 15);
        Assert.isTrue(index == 2);
        index = BinarySearch.Search(arr, 25);
        Assert.isTrue(index == 5);
        index = BinarySearch.Search(arr, 35);
        Assert.isTrue(index == 6);

        index = BinarySearch.Search(arr, 15, 0, arr.length);

        System.out.println("myQueue test.");
        MyQueue<Integer> queue = new MyQueue<>();
        queue.enqueue1(3);
        queue.enqueue1(4);
        queue.enqueue1(5);
        queue.enqueue1(6);
        while (!queue.isEmpty()){
            System.out.println(queue.dequeue1());
        }

        System.out.println("myStack test.");
        MyStack<Integer> stack = new MyStack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        while (!stack.isEmpty()){
            System.out.println(stack.pop());
        }

        System.exit(0);
    }
}
