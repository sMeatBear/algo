package app.basic.array;

import java.util.Arrays;

public class BubbleSort {
    public static void improvedBubble(int[] nums) {
        if (nums == null) return;

        int exchangeIdx = nums.length - 1;

        // If there is no swap then out of loop
        while (exchangeIdx != 0) {
            int bound = exchangeIdx;
            exchangeIdx = 0;

            for (int i = 0; i < bound; i++) {
                if (nums[i] > nums[i + 1]) {
                    int tmp = nums[i + 1];
                    nums[i + 1] = nums[i];
                    nums[i] = tmp;
                    // *Record the last swap position as next loop's bound
                    exchangeIdx = i;
                }
            }
        }
    }

    public static void bubbleSort(int[] nums) {
        if (nums == null) return;

        // The actual element that need to sort (nums.length - 1)
        for (int i = 0; i < nums.length - 1; i++) {
            // sink or bubble up
            for (int j = 0; j < nums.length - i - 1; j++ ) {
                // Swap
                if (nums[j] > nums[j + 1]) {
                    int tmp = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = tmp;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[] {1, 2, 9, 7, 3, 0, 6};
        improvedBubble(nums);
        System.out.println(Arrays.toString(nums));
    }
}