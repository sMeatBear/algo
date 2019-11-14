package app.backtracking;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

class SolutionGenerate {
    // backtracking method
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
    
    public List<String> generateParenthesis1(int n) {
        // result
        List<String> res = new ArrayList<String>();
        Deque<Character> s = new ArrayDeque<Character>();
        // // parenthesis pair
        // Map<String, String> pair = new HashMap<String, String>();
        // pair.put("(", ")");
        pushParenthesis(s, "", n * 2, '(', res);
        
        
        return res;
    }
    
    // iterating
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        if (n < 2) return res;
        
        
        // backtracking without recursion
        // from first element
        int i = 0;

        char[] comb = new char[n * 2];
        
        while(i >= 0) {
            // ' ' -> ( -> ) for each layer
            if (comb[i] == '\u0000') {
                comb[i] = '(';
            } else if (comb[i] == '(') {
                comb[i] = ')';
            } else {
                // backtrack
                comb[i] = '\u0000';
                i--;
                continue;
            }
            
            // if this is the last layer and it works
            if (i == n * 2 - 1 && isValid(comb)) {
                res.add(new String(comb));
            } else if (i < n * 2 - 1) {
                // move to next layer
                i++;
            }
            
        }
        
        return res;
    }
    
    public boolean isValid(char[] comb) {
        Deque<Character> s = new ArrayDeque<>();
        
        // check parentheses one by one
        for (char p : comb) {
            if (p == '(') s.push(p);
            else {
                // ) matches with (
                if (s.isEmpty() || s.pop() != '(') return false;
            }
        }
        
        if (s.isEmpty()) return true;
        else return false;
    }
}

public class GenerateParentheses {
    public static void main(String[] args) {
        SolutionGenerate s = new SolutionGenerate();

        List<String> res = s.generateParenthesis(3);

        System.out.println(res);
    }
}