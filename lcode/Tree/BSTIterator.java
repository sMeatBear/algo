import java.util.*;
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class BSTIterator {
    private int pointer;
    private List<TreeNode> seq;
    
    private void getSeq(List<TreeNode> _seq, TreeNode root) {
        if (root != null) {
            getSeq(_seq, root.left);
            _seq.add(root);
            getSeq(_seq, root.right);
        }
    }
    
    public BSTIterator(TreeNode root) {
        seq = new ArrayList<TreeNode>();
        getSeq(seq, root);
        pointer = 0;
    }
    
    /** @return the next smallest number */
    public int next() throws NullPointerException {
        return seq.get(pointer++);
    }
    
    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        if (pointer >= seq.size()) return false;
        else return true;
    }
}