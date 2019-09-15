import java.util.*;

class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) return "";
        // level order serialize
        LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
        String res = "";
        
        queue.add(root);
        // use queue to serialize
        while (!queue.isEmpty()) {
            // deque first node
            TreeNode cur = null;
            try {
                cur = queue.remove();
            } catch (Exception e) {
                break;
            }
            // if emtpy node
            if (cur == null) {
                res += "null" + ",";
                continue;
            } else res += cur.val + ",";
            // enque children
            queue.add(cur.left);
            queue.add(cur.right);
        }
        
        // remove last layer's null
        String[] tmp = res.split(",");
        int i;
        for (i = tmp.length - 1; tmp[i].equals("null"); i--) {
        }
        res = "[";
        for (int j = 0; j <= i; j++) {
            res += tmp[j] + ","; 
        }
        res = res.substring(0, res.length() - 1) + "]";
        
        // stdout result
        System.out.println(res);
        
        return res;
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        // level order decodes
        // handle string data
        data = data.substring(1, data.length() - 1);
        // empty tree
        if (data.length() == 0) return null;
        String[] dArr = data.split(",");
        
        // reconstruct tree
        TreeNode root = new TreeNode(Integer.parseInt(dArr[0]));
        LinkedList<TreeNode> curLayer = new LinkedList<TreeNode>();
        curLayer.add(root);
        
        for (int i = 1; i < dArr.length; i += 2) {
            // deque
            TreeNode current = curLayer.poll();
            if (!dArr[i].equals("null")) {
                current.left = new TreeNode(Integer.parseInt(dArr[i]));
                curLayer.add(current.left);
            }
            if (i + 1 < dArr.length && !dArr[i + 1].equals("null")) {
                current.right = new TreeNode(Integer.parseInt(dArr[i + 1]));
                // add child to current layer
                curLayer.add(current.right);
            }
        }
        
        return root;
    }
}


public class Serialize {
    public static void main(String[] args) {
        Codec s = new Codec();
        System.out.println(s.deserialize("[]"));
    }
}