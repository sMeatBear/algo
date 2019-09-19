class SolutionValidBST {
    
    public boolean isValidBST(TreeNode root) {
        if (root != null) {
            TreeNode rightmost = null, leftmost = null, p;
            boolean left, right;

            // in order
            // 1. left child is less than the root and right child is greater than the root
            // 2. find left child tree most right leaf which shoube smaller than the root 
            //      and right child tree leftmost leaf greater than the root
            p = root.left
            while (p != null) {
                rightmost = p;
                p = p.right;
            }
            
            p = root.right;
            while (p != null) {
                leftmost = p;
                p = p.left;
            }
            
            // check left and right
            if (root.left == null || root.left.val < root.val && rightmost.val < root.val) left = true;
            else left = false;
            
            if (root.right == null || root.right.val > root.val && leftmost.val > root.val) right = true;
            else right = false;
            
            return left && right && isValidBST(root.left) && isValidBST(root.right);
        } else {
            return true;
        }
        
    }
}

public class ValidateBST {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(10);
        SolutionValidBST s = new SolutionValidBST();
        System.out.println(s.isValidBST(root));
    }
}