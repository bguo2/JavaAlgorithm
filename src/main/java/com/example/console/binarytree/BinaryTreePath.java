package com.example.console.binarytree;

//Step-By-Step Directions From a Binary Tree Node to Another
//You are given the root of a binary tree with n nodes. Each node is uniquely assigned a value from 1 to n.
// You are also given an integer startValue representing the value of the start node s, and
// a different integer destValue representing the value of the destination node t.
//
//Find the shortest path starting from node s and ending at node t. Generate step-by-step directions of
// such path as a string consisting of only the uppercase letters 'L', 'R', and 'U'. Each letter indicates a specific
// direction:
//
//'L' means to go from a node to its left child node.
//'R' means to go from a node to its right child node.
//'U' means to go from a node to its parent node.
//Return the step-by-step directions of the shortest path from node s to node t.
//Example 1:
//            5
//            /\
//           1  2
//          /  / \
//         3  6   4
//
//
//Input: root = [5,1,2,3,null,6,4], startValue = 3, destValue = 6
//Output: "UURL"
//Explanation: The shortest path is: 3 → 1 → 5 → 2 → 6.
//Example 2:
//       2
//      /
//     1
//Input: root = [2,1], startValue = 2, destValue = 1
//Output: "L"
//Explanation: The shortest path is: 2 → 1.
//
//
//Constraints:
//
//The number of nodes in the tree is n.
//2 <= n <= 105
//1 <= Node.val <= n
//All the values in the tree are unique.
//1 <= startValue, destValue <= n
//startValue != destVal
//Build paths and remove common prefix
public class BinaryTreePath {
    boolean dfs(TreeNode root, int target, StringBuilder path){
        if(root.val == target){
            return true;
        }

        if(root.left != null){
            path.append('L');
            if(dfs(root.left, target, path))
                return true;
            path.deleteCharAt(path.length() - 1);
        }

        if(root.right != null){
            path.append('R');
            if(dfs(root.right, target, path))
                return true;
            path.deleteCharAt(path.length() - 1);
        }
        return false;
    }

    //remove common prefix
    String[] removePrefix(String s1, String s2){
        int n1 = s1.length();
        int n2 = s2.length();
        int i=0;
        while(i < Math.min(n1,n2) && s1.charAt(i)==s2.charAt(i))
            i++;
        String[] result = new String[2];
        result[0] = s1.substring(i);
        result[1] = s2.substring(i);
        return result;
    }

    String getDirections(TreeNode root, int startValue, int destValue) {
        StringBuilder pathOne = new StringBuilder();
        StringBuilder pathTwo = new StringBuilder();

        dfs(root, startValue, pathOne);
        dfs(root, destValue, pathTwo);

        String[] res = removePrefix(pathOne.toString(), pathTwo.toString());

        int n = res[0].length();  //pathOne change L to U: reverse order if there are path from root to destination
        StringBuilder s = new StringBuilder();
        if(!res[1].isEmpty()) {
            for (int i = 0; i < n; i++) {
                s.append('U');
            }
        }

        s.append(res[1]); //pathTwo
        return s.toString();
    }
}
