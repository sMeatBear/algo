package app.tree.no110_balancedbinarytree;

import java.util.HashMap;
import java.util.Map;

import app.tree.TreeNode;

class Solution {
    // approach 2: tc: n
    boolean isBal = true;
    
    public int findDepth (TreeNode root) {
        if (root == null || !isBal) return 0;
        else {
            // each node only visited once
            int left = findDepth(root.left);
            int right = findDepth(root.right);
            if (Math.abs(left - right) > 1 || !isBal) {
                isBal = false; 
                return 0;
            }
            
            return Math.max(left, right) + 1;
        }
    }
    
    public boolean isBalanced(TreeNode root) {
        findDepth(root);
        return isBal;
    }

    // approach 1: tc: nlogn
    public boolean isBalanced1(TreeNode root) {
        if (root == null) {
            return true;
        }
        
        int leftHeight = getHeight(root.left);
        int rightHeight = getHeight(root.right);
        if (Math.abs(leftHeight - rightHeight) > 1) {
            return false;
        }
        
        return isBalanced(root.left) && isBalanced(root.right);
    }
    
    public int getHeight(TreeNode root) {
        if (root == null) {
            return 0;
        }
        
        return Math.max(getHeight(root.left), getHeight(root.right)) + 1;
    }
}

public class BalancedBinaryTree {
    
}