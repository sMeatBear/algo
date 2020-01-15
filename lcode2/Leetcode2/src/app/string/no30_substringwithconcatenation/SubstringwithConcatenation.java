package app.string.no30_substringwithconcatenation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * You are given a string, s, and a list of words, words, that are all of the same length. Find all starting indices of substring(s) in s that is a concatenation of each word in words exactly once and without any intervening characters.

 

    Example 1:

    Input:
    s = "barfoothefoobarman",
    words = ["foo","bar"]
    Output: [0,9]
    Explanation: Substrings starting at index 0 and 9 are "barfoo" and "foobar" respectively.
    The output order does not matter, returning [9,0] is fine too.
    Example 2:

    Input:
    s = "wordgoodgoodgoodbestword",
    words = ["word","good","best","word"]
    Output: []
 */

class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> res = new ArrayList<>();
        if (s == null || words == null || 
            s.length() == 0 || words.length == 0 || 
            words[0].length() == 0) 
            return res;

        // get the length of each word in words array
        int wLen = words[0].length();
        // use map to store words (duplicate words exist)
        // dict <word, count>x
        Map<String, Integer> dict = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            dict.put(words[i], dict.getOrDefault(words[i], 0) + 1);
        }

        for (int i = 0; i < s.length() - words.length * wLen + 1; i++) {
            Map<String, Integer> appearance = new HashMap<>(dict);
            // appearance count
            int count = words.length;
            int start = i;

            // search forwards
            while (count > 0) {
                String next = s.substring(start, start + wLen);
                int freq = appearance.getOrDefault(next, 0);
                if (freq != 0) {
                    // exist the word and it appears at first time
                    count--;
                    appearance.put(next, appearance.get(next) - 1);
                    // search next word
                    start += wLen;
                } else {
                    break;
                }

                // if all words only appear once
                if (count == 0) res.add(i);
            }

        }
        return res;
    }
}


public class SubstringwithConcatenation {
    public static void main(String[] args) {
        Solution s = new Solution();
        String str = "wordgoodgoodwordgoodbestword", str2 = "barfoofoobarthefoobarman";
        String[] words = new String[] {"word","good","best","word"},
                words2 = new String[] {"foo","bar","the"};
        List<Integer> res = s.findSubstring(str, words);
        List<Integer> res2 = s.findSubstring(str2, words2);

        System.out.println(res);
        System.out.println(res2);
    }
}