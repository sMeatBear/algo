package app.string.no468_validateipaddress;

/**
 * Write a function to check whether an input string is a valid IPv4 address or
 * IPv6 address or neither.
 * 
 * IPv4 addresses are canonically represented in dot-decimal notation, which
 * consists of four decimal numbers, each ranging from 0 to 255, separated by
 * dots ("."), e.g.,172.16.254.1;
 * 
 * Besides, leading zeros in the IPv4 is invalid. For example, the address
 * 172.16.254.01 is invalid.
 * 
 * IPv6 addresses are represented as eight groups of four hexadecimal digits,
 * each group representing 16 bits. The groups are separated by colons (":").
 * For example, the address 2001:0db8:85a3:0000:0000:8a2e:0370:7334 is a valid
 * one. Also, we could omit some leading zeros among four hexadecimal digits and
 * some low-case characters in the address to upper-case ones, so
 * 2001:db8:85a3:0:0:8A2E:0370:7334 is also a valid IPv6 address(Omit leading
 * zeros and using upper cases).
 * 
 * However, we don't replace a consecutive group of zero value with a single
 * empty group using two consecutive colons (::) to pursue simplicity. For
 * example, 2001:0db8:85a3::8A2E:0370:7334 is an invalid IPv6 address.
 * 
 * Besides, extra leading zeros in the IPv6 is also invalid. For example, the
 * address 02001:0db8:85a3:0000:0000:8a2e:0370:7334 is invalid.
 * 
 * Note: You may assume there is no extra space or special characters in the
 * input string.
 * 
 * Example 1: Input: "172.16.254.1"
 * 
 * Output: "IPv4"
 * 
 * Explanation: This is a valid IPv4 address, return "IPv4". Example 2: Input:
 * "2001:0db8:85a3:0:0:8A2E:0370:7334"
 * 
 * Output: "IPv6"
 * 
 * Explanation: This is a valid IPv6 address, return "IPv6". Example 3: Input:
 * "256.256.256.256"
 * 
 * Output: "Neither"
 * 
 * Explanation: This is neither a IPv4 address nor a IPv6 address.
 */
class Solution {
    public String validIPAddress(String IP) {
        // sanity check
        String v4 = "IPv4", v6 = "IPv6", neither = "Neither";
        if (IP == null || IP.endsWith(".") || IP.endsWith(":")) {
            return neither;
        }
        int v4Len = 4, v6Len = 8;
        // split
        String[] group = IP.split("\\.");
        if (group.length == v4Len) {
            for (String g : group) {
                if (!isUnsignedByte(g)) {
                    return neither;
                }
            }
            return v4;
        } else if ((group = IP.split(":")).length == v6Len) {
            for (String g : group) {
                if (!isUnsignedShort(g)) {
                    return neither;
                }
            }
            return v6;
        }
        return neither;
    }
    
    /** str is a decimal number */
    public boolean isUnsignedByte(String str) {
        // out of bound
        if (str.length() == 0 || str.length() > 3) {
            return false;
        }
        int bound = 0xff;
        int radix = 10, pos = 1, num = 0;
        for (int i = str.length() - 1; i >= 0; i--, pos *= radix) {
            char ch = str.charAt(i);
            // tell if char is digit (only the rightmost digit can be 0)
            if (i == str.length() - 1) {
                // not in [0, 9]
                if (ch < 0x30 || ch > 0x39) {
                    return false;
                }
            } else {
                // not in (0, 9]
                if (ch <= 0x30 || ch > 0x39) {
                    return false;
                }
            }
            // append the digit to result
            num += (ch - '0') * pos;
        }
        return num <= bound;
    }
    
    /** str is a hex */
    public boolean isUnsignedShort(String str) {
        if (str.length() == 0 || str.length() > 4) {
            return false;
        }
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            // ch is in [0, 9], [A, F], [a, f]
            if (!('0' <= ch && ch <= '9' || 'A' <= ch && ch <= 'F' || 'a' <= ch && ch <= 'f')) {
                return false;
            }
        }
        return true;
    }
}

public class ValidateIpAddr {
    public static void main(String[] args) {
        Solution sol = new Solution();
        // String IP = "172.16.265.1";
        String IP = "2001:0db8:85a3:0:0:8A2E:0370:000000";
        System.out.println(sol.validIPAddress(IP));
    }
}