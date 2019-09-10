import java.util.*;


class Solution22 {
    // recursive method
    public void pushParenthesis(Deque<Character> s, String str, int count, char ch, List<String> res) {
        // exit
        if (count == 1) {
            // add new left bracket is invalid
            // only one element left is allowed
            if (ch == '(' || s.size() != 1) return;
            
            res.add(str + ')');
            return;
        }
        
        // not terminal state push or pop top element to compare
        if (ch == '(') {
            s.push(ch);
        } else {
            // stack is empty or top element cannot pair with each other
            if (s.isEmpty() || s.pop() != '(') return;
        }
        // recursive
        Deque<Character> s1 = new ArrayDeque<Character>(s);
        Deque<Character> s2 = new ArrayDeque<Character>(s);

        pushParenthesis(s1, str + ch, count - 1, '(', res);
        pushParenthesis(s2, str + ch, count - 1, ')', res);
        
    }
    
    public List<String> generateParenthesis(int n) {
        // result
        List<String> res = new ArrayList<String>();
        Deque<Character> s = new ArrayDeque<Character>();
        // // parenthesis pair
        // Map<String, String> pair = new HashMap<String, String>();
        // pair.put("(", ")");
        pushParenthesis(s, "", n, '(', res);
        
        
        return res;
    }
}

public class No22 {
    public static void main(String[] args) {
        Solution22 s = new Solution22();
        System.out.println(s.generateParenthesis(6));
        
    }
}