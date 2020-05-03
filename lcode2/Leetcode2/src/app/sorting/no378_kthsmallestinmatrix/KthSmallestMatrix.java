package app.sorting.no378_kthsmallestinmatrix;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Given a n x n matrix where each of the rows and columns are sorted in
 * ascending order, find the kth smallest element in the matrix.
 * 
 * Note that it is the kth smallest element in the sorted order, not the kth
 * distinct element.
 * 
 * Example:
 * 
 * matrix = [ [ 1, 5, 9], [10, 11, 13], [12, 13, 15] ], k = 8,
 * 
 * return 13. Note: You may assume k is always valid, 1 ≤ k ≤ n2.
 */
class Solution {
    // approach 3: Dijkstra
    public int kthSmallest(int[][] matrix, int k) {
        // coner case
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return Integer.MIN_VALUE;
        }
        // Use a min heap to realize Dijkstra search (Best First Search (UCS))
        // store <row, col, value>
        // min heap
        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> {
            if (a[2] < b[2]) {
                return -1;
            } else {
                return 1;
            }
        });
        boolean[][] visited = new boolean[matrix.length][matrix.length];
        int count = 0;
        int[] curr = null;
        queue.offer(new int[]{0, 0, matrix[0][0]});
        while (count < k) {
            curr = queue.poll();
            int newRow = curr[0] + 1, newCol = curr[1] + 1;
            // find next down and right and add to queue
            if (newRow < matrix.length && !visited[newRow][curr[1]]) {
                queue.offer(new int[] {newRow, curr[1], matrix[newRow][curr[1]]});
                visited[newRow][curr[1]] = true;
            }
            if (newCol < matrix.length && !visited[curr[0]][newCol]) {
                queue.offer(new int[] {curr[0], newCol, matrix[curr[0]][newCol]});
                visited[curr[0]][newCol] = true;
            }
            count++;
        }
        return curr[2];
    }

    // approach 2: n pointers using queue
    public int kthSmallest2(int[][] matrix, int k) {
        // record row, col, val
        int i = 0;
        int[] res = new int[3];
        // store each row's pointer
        Queue<int[]> q = new PriorityQueue<>((a, b) -> {
            int diff = a[2] - b[2];
            // compare value of the matrix
            if (diff != 0) return diff;
            // if same, then row number
            return a[0] - b[0];
        });

        // add all rows first element
        for (int r = 0; r < matrix.length; r++) {
            q.offer(new int[] {r, 0, matrix[r][0]});
        }

        // k iteration
        while (i < k) {
            int[] ith = q.poll();
            // next element to compare
            // If c is beyond the bound, then set val to max_int
            int r = ith[0], c = ith[1] + 1, val = c < matrix[0].length ? matrix[r][c] : Integer.MAX_VALUE;
            res = new int[] {r, c, val};
            q.offer(res);
            i++;
        }

        return matrix[res[0]][res[1] - 1];
    }

    // approach 1: n pointers
    public int kthSmallest1(int[][] matrix, int k) {
        int[] ptrs= new int[matrix.length];
        // ith smallest element (from 0 to k - 1)
        int i = 0, resRow = 0;
        while (i < k) {
            resRow = getSmallestIdx(ptrs, matrix);
            ptrs[resRow]++;
            i++;
        }

        // !! mind ptrs[resRow] - 1 means previous compared smallest number
        return matrix[resRow][ptrs[resRow] - 1];
    }

    public int getSmallestIdx(int[] ptrs, int[][] matrix) {
        int min = Integer.MAX_VALUE, idx = 0;
        for (int i = 0; i < ptrs.length; i++) {
            int j = ptrs[i];
            if (j < matrix[0].length && min > matrix[i][j]) {
                min = matrix[i][j];
                idx = i;
            }
        }

        return idx;
    }
}

public class KthSmallestMatrix {
    public static void main(String[] args) {
        int[][] matrix = new int[][] {{1,3,5},{6,7,12},{11,14,14}};
        Solution sol = new Solution();
        int res = sol.kthSmallest(matrix, 6);
        System.out.println(res);
    }
}