package app.tree.no226_invertbinary;

/**
Invert a binary tree.

Example:

Input:

     4
   /   \
  2     7
 / \   / \
1   3 6   9
Output:

     4
   /   \
  7     2
 / \   / \
9   6 3   1
Trivia:
This problem was inspired by this original tweet by Max Howell:

Google: 90% of our engineers use the software you wrote (Homebrew), but you can’t invert a binary tree on a whiteboard so f*** off.
 */
/**
 * Definition for a binary tree node.
 */
 class TreeNode {
     int val;
     TreeNode left;
     TreeNode right;
     TreeNode(int x) { val = x; }
 }

class Solution {
    public TreeNode invertTree(TreeNode root) {
        preorder(root);
        return root;
    }

    public void preorder(TreeNode t) {
        if (t != null) {
            // swap left and right
            TreeNode tmp = t.left;
            t.left = t.right;
            t.right = tmp;
            preorder(t.left);
            preorder(t.right);
        }
    }
}

public class InvertBinary {
    public static void main(String[] args) {
        
    }
}