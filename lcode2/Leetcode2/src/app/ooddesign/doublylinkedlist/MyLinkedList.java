package app.ooddesign.doublylinkedlist;

public class MyLinkedList {
    /** doubly linked list */
    static class ListNode {
        int val;
        ListNode next;
        ListNode prev;
        public ListNode(int val) {
            this.val = val;
        }
    }
    private ListNode dummy;
    private ListNode tail;
    private int size;
    
    /** Initialize your data structure here. */
    public MyLinkedList() {
        dummy = new ListNode(-1);
        tail = dummy;
    }
    
    /** Get the value of the index-th node in the linked list. If the index is invalid, return -1. */
    public int get(int index) {
        if (index < 0 || index >= size) {
            return -1;
        }
        return find(index).val;
    }
    
    /** Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list. */
    public void addAtHead(int val) {
        ListNode newNode = new ListNode(val);
        newNode.next = dummy.next;
        dummy.next = newNode;
        newNode.prev = dummy;
        if (newNode.next != null) {
            newNode.next.prev = newNode;
        } else {
            tail = newNode;
        }
        size++;
    }
    
    /** Append a node of value val to the last element of the linked list. */
    public void addAtTail(int val) {
        ListNode newNode = new ListNode(val);
        tail.next = newNode;
        newNode.prev = tail;
        tail = newNode;
        size++;
    }
    
    /** Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted. */
    public void addAtIndex(int index, int val) {
        if (index < 0 || index > size) {
            return;
        }
        if (index == 0) {
            addAtHead(val);
            return;
        }
        if (index == size) {
            addAtTail(val);
            return;
        }
        ListNode curr = find(index), prev = curr.prev;
        ListNode newNode = new ListNode(val);
        newNode.next = curr;
        curr.prev = newNode;
        prev.next = newNode;
        newNode.prev = prev;
        size++;
    }
    
    /** Delete the index-th node in the linked list, if the index is valid. */
    public void deleteAtIndex(int index) {
        if (index < 0 || index >= size) {
            return;
        }
        // if remove the tail
        if (index == size - 1) {
            tail = tail.prev;
            tail.next = null;
        } else {
            ListNode curr = find(index);
            curr.prev.next = curr.next;
            curr.next.prev = curr.prev;
        }
        size--;
    }
    
    private ListNode find(int index) {
        int offsetL = index, offsetR = size - index - 1;
        ListNode curr = null;
        if (offsetL <= offsetR) {
            curr = dummy.next;
            for (int i = 0; i < offsetL; i++, curr = curr.next) {}
        } else {
            curr = tail;
            for (int i = 0; i < offsetR; i++, curr = curr.prev) {}
        }
        return curr;
    }

    public static void main(String[] args) {
        MyLinkedList ml = new MyLinkedList();
        ml.addAtHead(1);
        ml.addAtTail(3);
        ml.addAtIndex(1, 2);
        ml.get(1);
        ml.deleteAtIndex(1);
        ml.get(1);
    }
}