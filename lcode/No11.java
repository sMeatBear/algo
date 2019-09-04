class Solution {
    public int maxArea(int[] height) {
        // brute force: O(n^2)
        int max = 0;
        for (int i = 0; i < height.length; i++) {
            for (int j = i + 1; j < height.length; j++) {
                max = Math.max(max, Math.min(height[i], height[j]) * (j - i));
            }
        }
        return max;
    }

    // O(n)
    public int advancedMax(int[] height) {
        int max = 0;
        int p1 = 0, p2 = height.length - 1;
        int interval = p2 - p1;
        while (interval > 0) {
            max = Math.max(max, interval * Math.min(height[p1], height[p2]));
            // always move short column
            // the two points from the two sides, so the short col always get the 
            // its further place to get max value
            if(height[p2]> height[p1]) p1++; 
            else p2--;
            interval = p2 - p1;
        }
        return max;
    }
}
public class No11 {

    public static void main(String[] args) {
        // No11 s = new No11();
        int[] height = {2,3,4,5,18,17,6};
        int res = new Solution().advancedMax(height);
        System.out.println(res);
    }
}


