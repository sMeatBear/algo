package app.array.no918_maxsumcircularsub;

/**
 * 
 *Given a circular array C of integers represented by A, find the maximum possible sum of a non-empty subarray of C.

Here, a circular array means the end of the array connects to the beginning of the array.  (Formally, C[i] = A[i] when 0 <= i < A.length, and C[i+A.length] = C[i] when i >= 0.)

Also, a subarray may only include each element of the fixed buffer A at most once.  (Formally, for a subarray C[i], C[i+1], ..., C[j], there does not exist i <= k1, k2 <= j with k1 % A.length = k2 % A.length.)

 

Example 1:

Input: [1,-2,3,-2]
Output: 3
Explanation: Subarray [3] has maximum sum 3
Example 2:

Input: [5,-3,5]
Output: 10
Explanation: Subarray [5,5] has maximum sum 5 + 5 = 10
Example 3:

Input: [3,-1,2,-1]
Output: 4
Explanation: Subarray [2,-1,3] has maximum sum 2 + (-1) + 3 = 4
Example 4:

Input: [3,-2,2,-3]
Output: 3
Explanation: Subarray [3] and [3,-2,2] both have maximum sum 3
Example 5:

Input: [-2,-3,-1]
Output: -1
Explanation: Subarray [-1] has maximum sum -1
 

Note:

-30000 <= A[i] <= 30000
1 <= A.length <= 30000
Accepted
 */

class Solution {
    // Approach 2: Two situation: O(n)
    public int maxSubarraySumCircular(int[] A) {
        int res = Integer.MIN_VALUE;
        int dp = Integer.MIN_VALUE;
        
        int totalSum = 0;
        for (int a : A) {
            totalSum += a;
        }
        
        int max1 = commonMaxSubarr(A, 0, A.length, 1);
        int max2 = totalSum + commonMaxSubarr(A, 1, A.length - 1, -1);
        
        return Math.max(max1, max2);
    }
    
    public int commonMaxSubarr(int[] A, int start, int end, int sign) {
        if (start >= end) {
            return 0;
        }
        int max = Integer.MIN_VALUE;
        int prevSum = Integer.MIN_VALUE;
        for (int i = start; i < A.length; i++) {
            prevSum = sign * A[i] + Math.max(0, prevSum);
            // update sum
            max = Math.max(max, prevSum);
        }
        return max;
    }

    // Approach 1: bf + dp O(n^2) failed
    public int maxSubarraySumCircular1(int[] A) {
        int max = Integer.MIN_VALUE;
        for (int start = 0; start < A.length; start++) {
            int dp = Integer.MIN_VALUE;
            int i = start;
            do {
                dp = dp > 0 ? dp + A[i] : A[i];
                max = Math.max(dp, max);
                i = (i + 1) % A.length;
            } while(i != start);
        }
        
        return max;
    }
}

public class MaxSumCircularSub {
    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] A = new int[] {-5,3,5};
        int res = sol.maxSubarraySumCircular(A);
        System.out.println(res);
    }    
}