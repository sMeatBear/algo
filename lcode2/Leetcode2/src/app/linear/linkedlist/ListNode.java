package app.linear.linkedlist;

public class ListNode {
    public int val;
    public ListNode next;
    public ListNode(int x) { val = x; }
    public static ListNode toList(int[] nums) {
        if (nums == null) {
            return null;
        }
        
        ListNode head = new ListNode(-1), p = head;
        for (int num : nums) {
            ListNode newNode = new ListNode(num);
            p.next = newNode;
            p = p.next;
        }

        return head.next;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        ListNode p = this;
        while (p.next != null) {
            sb.append(p.val).append("->");
            p = p.next;
        }

        return sb.append(p.val).toString();
    }
}