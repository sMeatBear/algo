package app.tree.no114_flattenbinarytree;

import java.util.ArrayDeque;
import java.util.Deque;

import app.tree.TreeNode;
/**
 * 
 * Given a binary tree, flatten it to a linked list in-place.

For example, given the following tree:

    1
   / \
  2   5
 / \   \
3   4   6
The flattened tree should look like:

1
 \
  2
   \
    3
     \
      4
       \
        5
         \
          6

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/flatten-binary-tree-to-linked-list
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

/**
 * Definition for a binary tree node. public class TreeNode { int val; TreeNode
 * left; TreeNode right; TreeNode() {} TreeNode(int val) { this.val = val; }
 * TreeNode(int val, TreeNode left, TreeNode right) { this.val = val; this.left
 * = left; this.right = right; } }
 */
class Solution {
    // approach 3: insert left subtree into curr node and right subtree
    public void flatten(TreeNode curr) {
        while (curr != null) {
            if (curr.left != null) {
                TreeNode next = curr.left;
                TreeNode predecessor = next;
                // ** find the last node in the left subtree as the predecessor
                while (predecessor.right != null) {
                    predecessor = predecessor.right;
                }
                // ** insert the tree
                predecessor.right = curr.right;
                curr.left = null;
                curr.right = next;
            }
            curr = curr.right;
        }
    }

    // approach 2: recursive
    public void flatten2(TreeNode root) {
        if (root == null) {return;}
        preorder(root);
    }

    private TreeNode preorder(TreeNode root) {
        if (root.left == null) {
            if (root.right == null) {
                return root;
            }
            return preorder(root.right);
        } else {
            if (root.right == null) {
                root.right = root.left;
                root.left = null;
                return preorder(root.right);
            }
            TreeNode right = root.right;
            root.right = root.left;
            root.left = null;
            TreeNode leftEnd = preorder(root.right);
            leftEnd.right = right;
            return preorder(leftEnd.right);
        }
    } 
    
    // approach 1: iterative
    public void flatten1(TreeNode root) {
        if (root == null) {return;}
        Deque<TreeNode> stack = new ArrayDeque<>();

        stack.push(root);
        TreeNode prev = null;
        while (!stack.isEmpty()) {
            TreeNode curr = stack.pop();
            if (prev != null) {
                prev.right = curr;
                prev.left = null;
            }
            if (curr.right != null) {stack.push(curr.right);}
            if (curr.left != null) {stack.push(curr.left);}
            prev = curr;
        }
    }
}

public class FlattenBinaryTree {
    public static void main(String[] args) {
        TreeNode root = TreeNode.buildTreeInLvlOrder(new Integer[] {1,2,5,3,4,null,6});
        Solution sol = new Solution();
        sol.flatten(root);
        System.out.println(root);
    }
}