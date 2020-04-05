package app.math.permutation;

import java.util.Arrays;

public class Permutation {
    public static void next(int[] nums) {
        // 1. Find the first descending element:
        int len = nums.length, d = len - 1;
        while ( d > 0 && nums[d] <= nums[d - 1]) {
            d--;
        }

        // Get the first descending element index
        d--;
        if (d < 0) {
            // If only one element in the array or no descending elem from behind
            Arrays.sort(nums);
        } else {
            // 2. Swap with the smallest number *larger than* current nums[d] on the right
            int s;
            for (s = d + 1; s < len && nums[d] < nums[s]; s++) {
            }
    
            s--;
            // 3. Swap
            int tmp = nums[d];
            nums[d] = nums[s];
            nums[s] = tmp;
    
            // 4. Sort the rest behind the d
            // if (d + 1 < len) No need for this statement. d must smaller than the nums.length !!
            Arrays.sort(nums, d + 1, len);
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[] {1,2};
        Permutation.next(nums);
        System.out.println(Arrays.toString(nums));
    }
}