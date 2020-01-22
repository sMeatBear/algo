package app.sorting.basic;

import java.util.Arrays;

public class CommonSort {
    // O(n^2) Bubble
    public static void bubble(int[] nums) {
        boolean exchanged = true;
        for (int i = 0; i < nums.length && exchanged; i++) {
            exchanged = false;
            for (int j = 0; j < nums.length - i - 1; j++) {
                if (nums[j] > nums[j + 1]) {
                    int tmp = nums[j + 1];
                    nums[j + 1] = nums[j];
                    nums[j] = tmp;
                    exchanged = true;
                }
            }
        }
    }

    // O(nlogn) Merge sort
    public static int[] mergeSort(int[] nums) {
        return merge(nums, 0, nums.length - 1);
    }

    public static int[] merge(int[] nums, int l, int r) {
        // End condition
        if (l > r) {
            return new int[0];
        } else if (l == r) {
            return new int[] {nums[l]};  
        } if (r - l == 1) {
            int[] sorted = new int[2];
            if (nums[l] < nums[r]) {
                sorted[0] = nums[l];
                sorted[1] = nums[r];
            } else {
                sorted[0] = nums[r];
                sorted[1] = nums[l];
            }
            return sorted;
        }

        // Divive
        int m = l + (r - l) / 2;
        int[] left = merge(nums, l, m), right = merge(nums, m + 1, r);
        return combine(left, right);
    }

    public static int[] combine(int[] nums1, int[] nums2) {
        int[] nums = new int[nums1.length + nums2.length];
        int p1 = 0, p2 = 0, i = 0;

        // Two pointers
        while (p1 < nums1.length && p2 < nums2.length) {
            if (nums1[p1] < nums2[p2]) {
                nums[i] = nums1[p1];
                p1++;
            } else {
                nums[i] = nums2[p2];
                p2++;
            }
            i++;
        }

        // Append rest
        while (p1 < nums1.length) {
            nums[i] = nums1[p1];
            i++;
            p1++;
        }
        while (p2 < nums2.length) {
            nums[i] = nums2[p2];
            i++;
            p2++;
        }

        return nums;
    }

    public static void main(String[] args) {
        int[] nums = new int[] {1,9,10,121,11,2};
        bubble(nums);
        System.out.println("Bubble: " + Arrays.toString(nums));

        nums = new int[] {1,9,10,121,11,2};
        int[] merged = mergeSort(nums);
        System.out.println("Merge: " + Arrays.toString(merged));
    }
}