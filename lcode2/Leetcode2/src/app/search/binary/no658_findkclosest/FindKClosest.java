package app.search.binary.no658_findkclosest;

import java.util.ArrayList;
import java.util.List;

class Solution {
    // approach 1: binary Search
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        List<Integer> res = new ArrayList<>();
        if (arr == null || arr.length == 0) return res;
        
        int left = 0, right = arr.length - 1;
        // binary search
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] < x) {
                left = mid;
            } else {
                right = mid;
            }
        }
        
        int targetIdx = x - arr[left] <= arr[right] - x ? left : right, 
            start = targetIdx, end = targetIdx + 1;
        for (int i = 0; i < k && start >= 0; i++) {
            if (end >= arr.length || x - arr[start] <= arr[end] - x) {
                start--;
            } else {
                end++;
            }
        }
        
        for (int i = start + 1; i < start + k + 1; i++) {
            res.add(arr[i]);
        }
        
        return res;
    }
}

public class FindKClosest {
    public static void main(String[] args) {
        int[] arr = new int[] {0,0,0,1,3,5,6,7,8,8};
        int k = 2, x = 2;
        Solution s = new Solution();
        List<Integer> res = s.findClosestElements(arr, k, x);
        System.out.println(res);
    }
}