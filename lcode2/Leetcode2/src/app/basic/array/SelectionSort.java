package app.basic.array;

import java.util.Arrays;

public class SelectionSort {
    public static void selectionSort(int[] nums) {
        if (nums == null) return;
        int len = nums.length;

        for (int i = len - 1; i > 0; i--) {
            int minIdx = 0;
            for (int j = 0; j <= i; j++) {
                if (nums[minIdx] > nums[j]) {
                    minIdx = j;
                }
            }
            
            // Swap
            int tmp = nums[i];
            nums[i] = nums[minIdx];
            nums[minIdx] = tmp;
        }
    }
    public static void main(String[] args) {
        int[] nums = new int[] {0, 1, 12,8,7,11}, nums1 = null, nums2 = new int[0];
        selectionSort(nums);
        System.out.println(Arrays.toString(nums));
    }
}