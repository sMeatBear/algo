package app.math.permutation;

import java.util.Arrays;

public class Permutation {
    public static void next(int[] nums) {
        // 1. Find the first descending element:
        int len = nums.length, d = len - 1;
        while ( d > 0 && nums[d] <= nums[d - 1]) {
            d--;
        }
        d = d > 0 ? d - 1 : 0;

        // 2. Swap with the smallest number larger than current nums[d] on the right
        int s;
        for (s = d + 1; s < len && nums[d] <= nums[s]; s++) {
        }

        // 3. Swap
        // In case s == 0:
        s = s > 0 ? s - 1 : 0;
        int tmp = nums[d];
        nums[d] = nums[s];
        nums[s] = tmp;

        // 4. Sort the rest behind the d
        if (d + 1 < len)
            Arrays.sort(nums, d + 1, len);
    }

    public static void main(String[] args) {
        int[] nums = new int[] {2, 1, 3};
        Permutation.next(nums);
        System.out.println(Arrays.toString(nums));
    }
}