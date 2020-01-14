package app.array.no169_majorityelement;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an array of size n, find the majority element. The majority element is the element that appears more than ⌊ n/2 ⌋ times.

You may assume that the array is non-empty and the majority element always exist in the array.

Example 1:

Input: [3,2,3]
Output: 3
Example 2:

Input: [2,2,1,1,1,2,2]
Output: 2
 */

class Solution {
    public int majorityElement(int[] nums) {
        int len = nums.length;
        // majority number's restriction
        int maj = len / 2;
        int res = 0;
        Map<Integer, Integer> map = new HashMap<>();

        for (int num : nums) {
            int cnt = map.getOrDefault(num, 0) + 1;
            if (cnt > maj) return num;
            map.put(num, cnt);
        }

        /* no need to traverse the map
        for (Map.Entry<Integer, Integer> pair : map.entrySet()) {
            if (pair.getValue() > maj) {
                res = pair.getKey();
                break;
            }
        }
        */

        return res;
    }
}


public class MajorityElement {
    public static void main(String[] args) {
        
    }
}