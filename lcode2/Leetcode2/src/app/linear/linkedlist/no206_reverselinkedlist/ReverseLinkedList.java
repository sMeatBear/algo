package app.linear.linkedlist.no206_reverselinkedlist;

import app.linear.linkedlist.ListNode;

/**
 * Reverse a singly linked list.

Example:

Input: 1->2->3->4->5->NULL
Output: 5->4->3->2->1->NULL
Follow up:

A linked list can be reversed either iteratively or recursively. Could you implement both?
 */
class Solution {
    // approach 2: no dummy
    public ListNode reverseList(ListNode head) {
        if (head == null) return head;
        ListNode tail = head, p = tail.next;
        
        // fix the head node
        while (p != null) {
            // skip the middle node
            // must use tail to update next domain otherwise 1-2-1 will form a loop
            tail.next = p.next;
            // insert the node to the head
            p.next = head;
            // update head
            head = p;
            // move p to next
            p = tail.next;
        }
        
        return head;
    }
    
    // approach 1: dummy
    public ListNode reverseList1(ListNode head) {
        ListNode dummy = new ListNode(-1);
        
        while (head != null) {
            ListNode next = head.next;
            head.next = dummy.next;
            dummy.next = head;
            head = next;
        }
        
        return dummy.next;
    }
}

public class ReverseLinkedList {

}