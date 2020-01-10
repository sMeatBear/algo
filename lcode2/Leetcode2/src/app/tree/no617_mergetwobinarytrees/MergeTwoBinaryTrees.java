package app.tree.no617_mergetwobinarytrees;

import java.util.ArrayDeque;
import java.util.Deque;

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
    // optimized one
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if (t1 == null)
            return t2;
        if (t2 == null)
            return t1;
        t1.val += t2.val;
        t1.left = mergeTrees(t1.left, t2.left);
        t1.right = mergeTrees(t1.right, t2.right);
        return t1;
    }

    // iterative method
    public TreeNode iterativeMergeTrees(TreeNode t1, TreeNode t2) {
        // special cases
        if (t1 == null) return t2;
        if (t2 == null) return t1;
        
        Deque<TreeNode> stack1 = new ArrayDeque<>(), stack2 = new ArrayDeque<>();
        TreeNode cur1 = t1, cur2 = t2;

        // Push into the stack
        stack1.push(t1);
        stack2.push(t2);

        while (!stack1.isEmpty() && !stack2.isEmpty()) {
            cur1 = stack1.pop();
            cur2 = stack2.pop();
            cur1.val += cur2.val;

            // Push new nodes into stacks
            // left
            if (cur1.left != null && cur2.left != null) {
                stack1.push(cur1.left);
                stack2.push(cur2.left);
            } else if (cur2.left != null) {
                // change t2's left subtree to t1's
                cur1.left = cur2.left;
            }
            // right
            if (cur1.right != null && cur2.right != null) {
                stack1.push(cur1.right);
                stack2.push(cur2.right);
            } else if (cur2.right != null) {
                // change t2's right subtree to t1's
                cur1.right = cur2.right;
            }
        }

        return t1;
    }
    
    
    public TreeNode mergeTrees1(TreeNode t1, TreeNode t2) {
        // special cases
        if (t1 == null) return t2;
        if (t2 == null) return t1;
        
        // pre-order traver
        t1.val += t2.val;
        traverseTree(t1.left, t2.left, t1, true);
        traverseTree(t1.right, t2.right, t1, false);

        return t1;
    }

    /**
     * @param t1 binary tree1's current node 
     * @param t2 binary tree2's current node
     * @param p1 bt1's parent
     * @param p2 bt2's parent
     * @param isLeft if current child is left child 
     */
    public void traverseTree(TreeNode t1, TreeNode t2, TreeNode p1, boolean isLeft) {
        // pre-order traverse
        if (t1 != null && t2 != null) {
            t1.val += t2.val;
            // traverse left
            traverseTree(t1.left, t2.left, t1, true);
            // traverse right
            traverseTree(t1.right, t2.right, t1, false);
        } else if (t2 != null) {
            // create a new node for t1 if t1 is null but t2 is not.
            t1 = new TreeNode(t2.val);
            if (isLeft) p1.left = t1;
            else p1.right = t1;
            // traverse left
            traverseTree(t1.left, t2.left, t1, true);
            // traverse right
            traverseTree(t1.right, t2.right, t1, false);
        }
    }
}


public class MergeTwoBinaryTrees {
    public static void main(String[] args) {
        
    }
}