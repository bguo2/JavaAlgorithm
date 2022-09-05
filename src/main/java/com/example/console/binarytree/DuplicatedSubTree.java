package com.example.console.binarytree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

//Find Duplicate Subtrees
//Given the root of a binary tree, return all duplicate subtrees.
//
//For each kind of duplicate subtrees, you only need to return the root node of any one of them.
//
//Two trees are duplicate if they have the same structure with the same node values.
//
//
//
//Example 1:
//    1
//   /\
//  2  3
//  \   \
//   4   2
//        \
//         4
//Input: root = [1,2,3,4,null,2,4,null,null,4]
//Output: [[2,4],[4]]
//Example 2:
//
//
//Input: root = [2,1,1]
//Output: [[1]]
//Example 3:
//
//
//Input: root = [2,2,2,3,null,3,null]
//Output: [[2,3],[3]]
//
//
//Constraints:
//
//The number of the nodes in the tree will be in the range [1, 10^4]
//-200 <= Node.val <= 200
public class DuplicatedSubTree {
    private HashMap<String, Integer> dict;
    private List<TreeNode> res;
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        dict = new HashMap<>();
        res = new ArrayList<>();

        search(root);

        return res;
    }

    public String search(TreeNode node) {
        if(node == null){
            return "";
        }

        String ser_left = search(node.left);
        String ser_right = search(node.right);

        // serilized the treenode
        String sub = ser_left + "," + ser_right + "," + node.val;

        int freq = dict.getOrDefault(sub, 0) + 1;

        // only care if we face it twice
        if(freq == 2) {
            res.add(node);
        }

        dict.put(sub, freq);

        return sub;
    }
}
