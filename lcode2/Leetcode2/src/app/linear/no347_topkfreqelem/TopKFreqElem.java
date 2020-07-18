package app.linear.no347_topkfreqelem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * Given a non-empty array of integers, return the k most frequent elements.
 * 
 * Example 1:
 * 
 * Input: nums = [1,1,1,2,2,3], k = 2 Output: [1,2] Example 2:
 * 
 * Input: nums = [1], k = 1 Output: [1] Note:
 * 
 * You may assume k is always valid, 1 ≤ k ≤ number of unique elements. Your
 * algorithm's time complexity must be better than O(n log n), where n is the
 * array's size. It's guaranteed that the answer is unique, in other words the
 * set of the top k frequent elements is unique. You can return the answer in
 * any order.
 */

class Solution {
    /**
     * approach 2: quick select
     */
    public int[] topKFrequent(int[] nums, int k) {
        // count the freq
        Map<Integer, Integer> freqMap = new HashMap<>(nums.length);
        int[] result = new int[k];
        
        for (int num : nums) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }
        
        // quick select
        List<Map.Entry<Integer, Integer>> entryList = new ArrayList<>(freqMap.entrySet());
        int pivot = partition(entryList, 0, entryList.size() - 1, k);
        
        // post processing
        for (int i = 0; i < k; i++, pivot++) {
            result[i] = entryList.get(pivot).getKey();
        }
        return result;
    }
    
    private int partition(List<Map.Entry<Integer, Integer>> entryList, int begin, int end, int k) {
        if (begin >= end) {return begin;}
        // pick the begin as the pivot
        int pivotVal = entryList.get(begin).getValue();
        
        // two-pointer
        int slow = begin + 1, fast = slow;
        while (fast <= end) {
            if (entryList.get(fast).getValue() < pivotVal) {
                // swap
                swap(entryList, slow++, fast);
            }
            fast++;
        }
        // swap the pivot
        int pivotIdx = slow - 1;
        swap(entryList, begin, pivotIdx);
        
        // **check the number of selected element (use end not entryList.size() (k is different))
        int countTop = end - pivotIdx + 1;
        if (countTop < k) {
            // find the (k - countTop) at the left part
            pivotIdx = partition(entryList, begin, pivotIdx - 1, k - countTop);
        } else if (countTop > k) {
            pivotIdx = partition(entryList, pivotIdx + 1, end, k);
        }
        return pivotIdx;
    }
    
    private <T> void swap(List<T> list, int i, int j) {
        T temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }
    
    
    
    /** 
     *approach 1: minheap tc: O(nlogk) sc: O(n)    
     */
    public int[] topKFrequent1(int[] nums, int k) {
        // count the freq
        Map<Integer, Integer> freqMap = new HashMap<>(nums.length);
        for (int num : nums) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }
        
        Set<Map.Entry<Integer, Integer>> entrySet = freqMap.entrySet();
        // min heap
        PriorityQueue<Map.Entry<Integer, Integer>> pq = new PriorityQueue<>(entrySet.size(), (a, b) -> Integer.compare(a.getValue(), b.getValue()));
        
        // offer k
        int i = 0;
        Iterator<Map.Entry<Integer, Integer>> entryItr = entrySet.iterator();
        while (i < k) {
            pq.offer(entryItr.next());            
            i++;
        }
        
        while (entryItr.hasNext()) {
            pq.offer(entryItr.next());
            pq.poll();
        }
        
        int[] result = new int[k];
        for (i = 0; i < k; i++) {
            result[i] = pq.poll().getKey();
        }
        return result;
    }
}

public class TopKFreqElem {
    public static void main(String[] args) {
        Solution sol = new Solution();
        int k = 10;
        int[] nums = new int[] {3,2,3,1,2,4,5,5,6,7,7,8,2,3,1,1,1,10,11,5,6,2,4,7,8,5,6};
        System.out.println(Arrays.toString(sol.topKFrequent(nums, k)));
    }
}