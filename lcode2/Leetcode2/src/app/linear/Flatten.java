package app.linear;

// Definition for a Node.
class Node {
    public int val;
    public Node prev;
    public Node next;
    public Node child;

    public Node() {}

    public Node(int _val,Node _prev,Node _next,Node _child) {
        val = _val;
        prev = _prev;
        next = _next;
        child = _child;
    }

    public void init(int _val,Node _prev,Node _next,Node _child) {
        val = _val;
        prev = _prev;
        next = _next;
        child = _child;
    }

    @Override
    public String toString() {
        return val + "" + " pre: " + prev;
    }
};

class Solution {
    public Node flatten(Node head) {
        if (head == null) return head;
        Node[] res = traverse(head);
        return res[0];
    }
    
    public Node[] traverse(Node a) {
        Node[] res = new Node[2];
        Node head = a;
        res[0] = head;
        while (a.next != null) {
            if (a.child != null) {
                // store head and rear
                Node[] child = traverse(a.child);
                child[0].prev = a;
                child[1].next = a.next;
                a.next = child[0];
                a.child = null;
                // origial a next's prev should point to child[1]
                child[1].next.prev = child[1];
            } else {
                a = a.next;
            }
        }
        // check the last element
        if (a.child != null) {
             // store head and rear
            Node[] child = traverse(a.child);
            child[0].prev = a;
            child[1].next = a.next;
            a.next = child[0];
            a.child = null;
            a = child[1];
        }
        
        res[1] = a;
        
        return res;
    }
}

public class Flatten {
    public static void main(String[] args) {
        Node n1 = new Node(), n2 = new Node(), n3 = new Node(), n4 = new Node(), n5 = new Node(),
            n6 = new Node(), n7 = new Node();
        n1.init(1, null, n2, null);
        n2.init(2, n1, n5, n3);
        n3.init(3, null, n4, null);
        n4.init(4, n3, null, null);
        n5.init(5, n2, null, n6);
        n6.init(6, null, n7, null);
        n7.init(7, n6, null, null);

        Solution s = new Solution();
        Node h = s.flatten(n1);
        // Node h = n1;
        while (h != null) {
            System.out.println(h);
            h = h.next;
        }
    }
}