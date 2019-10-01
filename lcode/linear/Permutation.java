import java.util.Arrays;

public class Permutation {
    
    public static boolean nextPermutation(int[] nums) {
        // res
        boolean hasNext = false;
        // basic exception
        if (nums.length <= 1) return hasNext;

        // from rear to front
        int r = nums.length - 1;

        // find first descending element
        int desIndex = r - 1;
        
        for (; desIndex >= 0 && nums[desIndex] >= nums[desIndex + 1]; desIndex--) {
        }

        if (desIndex < 0) return hasNext;
        else {
            hasNext = true;
            // find the element just greater than the first descending number
            int i;
            for (i = desIndex + 1; i < nums.length; i++) {
                // fisrt element less than descending number
                if (nums[desIndex] >= nums[i]) break;
            }
            int greater = i - 1;
            // swap
            int tmp = nums[desIndex];
            nums[desIndex] = nums[greater];
            nums[greater] = tmp;
            // rest should be sorted
            Arrays.sort(nums, desIndex + 1, nums.length);
        }

        return hasNext;
    }


    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4};
        int count = 1;
        while (nextPermutation(nums)) {
            System.out.println(Arrays.toString(nums));
            count++;
        }
        System.out.println(count);
    }
}