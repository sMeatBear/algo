package app.string.no709_tolowercase;


class Solution {
    // normal approach
    public String toLowerCase1(String str) {
        return str.toLowerCase();
    }
    
    // original
    public String toLowerCase(String str) {
        // a newly allocated char array
        char[] strArr = str.toCharArray();

        for (int i = 0; i < strArr.length; i++) {
            // A - Z : ACSII 65 ~ 90
            // a - z : 97 ~ 122
            if (strArr[i] <= 90 && strArr[i] >= 65) {
                strArr[i] += 32;
            }
        }

        return new String(strArr);
    }
}



public class ToLowerCase {
    public static void main(String[] args) {
        Solution s = new Solution();
        String str = s.toLowerCase("HEllo");
        System.out.println(str);
    }
}