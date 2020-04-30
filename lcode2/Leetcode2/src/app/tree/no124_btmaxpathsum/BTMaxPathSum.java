package app.tree.no124_btmaxpathsum;

import app.tree.TreeNode;

/**
 * Given a non-empty binary tree, find the maximum path sum.

For this problem, a path is defined as any sequence of nodes from some starting node to any node in the tree along the parent-child connections. The path must contain at least one node and does not need to go through the root.

Example 1:

Input: [1,2,3]

       1
      / \
     2   3

Output: 6
Example 2:

Input: [-10,9,20,null,null,15,7]

   -10
   / \
  9  20
    /  \
   15   7

Output: 42
 */


/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    int max = Integer.MIN_VALUE;
    // approach 2: improvement of app1
    public int maxPathSum(TreeNode root) {
        maxPathSumHelper(root);
        return max;
    }
    
    public int maxPathSumHelper(TreeNode root) {
        if (root == null) {
            return 0;   
        }
        
        // If the reulst is less than 0, then we take 0
        int leftMax = Math.max(0, maxPathSumHelper(root.left));
        int rightMax = Math.max(0, maxPathSumHelper(root.right));
        
        max = Math.max(leftMax + rightMax + root.val, max);
        return leftMax > rightMax ? leftMax + root.val : rightMax + root.val;
    }
    
    // Approach 1: recursive method
    public int maxPathSum1(TreeNode root) {
        maxPathSumHelper(root);
        return max;
    }
    
    public int maxPathSumHelper1(TreeNode root) {
        if (root == null) {
            return 0;   
        }
        
        int leftMaxSum = maxPathSumHelper(root.left), rightMaxSum = maxPathSumHelper(root.right);
        if (leftMaxSum <= 0 && rightMaxSum <= 0) {
            max = Math.max(max, root.val);
            return root.val;
        } else if (leftMaxSum <= 0) {
            int tmp = rightMaxSum + root.val;
            max = Math.max(max, tmp);
            return tmp;
        } else if (rightMaxSum <= 0) {
            int tmp = leftMaxSum + root.val;
            max = Math.max(max, tmp);
            return tmp;
        } else {
            max = Math.max(max, leftMaxSum + rightMaxSum + root.val);
            return leftMaxSum > rightMaxSum ? leftMaxSum + root.val : rightMaxSum + root.val;
        }
        
    }
}

public class BTMaxPathSum {

}