package app.tree.no95_uniquebst;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import app.tree.TreeNode;

/**
 * Given an integer n, generate all structurally unique BST's (binary search trees) that store values 1 ... n.

Example:

Input: 3
Output:
[
  [1,null,3,2],
  [3,2,null,1],
  [3,1,null,null,2],
  [2,1,3],
  [1,null,2,null,3]
]
Explanation:
The above output corresponds to the 5 unique BST's shown below:

   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3
Accepted
181,913
Submissions
461,495
 */
class Solution {
    // approach 2: dp
    /**
     * Base rule: only 1
     * Induction: add a larger element you can only add to its root or right subtree
     */
    public List<TreeNode> generateTrees(int n) {
        if (n <= 0) {
            return new ArrayList<>();
        }
        List<TreeNode> prevRes;
        List<TreeNode> list = new ArrayList<>();
        list.add(new TreeNode(1));
        prevRes = list;

        for (int i = 2; i <= n; i++) {
            list = new ArrayList<>();
            for (int j = 0; j < prevRes.size(); j++) {
                TreeNode root = prevRes.get(j);
                // 1. take new node as the root
                TreeNode newRoot = new TreeNode(i);
                newRoot.left = root;
                list.add(newRoot);

                // 2. find all the right node make it as current left children and add the new node as right child
                TreeNode curr = root;
                while (curr.right != null) {
                    TreeNode newNode = new TreeNode(i);
                    TreeNode oriRight = curr.right;
                    // change oriRight as left child of newNode
                    newNode.left = oriRight;
                    curr.right = newNode;
                    list.add(copyATree(root));

                    // then restore the origin structure!
                    curr.right = oriRight;
                    curr = curr.right;
                }

                // 3. add the new node to the right most place
                curr.right = new TreeNode(i);
                list.add(copyATree(root));
                curr.right = null;
            }
            // update previous
            prevRes = list;
        }

        return list;
    }

    /**
     * Recursively copy a tree
     */
    private TreeNode copyATree(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode newRoot = new TreeNode(root.val);
        newRoot.left = copyATree(root.left);
        newRoot.right = copyATree(root.right);
        return newRoot;
    }

    // approach 1: recursive
    public List<TreeNode> generateTrees1(int n) {
        if (n < 1) {
            return new ArrayList<>();
        }
        return generateTressHelper(1, n);
    }

    /**
     * Only conside current root. Using recursion to get left child's all possible and right child's possible
     * and combine.
     * @return current subtree's root
     */
    public List<TreeNode> generateTressHelper(int lowerbound, int upperbound) {
        List<TreeNode> res = new ArrayList<>();
        if (lowerbound > upperbound) {
            res.add(null);
            return res;
        }

        for (int i = lowerbound; i <= upperbound; i++) {
            List<TreeNode> allLeftBst = generateTressHelper(lowerbound, i - 1);
            List<TreeNode> allRightBst = generateTressHelper(i + 1, upperbound);

            for (TreeNode left : allLeftBst) {
                for (TreeNode right : allRightBst) {
                    TreeNode root = new TreeNode(i);
                    root.left = left;
                    root.right = right;
                    res.add(root);
                }
            }
        }

        return res;
    }
}

public class UniqueBST {
    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.generateTrees(3));
    }
}