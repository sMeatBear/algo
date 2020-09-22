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

class Solution2 {
    private static class StringAna {
        final int POS_PRIME = 0x7fffffff;
        final int DEFAUT_SIZE = 26;
        int[] count;
        int hash = -1;

        public StringAna(String str) {
            count = new int[DEFAUT_SIZE];
            for (int i = 0; i < str.length(); i++) {
                char ch = str.charAt(i);
                count[ch - 'a']++;
            }
        }

        @Override
        public boolean equals(Object o) {
            if (o instanceof StringAna) {
                StringAna sa = (StringAna) o;
                for (int i = 0; i < DEFAUT_SIZE; i++) {
                    if (sa.count[i] != count[i]) {return false;}
                }
                return true;
            }

            return false;
        }

        @Override
        public int hashCode() {
            if (hash != -1) {return hash;}
            hash = 0;
            int base = 1;
            for (int i = 0; i < DEFAUT_SIZE; i++, base *= 100) {
                hash += count[i] * base;
            }
            // ** remember to update the hash value
            hash &= POS_PRIME;
            return hash;
        }
    }
    

    public List<List<String>> groupAnagrams(String[] strs) {
        Map<StringAna, List<String>> map = new HashMap<>();
        for (String s : strs) {
            StringAna sa = new StringAna(s);
            List<String> oneRes = map.get(sa);
            if (oneRes == null) {
                oneRes = new ArrayList<>();
                map.put(sa, oneRes);
            }
            oneRes.add(s);
        }
        return new ArrayList<>(map.values());
    }
}

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
        Solution2 s = new Solution2();
        String[] strs = new String[] {"eat", "tea", "tan", "ate", "nat", "bat"};
        // String[] strs = new String[] {"tan", "nat"};
        System.out.println(s.groupAnagrams(strs)); 
    }
}