// package Memoization;

import java.util.*;


public class MemoFib {
    private Map<Integer, Integer> cache = new HashMap<Integer, Integer>();

    public int fib (int index) {
        if (cache.containsKey(index)) {
            return cache.get(index);
        }
        if (index < 0) throw new IllegalArgumentException("index under 0");

        if (index < 2) {
            return index;
        }

        int result = fib(index - 1) + fib(index - 2); 
        cache.put(index, result);
        return result;
    }

    public static void main(String[] args) {
        MemoFib m = new MemoFib();
        System.out.println(m.fib(-1));
        
    }
}