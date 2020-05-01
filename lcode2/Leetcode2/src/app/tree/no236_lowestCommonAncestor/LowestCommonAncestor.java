package app.tree.no236_lowestCommonAncestor;

import app.tree.TreeNode;

class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }
        if (root == p || root == q) {
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        /* 
         * three situation:
         * 1. when current node get two non-null result from left and right branch
         *    that means current node must be the lowest common ancestor. 
         *    Assume p is found in left tree and q is found in right tree. We just
         *    separate our path to go down to find those two nodes. If we go down to
         *    the left tree, we can only found left, vice vesa. 
         *    So we should return current node
         * 2. When we only found one node in our left tree or right tree. That may 
         *    happen when we didn't meet the common node or we are just upper than 
         *    the common node. We need to just return where we found the 
         *    corresponding node
         * 3. If there is no node return. That means we go into a total wrong path.
         *    We just return null.
         * 4. Note: This method can work only when we can make sure that two nodes 
         *    exist. If only one node is actually in this whole tree. It will return
         *    that node and won't say there is no common ancestor because one of the 
         *    one doesn't exist.
         */
        if (left != null && right != null) {
            return root;
        } else if (left != null) {
            return left;
        } else if (right != null) {
            return right;
        } else {
            return null;
        }
    }
}
public class LowestCommonAncestor {
    
}