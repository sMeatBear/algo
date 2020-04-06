package app.string.no49_groupanagrams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given an array of strings, group anagrams together.
 * 
 * Example:
 * 
 * Input: ["eat", "tea", "tan", "ate", "nat", "bat"], Output: [
 * ["ate","eat","tea"], ["nat","tan"], ["bat"] ] Note:
 * 
 * All inputs will be in lowercase. The order of your output does not matter.
 */

class Solution {
    // Approach 1: BF Sort String
    public List<List<String>> groupAnagrams(String[] strs) {
        // Store chars of each type and the corresponding strings list
        Map<String, List<String>> types = new HashMap<>();
        // Result
        
        // Create or add types
        for (int i = 0; i < strs.length; i++) {
            // Sort the char array
            char[] strArr = strs[i].toCharArray();
            Arrays.sort(strArr);
            String strType = new String(strArr);
            
            if (types.containsKey(strType)) {
                // Add the string to types
                types.get(strType).add(strs[i]);
            } else {
                // Add a new type
                List<String> newType = new ArrayList<>();
                newType.add(strs[i]);
                types.put(strType, newType);
            }
        }
        
        return new ArrayList<>(types.values());
    }
}

public class GroupAnagrams {
    public static void main(String[] args) {
        Solution s = new Solution();
        String[] strs = new String[] {"eat", "tea", "tan", "ate", "nat", "bat"};
        System.out.println(s.groupAnagrams(strs)); 
    }
}