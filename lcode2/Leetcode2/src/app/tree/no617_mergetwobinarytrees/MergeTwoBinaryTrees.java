package app.tree.no617_mergetwobinarytrees;


/**
 * Definition for a binary tree node.
 */
 class TreeNode {
     int val;
     TreeNode left;
     TreeNode right;
     TreeNode(int x) { val = x; }
 }


class Solution {// optimized one
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
    
    
    // original
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