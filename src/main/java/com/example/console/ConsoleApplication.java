package com.example.console;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.swing.plaf.synth.SynthEditorPaneUI;
import java.util.function.Consumer;

@SpringBootApplication
public class ConsoleApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(ConsoleApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        String str1= new String("ABCD");
        String str2= new String("ABCD");
        String str3 = str1;
        if(str1 == str2)
            System.out.println("object equal");
        if(str1 == str3)
            System.out.println("object equal: str1, str3");
        if(str1.equals(str2))
            System.out.println("value is equal");

        ListImpl<Integer> list = new ListImpl<Integer>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.remove(4);
        list.reverse();

        Consumer<Integer> myconsumer = i -> {
            System.out.println(i);
        };
        list.iterate(myconsumer);
        ListImpl<Integer>.Node<Integer> head = list.getHead();
        head = list.reverse(head);
        list.setHead(head);
        list.iterate(myconsumer);

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
        System.out.println("BST inorder");
        bst.inorder(root);
        System.out.println("BST preorder Non recursive");
        bst.inorderNonRec(root);
        System.out.println("BST postorder");
        bst.postorder(root);
        System.out.println("BST postorder Non recursive");
        bst.postorderNonRec(root);
        System.out.println("end..."); ;
        System.exit(0);
    }
}
