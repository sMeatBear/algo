package app.linear;


/**
 * Your MyLinkedList object will be instantiated and called as such:
 * MyLinkedList obj = new MyLinkedList();
 * int param_1 = obj.get(index);
 * obj.addAtHead(val);
 * obj.addAtTail(val);
 * obj.addAtIndex(index,val);
 * obj.deleteAtIndex(index);
 */
class MyLinkedList {
    private class Node {
        private int val;
        private Node next;
    }

    private Node head;
    private Node rear;
    private int size;

    /** @return list size */
    public int size() {
        return this.size;
    }

    /** Initialize your data structure here. */
    public MyLinkedList() {
        head = new Node();
        rear = head;
    }
    
    /** Get the value of the index-th node in the linked list. If the index is invalid, return -1. */
    public int get(int index) {
        if (index >= size || index < 0) return -1;
        // current node
        Node node = head.next;

        for (int i = 0; i < index; i++) {
            node = node.next;
        }

        return node.val;
    }
    
    /** Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list. */
    public void addAtHead(int val) {
        Node node = new Node();
        node.val = val;
        node.next = head.next;
        head.next = node;

        // size ++
        if (++size == 1) rear = node;
    }
    
    /** Append a node of value val to the last element of the linked list. */
    public void addAtTail(int val) {
        Node node = new Node();
        node.val = val;
        rear.next = node;

        size++;
        rear = node;
    }
    
    /** Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted. */
    public void addAtIndex(int index, int val) {
        // error cases
        if (index > size) index = size;
        else if (index < 0) index = 0;

        // prevent rear cannot be updated
        if (index == 0) {
            addAtHead(val);
            return;
        }
        // rear
        if (index == size) {
            addAtTail(val);
            return;
        }

        // current node
        Node node = head.next;

        // find the pos
        for (int i = 0; i < index - 1; i++) {
            node = node.next;
        }

        // add
        Node newNode = new Node();
        newNode.val = val;
        newNode.next = node.next;
        node.next = newNode;
        size++;
    }
    
    /** Delete the index-th node in the linked list, if the index is valid. */
    public void deleteAtIndex(int index) {
        if (index < 0 || index >= size) return;

        Node node = head;

        // delete rear
        if (index == size - 1) {
            for (int i = -1; i < index - 1; i++) {
                node = node.next;
            }
            node.next = null;
            rear = node;
            size--;
            return; 
        } 

        for (int i = -1; i < index - 1; i++) {
            node = node.next;
        }

        node.next = node.next.next;
        size--;
        return;
    }
}


public class MyLinked {

    public static void main(String[] args) {
        MyLinkedList list = new MyLinkedList();
        list.addAtHead(0);
        list.addAtIndex(1, 9);
        list.addAtIndex(1, 5);
        list.addAtTail(7);
        list.addAtHead(1);
        list.addAtIndex(5, 8);
        System.out.println(list.get(0));
    }
}