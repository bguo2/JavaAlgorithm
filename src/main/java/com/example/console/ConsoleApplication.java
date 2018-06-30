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

        BST<Integer> bst = new BST<>(5);
        BST<Integer>.TreeNode<Integer> root = bst.getRoot();
        bst.add(root, 4);
        bst.add(root, 2);
        bst.add(root, 3);
        bst.add(root, 1);

        System.out.println("end..."); ;
        System.exit(0);
    }
}
