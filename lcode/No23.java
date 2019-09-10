import java.util.*;

class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }

        @Override
        public String toString() {
            String str = "";
            ListNode p = this;
            while (p != null) {
                str += p.val + " ";
                p = p.next;
            }
            return str;
        }
}


class Solution23 {
    
    public ListNode mergeKLists(ListNode[] lists) {
        // empty head node
        ListNode head = new ListNode(0);
        ListNode current = head;
        
        // current element
        ListNode[] p = new ListNode[lists.length];
        boolean[] emptyCount = new boolean[lists.length];

        // initialize 
        for (int i = 0; i < lists.length; i++) {
            p[i] = lists[i];
        }
        
        int emptyLists = 0;
        
        // link list
        while(emptyLists < p.length - 1) {
            int currP = -1;
            int i = 0, min = Integer.MAX_VALUE;
            // find current min value in the list array
            for (; i < p.length; i++) {
                if (p[i] == null) {
                    if (!emptyCount[i]) {
                        emptyLists++;
                        emptyCount[i] = true;
                    }
                    continue;
                }
                if (min > p[i].val) {
                    min = p[i].val;
                    currP = i;
                }               
            }
            // link currrent min
            if (currP != -1) {
                current.next = p[currP];
                p[currP] = p[currP].next;
                current = current.next;
            }
            
        }
        
        // one or 0 list is left, add to the rear of result
        if (emptyLists == p.length - 1) {
            for (ListNode s: p) {
                if (s != null) {
                    current.next = s;
                }
            }
        }
        
        return head.next;
    }   

    
}


public class No23 {
    public static ListNode createListNode(int[] a) {
        // empty head
        ListNode head = new ListNode(-1);
        head.next = null;
        ListNode current = head;

        for (int i = 0; i < a.length; i++) {
            ListNode node = new ListNode(a[i]);
            current.next = node;
            current = current.next;
        }

        return head.next;
    }
    public static void main(String[] args) {
        Solution23 s = new Solution23();
        int[] a = {1, 4, 5};
        int[] b = {1, 3, 4};
        int[] c = {};
        ListNode[] lists = {createListNode(a), createListNode(b), createListNode(c)};
        System.out.println(s.mergeKLists(lists));
    }
}