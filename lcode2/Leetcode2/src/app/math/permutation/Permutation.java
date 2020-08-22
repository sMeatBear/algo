package app.math.permutation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Permutation {
    public static <T> List<List<T>> getAllPermutation(T[] nums) {
        List<List<T>> result = new ArrayList<>();
        backtrack(nums, 0, result);
        return result;
    }
    

    private static <T> void backtrack(T[] nums, int i, List<List<T>> result) {
        if (i == nums.length) {
            List<T> res = new ArrayList<>();
            for (T n : nums) {
                res.add(n);
            }
            result.add(res);
        }

        for (int j = i; j < nums.length; j++) {
            swap(nums, i, j);
            backtrack(nums, i + 1, result);
            // backtrack
            swap(nums, i, j);
        }
    }

    public static <T> void swap(T[] nums, int i, int j) {
        T temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    /**
     * find the next permutation
     * 
     * @param nums the array which is needed to do permutation
     */
    public static boolean nextPermutation(int[] nums) {
        int len = nums.length;
        // 1. find the first descending
        int firstDescIdx = len - 2;
        for (; firstDescIdx >= 0 && nums[firstDescIdx] >= nums[firstDescIdx + 1]; firstDescIdx--) {}

        if (firstDescIdx < 0) {
            // sort from the start
            Arrays.sort(nums);
            // become the first state
            return false;
        }

        // 2. find the smallest element on the right which is larger than fisrt decending element
        int minIdx = firstDescIdx + 1;
        for (; minIdx < len - 1 && nums[minIdx + 1] > nums[firstDescIdx]; minIdx++) {}

        // 3. swap
        int tmp = nums[minIdx];
        nums[minIdx] = nums[firstDescIdx];
        nums[firstDescIdx] = tmp;

        // 4. sort
        Arrays.sort(nums, firstDescIdx + 1, len);

        return true;
    }

    @Deprecated
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
        int[] nums = new int[] {1,2,3,4};
        do {
            System.out.println(Arrays.toString(nums));
        } while(Permutation.nextPermutation(nums));

        String[] strs = new String[] {"1", "2", "3"};
        System.out.println(getAllPermutation(strs));
    }
}