package app.tree.no1008_constructbstinpreorder;
/**
 * Return the root node of a binary search tree that matches the given preorder traversal.

(Recall that a binary search tree is a binary tree where for every node, any descendant of node.left has a value < node.val, and any descendant of node.right has a value > node.val.  Also recall that a preorder traversal displays the value of the node first, then traverses node.left, then traverses node.right.)

 

Example 1:

Input: [8,5,1,7,10,12]
Output: [8,5,10,1,7,null,12]

 

Note: 

1 <= preorder.length <= 100
The values of preorder are distinct.
 */

import app.tree.TreeNode;

/**
 * Definition for a binary tree node. public class TreeNode { int val; TreeNode
 * left; TreeNode right; TreeNode(int x) { val = x; } }
 */
class Solution {
    private int i = 0;
    // approach 2: improved recursive
    public TreeNode bstFromPreorder(int[] preorder) {
        return build(preorder, Integer.MAX_VALUE);
    }

    private TreeNode build(int[] preorder, int upperBound) {
        // if index reaches the end or current node cannot be a child node return
        if (i >= preorder.length || preorder[i] > upperBound) {
            return null;
        }

        // current node can be a child node as whatever a left or right child
        TreeNode root = new TreeNode(preorder[i++]);
        // left child must smaller than current node value 
        // * (no lowerbound is because we assume the array is valid)
        root.left = build(preorder, root.val);
        // extends the upperbound to restrict the value
        root.right = build(preorder, upperBound);

        return root;
    }

    // approach 1: recursive
    public TreeNode bstFromPreorder1(int[] preorder) {
        if (preorder == null || preorder.length == 0) {
            return null;
        }
        
        TreeNode head = new TreeNode(preorder[0]);
        build(preorder, 1, head, Integer.MAX_VALUE);
        
        return head;
    }
    
    /**
     * @param upperBound for right node
     * @return next node position in the array
     */
    private int build(int[] preorder, int i, TreeNode parent, int upperBound) {
        if (parent != null && i < preorder.length) {
            if (preorder[i] < parent.val) {
                parent.left = new TreeNode(preorder[i]);
                // build left child
                i = build(preorder, i + 1, parent.left, parent.val);
            } 
            
            if ( i < preorder.length && preorder[i] > parent.val && preorder[i] < upperBound) {
                parent.right = new TreeNode(preorder[i]);
                // build right
                i = build(preorder, i + 1, parent.right, upperBound);
            }
            
            return i;
        } else {
            return i;
        }
        
    }
}
public class ConstructBstInPreorder {
    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] preorder = new int[] {8,5,1,7,10,12};
        System.out.println(sol.bstFromPreorder(preorder));
    }
}