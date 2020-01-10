package app.string.no1108_defangingip;


class Solution {
    public String defangIPaddr(String address) {
        return address.replaceAll("\\.", "[.]");
        // approach 2
        // return String.join("[.]", address.split("\\."));
    }
}


public class DefangingIP {
    public static void main(String[] args) {
        Solution s = new Solution();
        String str = s.defangIPaddr("1.1.1.1");
        System.out.println(str);
    }
}