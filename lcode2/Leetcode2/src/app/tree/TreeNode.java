package app.tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;
    public TreeNode(int x) { 
        val = x; 
    }
    
    /**
     * Build a tree by level order
     * @param nums level order data (If there is empty node in between, use null to represent)
     * @return
     */
    public static TreeNode buildTreeInLvlOrder(Integer[] nums) {
        if (nums == null || nums.length < 1) {
            return null;
        } 

        TreeNode root = new TreeNode(nums[0]);
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);

        // build tree by bfs
        int i = 1;
        while (i < nums.length) {
            TreeNode parent = queue.poll();
            // build left child node, skip the null
            if (nums[i] != null) {
                TreeNode left = new TreeNode(nums[i]);
                queue.offer(left);
                parent.left = left;
            }
            i++;

            if (i < nums.length) {
                if (nums[i] != null) {
                    TreeNode right = new TreeNode(nums[i]);
                    queue.offer(right);
                    parent.right = right;
                }
                i++;
            } else {
                break;
            }
        }

        return root;
    }

    @Override
    public String toString() {
        List<Integer> strList;
        // level order print
        strList = levelorder(this);

        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (Integer val : strList) {
            sb.append(val == null ? "null" : val).append(',');
        }

        return sb.substring(0, sb.length() - 1) + ']';
    }

    /**
     * @param root given the root of the tree
     * @return level order tree node
     */
    private List<Integer> levelorder(TreeNode root) {
        // bfs
        List<Integer> res = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();

        if (root == null) {
            return null;
        }

        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode curr = queue.poll();
            if (curr != null) {
                res.add(curr.val);
                
                queue.offer(curr.left);
                queue.offer(curr.right);
            } else {
                res.add(null);
            }
        }

        return res;
    }
}