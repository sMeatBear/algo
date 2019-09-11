import java.util.*;


class SolutionReverseList {
    public ListNode reverseList(ListNode head) {
        // iteratively
        Deque<ListNode> s = new ArrayDeque<ListNode>();
        // current pointer, new head, p2
        ListNode p = head, res = null, p2;
        
        while (p != null) {
            s.push(p);
            p = p.next;
        }
        
        if (!s.isEmpty()) res = s.pop();
        p2 = res;
        
        while(!s.isEmpty()) {
            p2.next = s.pop();
            p2 = p2.next;
            // ** let the end element next will be null in case in will form a loop
            p2.next = null;
        }
        
        return res;
    }
}

public class ReverseList {
    public static void main(String[] args) {
        Solution23 s = new Solution23();
        ListNode head = s.createListNode(new int[] {1, 2, 3, 4});
        SolutionReverseList sr = new SolutionReverseList();
        System.out.println(sr.reverseList(head).toString());
        
    }
}