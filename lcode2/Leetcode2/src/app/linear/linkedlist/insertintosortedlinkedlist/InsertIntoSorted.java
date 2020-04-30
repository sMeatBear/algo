package app.linear.linkedlist.insertintosortedlinkedlist;

import app.linear.linkedlist.ListNode;

public class InsertIntoSorted {
    public static ListNode insertIntoSorted(ListNode head, int target) {
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;
        dummy.next = head;

        // find the last element which is less than target
        while (cur.next != null && cur.next.val < target) {
            cur = cur.next;
        }

        // insert
        ListNode newNode = new ListNode(target);
        newNode.next = cur.next;
        cur.next = newNode;

        return dummy.next;
    }

    public static void main(String[] args) {
        int[] nums = new int[] {1,4,5,7,10,23,138};
        ListNode head = ListNode.toList(nums);
        
        System.out.println(insertIntoSorted(head, 5));
    }
}