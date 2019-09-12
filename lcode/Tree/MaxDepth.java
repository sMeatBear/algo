
// Definition for a binary tree node.
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}

class SolutionMaxDepth {
    public int maxDepth(TreeNode root) {
        while (root != null) {
            return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
        }
        return 0;
    }
}

public class MaxDepth {
    public static void main(String[] args) {
        SolutionMaxDepth s = new SolutionMaxDepth();
        // System.out.println(s.maxDepth());
    }
}