package app.tree.no94_inorder;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import app.tree.TreeNode;

class Solution {
    // approach 2: iterative traversal
    public List<Integer> inorderTraversal(TreeNode root) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        List<Integer> res = new ArrayList<>();
        TreeNode curr = root;
        
        // why curr != null ? because when the empty but the root's right may haven't been traversed
        while (!stack.isEmpty() || curr != null) {
            if (curr != null) {
                stack.push(curr);
                curr = curr.left;
            } else {
                curr = stack.pop();
                res.add(curr.val);
                curr = curr.right;
            }
        }
                
        return res;
    }
    
    // approach 1: recursive traversal
    public List<Integer> inorderTraversal1(TreeNode root) {
        // do the recursive function 
        List<Integer> res = new ArrayList<>();
        
        inorder(root, res);
        
        return res;
    }
    
    public void inorder(TreeNode root, List<Integer> res) {
        if (root != null) {
            inorder(root.left, res);
            res.add(root.val);
            inorder(root.right, res);
        }
    }
}

public class Inorder {
    public static void main(String[] args) {
        
    }
}