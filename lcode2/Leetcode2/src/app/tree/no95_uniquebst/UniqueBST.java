package app.tree.no95_uniquebst;

import java.util.ArrayList;
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
    public List<TreeNode> generateTrees(int n) {
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