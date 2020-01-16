package app.array.no42_trappingrain;

import java.util.ArrayList;
import java.util.List;

class Solution {
    // approach 2: two points
    public int trap(int[] height) {
        int l = 0, r = height.length-1, waterlevel = 0, trap = 0;
        // from start and end to middle
        while (l < r) {
            // the lower bar always can hold the water which is <= lower val 
            int lowergrounds = height[l] < height[r] ? height[l++] : height[r--];
            // move to next bar
            waterlevel = Math.max(waterlevel, lowergrounds);
            // if next bar is larger than the current low bar, then trap += 0
            trap += waterlevel - lowergrounds;
        }
        return trap;
    }
    // approach 1:
    public int trap1(int[] height) {
        int sum = 0;
        // special case
        if (height.length <= 2) return sum;

        // 1. Find maxima index
        List<Integer> maxima = new ArrayList<>();
        for (int i = 0; i < height.length; i++) {
            if (i == 0){
                if (height[i] > height[i + 1]) maxima.add(i);
            } else if(i == height.length - 1) {
                if (height[i] >= height[i - 1]) maxima.add(i);
            } else if (height[i] != height[i + 1]) {
                // !! height[i] != height[i+1] is to remove dulipcate maxima. Only record the last one
                if (height[i] >= height[i - 1] && height[i] > height[i + 1]) {
                    maxima.add(i);
                }
            }
        }

        // 2. Remove minima in the maxima index list
        List<Integer> newMaxima = new ArrayList<>();
        // O(n^2)
        for (int i = 0; i < maxima.size();) {
            // find the immediate right which is larger than or equal to left or max right 
            int leftLimit = height[maxima.get(i)], maxRightLimit = 0, maxJ = -1;
            for (int j = i + 1; j < maxima.size(); j++) {
                int rightLimit = height[maxima.get(j)];
                if (rightLimit >= leftLimit) {
                    // find a right val which is larger than left val, end inner loop
                    maxJ = j;
                    break;
                } else {
                    // update maxRight
                    if (rightLimit > maxRightLimit) {
                        maxRightLimit = rightLimit;
                        maxJ = j;
                    }
                }
            }
            if (maxJ == -1) break;
            // add maxima index to newMaxima
            newMaxima.add(maxima.get(i));
            newMaxima.add(maxima.get(maxJ));
            i = maxJ;
        }

        maxima = newMaxima;
        // 3. Compute sum
        for (int i = 0; i < maxima.size() - 1; i++) {
            // left bar index, and right bar index
            int left = maxima.get(i), right = maxima.get(i + 1);
            // find the limit
            int limit = height[left] < height[right] ? height[left] : height[right];
            // Compute the sum
            for (int j = left + 1; j < right; j++) {
                /* !! e.g. sequence like: 2,1,0,3,3
                    the limit = 2, left = 0, right = 4
                    when limit - height[3], it will get minus result.
                    so we add a constrictions
                */
                if (limit > height[j])
                    sum += limit - height[j];
            } 
        }

        return sum;
    }
}


public class TrappingRain {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] height = new int[] {5,3,7,7};
        int sum = s.trap(height);
        System.out.println(sum);
    }
}