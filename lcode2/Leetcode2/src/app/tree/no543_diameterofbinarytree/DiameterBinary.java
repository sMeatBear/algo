package app.tree.no543_diameterofbinarytree;


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
    int max = 1;
    // approach 1 dfs:
    public int diameterOfBinaryTree(TreeNode root) {
        traverse(root);
        return max - 1;
    }
    
    // bottom up
    public int traverse(TreeNode t) {
        if (t != null) {
            int left = traverse(t.left), right = traverse(t.right);
            max = Math.max(left + right + 1, max);
            return Math.max(left, right) + 1;
        }
        return 0;
    }
}

public class DiameterBinary {
    public static void main(String[] args) {
        
    }
}