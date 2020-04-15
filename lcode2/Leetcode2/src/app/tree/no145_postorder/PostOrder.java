package app.tree.no145_postorder;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import app.tree.TreeNode;

class Solution {
    // approach 2: iterative traversal
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        
        if (root == null) {
            return res;
        }
        
        TreeNode curr = root, prev = null;
        stack.push(curr);
        while (!stack.isEmpty()) {
            curr = stack.peek();
            // current node is root or previous node's children
            if (prev == null || curr == prev.left || curr == prev.right) {
                if (curr.left != null) {
                    stack.push(curr.left);
                } else if (curr.right != null) {
                    stack.push(curr.right);
                } else {
                    // leaf node
                    res.add(curr.val);
                    stack.pop();
                }
            } else if (curr.left == prev) {
                // return from left branch
                if (curr.right != null) {
                    stack.push(curr.right);
                } else {
                    // no right child
                    res.add(curr.val);
                    stack.pop();
                }
            } else {
                // return from right child (the third time visit)
                res.add(curr.val);
                stack.pop();
            }
            
            // update 
            prev = curr;
        }
        
        return res;
    }
    
    // approach 1: recursive traversal
    public List<Integer> postorderTraversal1(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        
        postorder(root, res);
        
        return res;
    }
    
    private void postorder(TreeNode t, List<Integer> res) {
        if (t != null) {
            postorder(t.left, res);
            postorder(t.right, res);
            res.add(t.val);
        }
    }
}

public class PostOrder {
    public static void main(String[] args) {
        Integer[] nums = new Integer[] {1,null,2,3};
        TreeNode root = TreeNode.buildTreeInLvlOrder(nums);
        
        Solution s = new Solution();
        List<Integer> res = s.postorderTraversal(root);
        System.out.println(res);
    }
}