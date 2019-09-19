class SolutionInsertBST {
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) return new TreeNode(val);
        
        TreeNode p = root;
        TreeNode newNode = new TreeNode(val);
        while (p != null) {
            if (val < p.val) {
                if (p.left == null) p.left = newNode;
                p = p.left;
            } else if (val > p.val) {
                if (p.right == null) p.right = newNode;
                p = p.right;
            } else {
                return root;
            }
        }
        
        return root;
    }
}

public class InsertBST {
    public static void main(String[] args) {
        
    }
}