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
    // approach 3:
    public int kthSmallest(int[][] matrix, int k) {
        return 0;
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
        
    }
}