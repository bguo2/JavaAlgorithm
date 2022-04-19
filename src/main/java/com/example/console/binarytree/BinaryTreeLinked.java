package com.example.console.binarytree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

//Populating Next Right Pointers in Each Node II
//Medium
//
//2283
//
//196
//
//Add to List
//
//Share
//Given a binary tree
//
//struct Node {
//  int val;
//  Node *left;
//  Node *right;
//  Node *next;
//}
//Populate each next pointer to point to its next right node. If there is no next right node, the next pointer
// should be set to NULL.
//
//Initially, all next pointers are set to NULL.
//
//
//
//Follow up:
//
//You may only use constant extra space.
//Recursive approach is fine, you may assume implicit stack space does not count as extra space for this problem.
//
//
//Example 1:
//      1
//      /\
//     2->3
//    /\   \
//   4->5-> 6
//Input: root = [1,2,3,4,5,null,6]
//Output: [1,#,2,3,#,4,5,6,#]
//Explanation: Given the above binary tree (Figure A), your function should populate each next pointer to point to its next right
// node, just like in Figure B. The serialized output is in level order as connected by the next pointers, with '#' signifying the
// end of each level.
public class BinaryTreeLinked {
    public static class Node {
        int val;
        public Node left;
        public Node right;
        Node next;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    };
    //use O(1) space
    //我们建立一个dummy结点来指向每层的首结点的前一个结点，然后指针cur用来遍历这一层，我们实际上是遍历一层，然后连下一层的next，
    // 首先从根结点开始，如果左子结点存在，那么cur的next连上左子结点，然后cur指向其next指针；如果root的右子结点存在，那么cur的next连上右子结点，
    // 然后cur指向其next指针。此时root的左右子结点都连上了，此时root向右平移一位，指向其next指针，如果此时root不存在了，说明当前层已经遍历完了，
    // 我们重置cur为dummy结点，root此时为dummy->next，即下一层的首结点，然后dummy的next指针清空，或者也可以将cur的next指针清空，
    // 因为前面已经将cur赋值为dummy了。那么现在想一想，为什么要清空？因为我们用dummy的目的就是要指到下一行的首结点的位置即dummy->next，
    // 而一旦将root赋值为dummy->next了之后，这个dummy的使命就已经完成了，必须要断开，如果不断开的话，那么假设现在root是叶结点了，
    // 那么while循环还会执行，不会进入前两个if，然后root右移赋空之后，会进入最后一个if，之前没有断开dummy->next的话，那么root又指向之前的叶结点了，
    // 死循环诞生了，跪了。
    public Node connect(Node root) {
        if(root == null)
            return null;
        Node dummy = new Node();
        Node cur = dummy, head = root;
        while(root != null) {
            if(root.left != null) {
                cur.next = root.left;
                cur = cur.next;
            }
            if(root.right != null) {
                cur.next = root.right;
                cur = cur.next;
            }
            root = root.next;
            if(root == null) {
                cur = dummy;
                root = dummy.next;
                dummy.next = null;
            }
        }
        return head;
    }

    public Node connectI(Node root) {
        if(root == null)
            return root;
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()) {
            int size = queue.size();
            for(int i = 0; i < size; i++) {
                Node node = queue.poll();
                if(i < size - 1)
                    node.next = queue.peek();
                if(node.left != null)
                    queue.offer(node.left);
                if(node.right != null)
                    queue.offer(node.right);
            }
        }

        return root;
    }

//print out binary tree
//      1
//      /\
//     2  3
//    /\   \
//   4  5   6
//          /\
//         7  8
//return [1,2,4,5,7,8,6,3]
    public static List<Integer> getTreeResult(Node root) {
        if(root == null) {
            return new ArrayList<>();
        }

        Queue<Node> queue = new LinkedList<>();
        Queue<Node> leftNodes = new LinkedList<>();
        Queue<Node> leafNodes = new LinkedList<>();
        Queue<Node> rightNodes = new LinkedList<>();
        Node lastLeftNode = null;

        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for(int i = 0; i < size;  i++) {
                Node node = queue.poll();
                if(i == 0 && lastLeftNode == null) {
                    leftNodes.offer(node);
                    if(node.left == null)
                        lastLeftNode = node;
                }
                else if(node.left == null && node.right == null)
                    leafNodes.offer(node);
                else if(i == size - 1) {
                    ((LinkedList)rightNodes).addFirst(node);
                }

                if(node.left != null)
                    queue.offer(node.left);
                if(node.right != null)
                    queue.offer(node.right);
            }
        }

        List<Integer> result = new ArrayList<>();
        appendResult(leftNodes, result);
        appendResult(leafNodes, result);
        appendResult(rightNodes, result);
        return result;
    }

    private static void appendResult(Queue<? extends Node> queue, List<? super Integer> result) {
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            result.add(node.val);
        }
    }
}
