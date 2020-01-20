package app.search.binary.no69_sqrt;

class Solution {
    // Approach 1: binary search
    public int mySqrt(int x) {
        int l = 0, r = x;
        while (l < r) {
            // middle
            int m = (l + r) / 2;
            long prod = (long)m * m;
            if (prod < x) l = m + 1;
            else r = m;
        }

        // two kind of possible
        long prod = (long)l * l;
        // equal
        if (prod == x) return l;
        // prod is greater than x
        return l - 1;
    }
}

public class Sqrt {
    public static void main(String[] args) {
        Solution s = new Solution();
        int res = s.mySqrt(2147395599);
        System.out.println(res);
    }
}