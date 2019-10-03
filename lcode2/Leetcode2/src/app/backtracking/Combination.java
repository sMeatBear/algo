package app.backtracking;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

class SolutionCombination {
    Deque<Integer> deque = new ArrayDeque<>();
    List<List<Integer>> res = new ArrayList<>();
    
    
    public List<List<Integer>> combine(int n, int k) {
       combine(1, n, k, 0);
       return res;
    }
    
    public void combine(int i, int n, int k, int lastVal) {
        for (int val = lastVal + 1; val <= n; val++) {
            deque.add(val);
            if (i == k) {
                List<Integer> one = new ArrayList<>(deque);
                res.add(one);
            } else {
                combine(i + 1, n, k, val);
            }

            deque.removeLast();
        }
    }
}

public class Combination {
    public static void main(String[] args) {
        SolutionCombination s = new SolutionCombination();
        int n = 4, k = 2;
        List<List<Integer>> res = s.combine(n, k);
        System.out.println(res);
    }
}