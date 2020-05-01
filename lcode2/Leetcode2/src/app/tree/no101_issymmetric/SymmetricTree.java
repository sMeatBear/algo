package app.tree.no101_issymmetric;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import app.tree.TreeNode;

/**
 * Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).

For example, this binary tree [1,2,2,3,4,4,3] is symmetric:

    1
   / \
  2   2
 / \ / \
3  4 4  3
 

But the following [1,2,2,null,3,null,3] is not:

    1
   / \
  2   2
   \   \
   3    3
 

Follow up: Solve it both recursively and iteratively.
 */
class Solution {
    // approach 3: improved iterative
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }

        // use a queue to do bfs but the enqueue sequence is special.
        Queue<TreeNode> q = new LinkedList<>();
        // do not offer two root node into queue which will double the comparsion
        q.add(root.left);
        q.add(root.right);
        while (!q.isEmpty()) {
            TreeNode t1 = q.poll();
            TreeNode t2 = q.poll();
            if (t1 == null && t2 == null) continue;
            if (t1 == null || t2 == null) return false;
            if (t1.val != t2.val) return false;
            // enqueue next layer corresponding two node together
            q.add(t1.left);
            q.add(t2.right);
            q.add(t1.right);
            q.add(t2.left);
        }
        return true;
    }

    // approach 2: iterative
    public boolean isSymmetric2(TreeNode root) {
        if (root == null) {
            return true;
        }
        
        // level order traverse
        List<TreeNode> curr = new ArrayList<>();
        int valid = 1;
        curr.add(root);
        while (valid >= 1) {
            // reset valid
            valid = 0;
            // compare current level
            for (int i = 0, j = curr.size() - 1; i < j; i++, j--) {
                if (curr.get(i) == null && curr.get(j) == null) {
                    continue;
                } else if (curr.get(i) == null || curr.get(j) == null) {
                    return false;
                } else if (curr.get(i).val != curr.get(j).val) {
                    return false;
                }
            }
            // current layer is the same, generate the next layer
            List<TreeNode> next = new ArrayList<>();
            for (TreeNode node : curr) {
                if (node == null) {
                    continue;
                }
                next.add(node.left);
                next.add(node.right);
                if (node.left != null) {
                    valid++;
                }
                if (node.right != null) {
                    valid++;
                }
            }
            // move to next layer
            curr = next;
        }
        
        return true;
    }
    
    // approach 1: recursive
    public boolean isSymmetric1(TreeNode root) {
        if (root == null) {
            return true;
        }
        return helper(root.left, root.right);
    }
    
    public boolean helper(TreeNode node1, TreeNode node2) {
        if (node1 == null && node2 == null) {
            return true;
        } else if (node1 == null || node2 == null) {
            return false;
        } else if (node1.val != node2.val) {
            return false;
        }
        
        return helper(node1.left, node2.right) && helper(node1.right, node2.left);
    }
}

public class SymmetricTree {
    public static void main(String[] args) {
        Solution sol = new Solution();
        TreeNode root = TreeNode.buildTreeInLvlOrder(new Integer[] {1,2,2,null,3,null,3});
        System.out.println(sol.isSymmetric(root));
    }
}