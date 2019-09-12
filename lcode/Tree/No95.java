/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution95 {
    // Binary search,  divide section into left part and right p
    public List<TreeNode> generateTrees(int start, int end) {
        List<TreeNode> res = new ArrayList<TreeNode>();
        // outlet 
        if (start > end) {
            res.add(null);
        }

        // find the middle point as a root
        for (int i = start; i <= end; i++) {
            // i is the middle point
            List<TreeNode> left = generateTrees(start, i - 1);
            List<TreeNode> right = generateTrees(i + 1, end);

            // form unique roots
            for (TreeNode l : left) {
                for (TreeNode r : right) {
                    // regard current node as root
                    TreeNode root = new TreeNode(i);
                    root.left = l;
                    root.right = r;
                    res.add(root);
                }
            }
        }
        
        return res;
        
    }
    
    public List<TreeNode> generateTrees(int n) {
        // traverse a full binary tree with n depth
        if (n == 0) return new ArrayList<TreeNode>();
        
        return generateTrees(1, n);
    }
}

public class No95 {
    public static void main(String[] args) {
        Solution95 s = new Solution95();
    }
}