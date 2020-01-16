package app.array.no1313_decompressrlencording;

import java.util.ArrayList;
import java.util.List;

/**
 We are given a list nums of integers representing a list compressed with run-length encoding.

Consider each adjacent pair of elements [a, b] = [nums[2*i], nums[2*i+1]] (with i >= 0).  For each such pair, there are a elements with value b in the decompressed list.

Return the decompressed list.

 

Example 1:

Input: nums = [1,2,3,4]
Output: [2,4,4,4]
Explanation: The first pair [1,2] means we have freq = 1 and val = 2 so we generate the array [2].
The second pair [3,4] means we have freq = 3 and val = 4 so we generate [4,4,4].
At the end the concatenation [2] + [4,4,4,4] is [2,4,4,4].
 

Constraints:

2 <= nums.length <= 100
nums.length % 2 == 0
1 <= nums[i] <= 100
 */
class Solution {
    //approach 2: use list
    public int[] decompressRLElist(int[] nums) {
        List<Integer> res = new ArrayList<>();

        for (int i = 0; i < nums.length; i += 2) {
            int cnt = 0, code = nums[i + 1];
            while (cnt < nums[i]) {
                res.add(code);
                cnt++;
            }
        }

        int[] resArr = new int[res.size()];
        for (int i = 0; i < resArr.length; i++) resArr[i] = res.get(i);

        return resArr;
    }

    // approach 1: no list
    public int[] decompressRLElist1(int[] nums) {
        // 1. Get all run length
        int len = 0;
        int[] runLen = new int[nums.length / 2];
        for (int i = 0; i < nums.length; i += 2) {
            len += nums[i];
            runLen[i / 2] = nums[i];
        }

        int[] res = new int[len];

        // fill the res
        int j = 0;
        for (int i = 0; i < runLen.length; i++) {
            int cnt = 0, code = nums[i * 2 + 1];
            while (cnt < runLen[i]) {
                res[j] = code;
                j++;
                cnt++;
            }
        }

        return res;
    }
}


public class DecompressRL {
    public static void main(String[] args) {
        
    }
}