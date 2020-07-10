package app.tree.no662_maxwidthbt;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import app.tree.TreeNode;

/**
 * Given a binary tree, write a function to get the maximum width of the given tree. The width of a tree is the maximum width among all levels. The binary tree has the same structure as a full binary tree, but some nodes are null.

The width of one level is defined as the length between the end-nodes (the leftmost and right most non-null nodes in the level, where the null nodes between the end-nodes are also counted into the length calculation.

Example 1:

Input: 

           1
         /   \
        3     2
       / \     \  
      5   3     9 

Output: 4
Explanation: The maximum width existing in the third level with the length 4 (5,3,null,9).
Example 2:

Input: 

          1
         /  
        3    
       / \       
      5   3     

Output: 2
Explanation: The maximum width existing in the third level with the length 2 (5,3).
Example 3:

Input: 

          1
         / \
        3   2 
       /        
      5      

Output: 2
Explanation: The maximum width existing in the second level with the length 2 (3,2).
Example 4:

Input: 

          1
         / \
        3   2
       /     \  
      5       9 
     /         \
    6           7
Output: 8
Explanation:The maximum width existing in the fourth level with the length 8 (6,null,null,null,null,null,null,7).


Note: Answer will in the range of 32-bit signed integer.
 */

class Solution {
    // approach 2: find all leftmost nodes index and rightmost nodes index (wrong)
    public int widthOfBinaryTree2(TreeNode root) {
        // *each layer's leftmost node index and rightmost index
        // left = parent * 2; right = parent * 2 + 1;
        List<Integer> leftIndex = new ArrayList<>(), rightIndex = new ArrayList<>();
        int width = 1;
        TreeNode curr = root;
        int currIndex = 0;

        // fill the left
        while (curr != null) {
            leftIndex.add(currIndex);
            // go left if it can
            if (curr.left != null) {
                curr = curr.left;
                currIndex *= 2;
            } else {
                curr = curr.right;
                currIndex = currIndex * 2 + 1;
            }
        }

        // fill the right
        curr = root;
        currIndex = 0;
        while (curr != null) {
            rightIndex.add(currIndex);
            if (curr.right != null) {
                curr = curr.right;
                currIndex = currIndex * 2 + 1;
            } else {
                curr = curr.left;
                currIndex *= 2;
            }
        }


        // compare layer by layer
        for (int i = 0; i < leftIndex.size() && i < rightIndex.size(); i++) {
            width = Math.max(width, rightIndex.get(i) - leftIndex.get(i) + 1);
        }

        return width;
    }

    // approach 1: iterative (level order TLE) -> do not count the null (tc: O(n))
    public int widthOfBinaryTree(TreeNode root) {
        if (root == null) {return 0;}
        // find the leftmost and rightmost leaft node's no. in each layer and find the largest
        // level order
        int width = 1;
        Deque<Object[]> queue = new ArrayDeque<>();
        queue.offer(new Object[] {root, 0});
        
        // 1. find the leftmost node
        while (!queue.isEmpty()) {
            int layerSize = queue.size(), left = 0, right = 0;
            // record the first polled element's index
            if (layerSize > 0) {
                Object[] curr = queue.poll();
                TreeNode currNode = (TreeNode) curr[0];
                int index = (int) curr[1];
                left = right = index;
                if (currNode.left != null) {queue.offer(new Object[] {currNode.left, index * 2});}
                if (currNode.right != null) {queue.offer(new Object[] {currNode.right, index * 2 + 1});}
            }
            for (int i = 1; i < layerSize - 1; i++) {
                Object[] curr = queue.poll();
                TreeNode currNode = (TreeNode) curr[0];
                int index = (int) curr[1];
                if (currNode.left != null) {queue.offer(new Object[] {currNode.left, index * 2});}
                if (currNode.right != null) {queue.offer(new Object[] {currNode.right, index * 2 + 1});}
            }
            // rightmost node
            if (layerSize > 1) {
                Object[] curr = queue.poll();
                TreeNode currNode = (TreeNode) curr[0];
                int index = (int) curr[1];
                right = index;
                if (currNode.left != null) {queue.offer(new Object[] {currNode.left, index * 2});}
                if (currNode.right != null) {queue.offer(new Object[] {currNode.right, index * 2 + 1});}
            }
            width = Math.max(width, right - left + 1);
        }
        
        return width;
    }
}

public class MaxWidthBinaryTree {
    public static void main(String[] args) {
        Integer[] nums = new Integer[] {1,3,2,5,3,null,9,9};
        TreeNode root = TreeNode.buildTreeInLvlOrder(nums);
        Solution sol = new Solution();
        System.out.println(sol.widthOfBinaryTree(root));
    }
}