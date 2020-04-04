package app.tree.no987_verticalordertraversal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.tree.TreeNode;

/**
Given a binary tree, return the vertical order traversal of its nodes values.

For each node at position (X, Y), its left and right children respectively will be at positions (X-1, Y-1) and (X+1, Y-1).

Running a vertical line from X = -infinity to X = +infinity, whenever the vertical line touches some nodes, we report the values of the nodes in order from top to bottom (decreasing Y coordinates).

If two nodes have the same position, then the value of the node that is reported first is the value that is smaller.

Return an list of non-empty reports in order of X coordinate.  Every report will have a list of values of nodes.

 

Example 1:



Input: [3,9,20,null,null,15,7]
Output: [[9],[3,15],[20],[7]]
Explanation: 
Without loss of generality, we can assume the root node is at position (0, 0):
Then, the node with value 9 occurs at position (-1, -1);
The nodes with values 3 and 15 occur at positions (0, 0) and (0, -2);
The node with value 20 occurs at position (1, -1);
The node with value 7 occurs at position (2, -2).
Example 2:



Input: [1,2,3,4,5,6,7]
Output: [[4],[2],[1,5,6],[3],[7]]
Explanation: 
The node with value 5 and the node with value 6 have the same position according to the given scheme.
However, in the report "[1,5,6]", the node value of 5 comes first since 5 is smaller than 6.
 

Note:

The tree will have between 1 and 1000 nodes.
Each node's value will be between 0 and 1000.
*/

/**
 * Definition for a binary tree node. public class TreeNode { int val; TreeNode
 * left; TreeNode right; TreeNode(int x) { val = x; } }
 */
class Solution {
    // Aporoach 2: levelorder traverse with treemap and without recursion
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        // Use Tree Map to store the position of each node
        // <Position, nodes val>
        Map<Integer, List<Integer>> totalpos = new TreeMap<>();
        
        // Use recursion to do preorder traverse
        // Levelorder traverse to add node to hash map: top-down, left to right !!
        // Levelorder can keep the node in the same position in depth ascending order
        // Use another idxs list to store each nodes position !!
        List<TreeNode> next = new ArrayList<>();
        List<Integer> idxs = new ArrayList<>();
        next.add(root);
        idxs.add(0);
        
        while (!next.isEmpty()) {
            // new next list
            List<TreeNode> newNext = new ArrayList<>();
            List<Integer> newIdxs = new ArrayList<>();
            
            // To store this lvl's node position which is easy to sort !!!
            Map<Integer, List<Integer>> pos = new HashMap<>();
            for (int i = 0; i < next.size(); i++) {
                TreeNode t = next.get(i);
                int idx = idxs.get(i);
                
                // Is current node a new position?
                List<Integer> posList;
                if (pos.containsKey(idx)) {
                    // Get exist position list
                    posList = pos.get(idx);
                } else {
                    // Create new position list and add it to the map
                    posList = new ArrayList<>();
                    pos.put(idx, posList);
                }
                // Add new node to the position list
                posList.add(t.val);
                
                // Generate next lvl's info
                if (t.left != null) {
                    newNext.add(t.left);
                    newIdxs.add(idx - 1);
                }   
                if (t.right != null) {
                    newNext.add(t.right);
                    newIdxs.add(idx + 1);
                }
            }
            
            // Combine pos and totalpos
            for (Map.Entry<Integer, List<Integer>> p : pos.entrySet()) {
                List<Integer> list = p.getValue();
                int idx = p.getKey();
                Collections.sort(list);
                
                if (totalpos.containsKey(idx)) {
                    totalpos.get(idx).addAll(list);
                } else {
                    totalpos.put(idx, list);
                }
            }
            
            // Update next and idxs
            next = newNext;
            idxs = newIdxs;
        }
        
        // Fill the result list
        List<List<Integer>> res = new ArrayList<>();
        for (List<Integer> posList : totalpos.values()) {
            res.add(posList);
        }
        
        return res;    
    }
    
    
    // Approach 1: levelorder traverse the whole binary tree and store with TreeMap
    public List<List<Integer>> verticalTraversal1(TreeNode root) {
        // Use Tree Map to store the position of each node
        // <Position, nodes val>
        Map<Integer, List<Integer>> pos = new TreeMap<>();
        // Initial index
        int idx = 0;
        
        // Use recursion to do preorder traverse
        // Levelorder traverse to add node to hash map: top-down, left to right !!
        // Levelorder can keep the node in the same position in depth ascending order
        // Use another idxs list to store each nodes position !!
        List<TreeNode> next = new ArrayList<>();
        List<Integer> idxs = new ArrayList<>();
        next.add(root);
        idxs.add(idx);
        levelorder(next, idxs, pos);
        
        // Fill the result list
        List<List<Integer>> res = new ArrayList<>();
        for (List<Integer> posList : pos.values()) {
            res.add(posList);
        }
        
        return res;
    }
    
    public void levelorder(List<TreeNode> next, List<Integer> idxs, Map<Integer, List<Integer>> totalpos) {
        if (!next.isEmpty()) {
            // new next list
            List<TreeNode> newNext = new ArrayList<>();
            List<Integer> newIdxs = new ArrayList<>();
            
            // To store this lvl's node position which is easy to sort !!!
            Map<Integer, List<Integer>> pos = new HashMap<>();
            for (int i = 0; i < next.size(); i++) {
                TreeNode t = next.get(i);
                int idx = idxs.get(i);
                
                // Is current node a new position?
                List<Integer> posList;
                if (pos.containsKey(idx)) {
                    // Get exist position list
                    posList = pos.get(idx);
                } else {
                    // Create new position list and add it to the map
                    posList = new ArrayList<>();
                    pos.put(idx, posList);
                }
                // Add new node to the position list
                posList.add(t.val);
                
                // Generate next lvl's info
                if (t.left != null) {
                    newNext.add(t.left);
                    newIdxs.add(idx - 1);
                }   
                if (t.right != null) {
                    newNext.add(t.right);
                    newIdxs.add(idx + 1);
                }
            }
            
            // Combine pos and totalpos
            for (Map.Entry<Integer, List<Integer>> p : pos.entrySet()) {
                List<Integer> list = p.getValue();
                int idx = p.getKey();
                Collections.sort(list);
                
                if (totalpos.containsKey(idx)) {
                    totalpos.get(idx).addAll(list);
                } else {
                    totalpos.put(idx, list);
                }
            }
            
            // Do next level
            levelorder(newNext, newIdxs, totalpos);
        }
    }
}

public class VerticalOrderTraversal {

}