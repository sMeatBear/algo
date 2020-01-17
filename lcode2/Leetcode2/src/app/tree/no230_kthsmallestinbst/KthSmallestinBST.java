package app.tree.no230_kthsmallestinbst;
/**
 * Definition for a binary tree node.
 */

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.

Note:
You may assume k is always valid, 1 ≤ k ≤ BST's total elements.

Example 1:

Input: root = [3,1,4,null,2], k = 1
   3
  / \
 1   4
  \
   2
Output: 1
Example 2:

Input: root = [5,3,6,2,4,null,null,1], k = 3
       5
      / \
     3   6
    / \
   2   4
  /
 1
Output: 3
Follow up:
What if the BST is modified (insert/delete operations) often and you need to find the kth smallest frequently? How would you optimize the kthSmallest routine?
  */
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

class Solution {
    // approach 2: use inorder : property left <= parent <= right
    public int kthSmallest(TreeNode root, int k) {
        List<Integer> list = new ArrayList<>();
        inorder(root, list);
        return list.get(k - 1);
    }

    // top down
    public void inorder(TreeNode t, List<Integer> list) {
        if (t != null) {
            inorder(t.left, list);
            list.add(t.val);
            inorder(t.right, list);
        }
    }

    // approach 1: use a priorityqueue
    public int kthSmallest1(TreeNode root, int k) {
        Queue<Integer> q = new PriorityQueue<>((a, b) -> (b - a));
        preorder(root, k, q);
        return q.poll();
    }

    public void preorder(TreeNode t, int k, Queue<Integer> q) {
        if (t != null) {
            int val = t.val;
            q.offer(val);
            if (q.size() > k) q.poll();
            preorder(t.left, k, q);
            preorder(t.right, k, q);
        }
    }
}

public class KthSmallestinBST {
    public static void main(String[] args) {
        
    }
}