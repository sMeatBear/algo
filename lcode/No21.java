class ListNode {
    int val;
    ListNode next;

    public ListNode(int val) {
        this.val = val;
    }
}

class Solution21 {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        // build an empty head node which is for convenience
        ListNode head = new ListNode(0);
        ListNode p = head;
        
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                p.next = l1;
                l1 = l1.next;
            } else {
                p.next = l2;
                l2 = l2.next;
            }
            p = p.next;
        }
        
        // add non empty list
        p.next = l1 == null ? l2 : l1;
        return head.next;
    }
}

public class No21 {
    public static void main(String[] args) {
        
    }
}