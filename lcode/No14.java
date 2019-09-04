class Solution14 {
    public String longestCommonPrefix(String[] strs) {
        String res = "";
        // if only one string in strs
        if (strs.length == 1) return strs[0];
        int j = 0;
        for (int i = 0; i < strs.length - 1; i++) {
            if (j < strs[i].length() && j < strs[i + 1].length()) {
                if (strs[i].charAt(j) != strs[i + 1].charAt(j))
                    break;
                if (i == strs.length - 2) {
                    res += strs[i].charAt(j);
                    i = -1;
                    j++;
                }
            } else break;
                
        }
        
        return res;
    }
}

public class No14 {
    public static void main(String[] args) {
        Solution14 s = new Solution14();
        String[] strs = {"aa", "aa"};
        System.out.println(s.longestCommonPrefix(strs));
    }
}