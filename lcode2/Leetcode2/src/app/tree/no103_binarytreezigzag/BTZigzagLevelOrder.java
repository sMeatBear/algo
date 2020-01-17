package app.tree.no103_binarytreezigzag;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Definition for a binary tree node.
 */
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

/**
 Given a binary tree, return the zigzag level order traversal of its nodes' values. (ie, from left to right, then right to left for the next level and alternate between).

For example:
Given binary tree [3,9,20,null,null,15,7],
    3
   / \
  9  20
    /  \
   15   7
return its zigzag level order traversal as:
[
  [3],
  [20,9],
  [15,7]
]
 */
class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;

        List<TreeNode> rootLevel = Arrays.asList(new TreeNode[] {root});
        res.add(Arrays.asList(new Integer[] {root.val}));
        levelOrder(rootLevel, false, res);
        return res;
    }

    // top down
    public void levelOrder(List<TreeNode> lastLevel, boolean l2r, List<List<Integer>> res) {
        // get the last level nodes
        List<TreeNode> nextLevel = new ArrayList<>();
        // from left to right
        if (l2r) {
            // The traverse sequence should be opposite with last level
            for (int i = lastLevel.size() - 1; i >= 0; i-- ) {
                TreeNode node = lastLevel.get(i);
                if (node.left != null) nextLevel.add(node.left);
                if (node.right != null) nextLevel.add(node.right);
            }
            // no next level
            if (nextLevel.size() == 0) return;
        } else {
            // from right to left
            for (int i = lastLevel.size() - 1; i >= 0; i-- ) {
                TreeNode node = lastLevel.get(i);
                if (node.right != null) nextLevel.add(node.right);
                if (node.left != null) nextLevel.add(node.left);
            }
            // no next level
            if (nextLevel.size() == 0) return;
        }
        // add val
        List<Integer> nextVal = new ArrayList<>();
        for (TreeNode node : nextLevel) {
            nextVal.add(node.val);
        }
        res.add(nextVal);
        levelOrder(nextLevel, !l2r, res);
    }
}

public class BTZigzagLevelOrder {
    public static void main(String[] args) {
        
    }
}