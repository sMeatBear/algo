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

    @Override
    public String toString() {
        return val + "";
    }
};

class Solution {
    public Node flatten2(Node h) {
        return h;
    }
    
    public Node flatten(Node head) {
        Node p = head;
        while (p.next != null) {
            if (p.child != null) {
                Node[] child = traverse(p.child);
                child[0].prev = p;
                child[1].next = p.next;
                p.next = child[0];
                p.child = null;
                p = child[1].next;
            } else {
                p = p.next;
            }
        }
        
        if (p.child != null) {
                Node[] child = traverse(p.child);
                child[0].prev = p;
                child[1].next = p.next;
                p.next = child[0];
                p.child = null;
                p = child[1].next;
        }
        return head;
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
                a = child[1].next;
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
            a = child[1].next;
        }
        
        res[1] = a;
        
        return res;
    }
}