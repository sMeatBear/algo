import java.util.*;


class Solution117 {
       // improved method
    // @param root      first element of each layer 
    public void connect2(Node root) {
        // first node in current layer 
        Node head = root;  
        // record previous node in next layer
        Node pre = null;   
        // current node
        Node curr = null;   
        
        // connect
        while (head != null) {
            curr = head;
                            
            // move rightward till end
            while (curr != null) {
                // connect left and right child
                if (curr.left != null) {
                    if (pre != null) {
                        pre.next = curr.left;
                        pre = pre.next;
                    } else {
                        // next layer first node
                        head = curr.left;
                        pre = curr.left;
                    }
                }
                
                if (curr.right != null) {
                    if (pre != null) {
                        pre.next = curr.right;
                        pre = pre.next;
                    } else {
                        head = curr.right;
                        pre = curr.right;
                    } 
                }
                curr = curr.next;
            }
        }
        
    
    }
    
    public Node connect(Node root) {
        // List<Node> res = new ArrayList<Node>();
        // res.add(root);
        // traverse(res);
        connect2(root);
        return root;
    }
}

public class No117 {
    public static void main(String[] args) {
        Deque<String> q = new ArrayDeque<String>();
        LinkedList<String> a = new LinkedList<String>();
        a.add(null);
        System.out.println(a.poll());
        try {
            System.out.println("caoni ma java");
            int i = 1 / 0;
        } catch (Exception e) {
            //TODO: handle exception
            e.printStackTrace();
        }
    }
}