package app.linear.no957_prisoncells;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {
    // approach 1: brute force (time limit exceeded -> add a set)
    public int[] prisonAfterNDays(int[] cells, int N) {
        Map<Integer, Integer> map = new HashMap<>();
        boolean unchecked = true;
        for (int i = 0; i < N; i++) {
            int base = 10;
            int hash = cells[0];
            int prev = cells[0];
            for (int j = 1; j < cells.length - 1; j++, base *= 10) {
                int temp = cells[j];
                cells[j] = prev == cells[j + 1] ? 1 : 0;
                prev = temp;
                hash += cells[j] * base;
            }
            cells[0] = 0;
            cells[cells.length - 1] = 0;
            hash += cells[cells.length - 1] * base;
            // check loop
            if (unchecked) {
                Integer day = map.get(hash);
                if (day == null) {
                    map.put(hash, i);
                } else {
                    //==================== N - 1 - day is the offset from 'day'======================
                    int loopSize = i - day;
                    int offset = (N - 1- day) % loopSize;
                    // ==============================================
                    N = offset;
                    // *to counteract i++
                    i = -1;
                    unchecked = false;
                }
            }
        }
        return cells;
    }
}

public class PrisonCells {
    public static void main(String[] args) {
        Solution sol = new Solution();
        int N = 99;
        int[] cells = new int[] {1,0,0,0,1,0,0,1};
        // int[] cells = new int[] {0,0,1,1,1,0,1,0};
        String res = Arrays.toString(sol.prisonAfterNDays(cells, N));
        System.out.println(res);
    }
}