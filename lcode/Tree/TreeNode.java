public class TreeNode {
    
// Definition for a binary tree node.
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }

    public String traverse(TreeNode root) {
        String str = "";
        if (root != null) {
            str += traverse(root.left);
            str += root.val + " ";
            str += traverse(root.right);
            return str;
        }
        return "";
    }

    @Override
    public String toString() {
        String str = "";
        str += traverse(this);
        return str;
    }
}
