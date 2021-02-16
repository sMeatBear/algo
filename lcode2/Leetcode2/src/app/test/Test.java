package app.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class InnerCall {
    private int b;
    int exeTest(InnerCall ic, Test t) {
        return b + ic.b + t.testInner;
    }
}

public class Test {
    private int testInner;
    /**
     * 
     * Q3
     */
    // approach 2: math
    public List<String> permutationString(String str) {
        if (str == null) {
            return null;
        }

        char[] sArr = str.toCharArray();
        List<String> res = new ArrayList<>();
        Arrays.sort(sArr);
        res.add(new String(sArr));
        while (permutation(sArr)) {
            res.add(new String(sArr));
        }

        return res;
    }

    public boolean permutation(char[] sArr) {
        // 1. find the first descending element
        int descIndex = sArr.length - 2;
        for (; descIndex >= 0 && sArr[descIndex] >= sArr[descIndex + 1]; descIndex--) {}
        if (descIndex < 0) {
            return false;
        }

        // 2. find the last element that is greater than sArr[descIndex]
        // if exchange a same element to the front and then sort, it will procude duplicate situation. It has to be a larger element!
        int lastLarger = descIndex + 1;
        for (; lastLarger < sArr.length - 1 && sArr[descIndex] < sArr[lastLarger + 1]; lastLarger++) {}

        // 3. swap desc and last larger
        char tmp = sArr[descIndex];
        sArr[descIndex] = sArr[lastLarger];
        sArr[lastLarger] = tmp;

        // 4. sort
        Arrays.sort(sArr, descIndex + 1, sArr.length);

        return true;
    }

    // approach 1: dfs
    public List<String> permutationStr(String str) {
        if (str == null) {
            return null;
    }
    
    List<String> list = new ArrayList<>();
    char[] strArr = str.toCharArray();
    dfs(strArr, list, 0);
    return list;
    }
    
    private void dfs(char[] strArr, List<String> list, int index) {
        if (index == strArr.length) {
            list.add(new String(strArr));
    }
    
    for (int i = index; i < strArr.length; i++) {
        // swap with number behind
        swap(strArr, index, i);
        dfs(strArr, list, index + 1);
        // backtrack
        swap(strArr, index, i);
    }
    }
    
    private void swap(char[] strArr, int p1, int p2) {
        char tmp = strArr[p1];
        strArr[p1] = strArr[p2];
        strArr[p2] = tmp;
    }
    
    /**
     * 
     * Q4
     */
    public void quicksort(int[] nums) {
        if (nums == null) {
            return;
        }
        quicksort(nums, 0, nums.length - 1);
    }

    /**
     * start and end index are both included
     */
    public void quicksort(int[] nums, int start, int end) {
        if (start < end) {
            int pivot = partition(nums, start, end);
            quicksort(nums, start, pivot - 1);
            quicksort(nums, pivot + 1, end);
        }
    }

    public int partition(int[] nums, int start, int end) {
        // select start as the pivot
        while (true) {
            // if end is larger than pivot, keep the same
            while (start < end && nums[start] <= nums[end]) {
                end--;
            }
            if (start < end) {
                swap(nums, start, end);
                start++;
            } else {
                break;
            }
            while (start < end && nums[start] <= nums[end]) {
                start++;
            }
            if (start < end) {
                swap(nums, start, end);
                end--;
            } else {
                break;
            }
        }

        return start;
    }

    public void swap(int[] nums, int p1, int p2) {
        int tmp = nums[p1];
        nums[p1] = nums[p2];
        nums[p2] = tmp;
    }

    public static void main(String[] args) {
        Test t = new Test();
        String str = "abcc";
        System.out.println(t.permutationString(str));
    }
}