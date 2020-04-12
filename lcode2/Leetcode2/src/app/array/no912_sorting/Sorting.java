package app.array.no912_sorting;

import java.util.Arrays;

class Solution {
    // quick sort
    public int[] quickSort(int[] nums) {
        quickSort(nums, 0, nums.length - 1);
        return nums;
    }
    
    public void quickSort(int[] nums, int s, int e) {
        if (s < e) {
            int pivot = partition(nums, s, e);
            quickSort(nums, s, pivot - 1);
            quickSort(nums, pivot + 1, e);
        }
    }
    
    // partition, return pivot
    public int partition(int[] nums, int s, int e) {
        int i = s, j = e;
        while (i < j) {
            // if nums[j] >= nums[i] then keep it at the right
            while (i < j && nums[i] <= nums[j]) {
                j--;
            }
            
            if (i < j) {
                // swap, now nums[j] is the pivot
                int tmp = nums[i];
                nums[i] = nums[j];
                nums[j] = tmp;
                i++;
            } else {
                break;
            }
            
            // keep the smaller ones on the left
            while (i < j && nums[j] >= nums[i]) {
                i++;
            }
            
            if (i < j) {
                // now nums[i] is pivot
                int tmp = nums[i];
                nums[i] = nums[j];
                nums[j] = tmp;
                j--;
            } else {
                break;
            }
        }
        return i;
    }
    
    // merge sort
    public int[] mergeSort(int[] nums) {
        // corner case
        if (nums == null) return null;
        
        int[] tmp = new int[nums.length];
        mergeSort(nums, tmp, 0, nums.length - 1);
        
        return nums;
    }
    
    public void mergeSort(int[] nums, int[] tmp, int l, int r) {
        if (l >= r) return;
        
        int m = l + (r - l) / 2;
        // left sort
        mergeSort(nums, tmp, l, m);
        // right sort
        mergeSort(nums, tmp, m + 1, r);
        // combine
        merge(nums, tmp, l, m, r);
    }   
    
    public void merge(int[] nums, int[] tmp, int l, int m, int r) {
        // i is left start position, j is right start position, k is tmp start
        int i = l, j = m + 1, k = l;
        
        // O(n) merge
        while (i <= m && j <= r) {
            if (nums[i] < nums[j]) {
                tmp[k++] = nums[i++];
            } else {
                tmp[k++] = nums[j++];
            }
        }
        
        // append rest
        while (i <= m) {
            tmp[k++] = nums[i++];
        }
        
        while (j <= r) {
            tmp[k++] = nums[j++];
        }
        
        // copy the sequence to original array
        for (int n = l; n <= r; n++) {
            nums[n] = tmp[n];
        }
    }
    
    // Bubble sort
    public int[] bubbleSort(int[] nums) {
        int exchange = nums.length - 1;
        
        while (exchange != 0) {
            int bound = exchange;
            exchange = 0;
            int i = 0;
            
            while (i < bound) {
                if (nums[i] > nums[i + 1]) {
                    int tmp = nums[i + 1];
                    nums[i + 1] = nums[i];
                    nums[i] = tmp;
                    exchange = i;
                }
                i++;
            }
        }
        
        return nums;
    }
    
    // Selection sort
    public int[] selectionSort(int[] nums) {
        int minIdx = 0;
        
        for (int i = 0; i < nums.length - 1; i++) {
            // update minIdx
            minIdx = i;
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] < nums[minIdx]) {
                    minIdx = j;
                }
            }
            int tmp = nums[minIdx];
            nums[minIdx] = nums[i];
            nums[i] = tmp;
        }
        
        return nums;
    }
}

public class Sorting {
    public static void main(String[] args) {
        int[] nums = new int[] {5,2,3,1,5,1,1,2};
        Solution s = new Solution();
        s.quickSort(nums);
        System.out.println(Arrays.toString(nums));
    }
}