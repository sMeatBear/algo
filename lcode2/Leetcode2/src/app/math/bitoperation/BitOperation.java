package app.math.bitoperation;

import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    // use bit operation 4 bits by 4 bits
    public static String toHexByBit(int num) {
        int mask = 0xF;
        // 32 bits should remove 8 times
        int moveTimes = 32 / 4;
        int moveBit = 4;
        Deque<Character> stack = new ArrayDeque<>();
        for (int i = 0; i < moveTimes; i++) {
            int remaining = num & mask;
            if (remaining >= 10) {
                stack.push((char) (remaining - 10 + 'A'));
            } else {
                stack.push((char) (remaining + '0'));
            }
            num >>>= moveBit;
        }

        StringBuilder sb = new StringBuilder();
        sb.append("0x");
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }

        return sb.toString();
    }

    // math method
    public static String toHex(int num) {
        // in case overflow
        long n = num;
        if (n < 0) {
            n = -n;
        }

        // to store the remaining
        Deque<Integer> resStack = new ArrayDeque<>();
        for (; n > 0; n /= 16) {
            resStack.push((int) (n % 16));
        }

        // convert the ans to string
        StringBuilder sbuilder = new StringBuilder();
        sbuilder.append("0x");
        while (!resStack.isEmpty()) {
            int remaining = resStack.pop();
            if (remaining >= 10) {
                sbuilder.append((char) ('A' + (remaining - 10)));
            } else {
                sbuilder.append(remaining);
            }
        }

        return sbuilder.toString();
    }
    // bit reverse
    public static String bitReverse(String bit) {
        int num = Integer.parseInt(bit, 2);
        // two pointer
        // if the value is the same at i and j bit, we don't exchange
        // if they are different, we use a 100..001 mask to flip
        for (int i = 0, j = 31; i < j; i++, j--) {
            if ((num >>> j & 1) != (num >>> i & 1)) {
                int mask = (1 << j) + (1 << i);
                // do the xor to flip
                num ^= mask;
            }
        }

        return Integer.toBinaryString(num);
    }
    // if all lower case letter in the string is unique
    public static boolean isUniqueLetter(String str) {
        if (str == null) {
            return true;
        }
        // use 32 bit to record if the letter is unique
        int record = 0;
        // int[] record = new int[8]; for the whole ascii signs

        for (int i = 0; i < str.length(); i++) {
            int pos = str.charAt(i) - 'a';
            if ((record >> pos & 1) == 1) {
                // if pos bit is 1
                return false;
            } else {
                // if not, set it to 1
                record |= 1 << pos;
            }
        }

        return true;
    }

    // the number of different bits between two numbers
    public static int diffBitsOfTwo(int num1, int num2) {
        int count = 0;
        // step 1: do the xor to find the different bit position
        // step 2: count how many 1 in the diff result
        for (int n = num1 ^ num2; n != 0; n &= n - 1, count++) {}
        return count;
    }

    // if it is power of 2:
    public static boolean isPowerOfTwo(int num) {
        if (num < 1) {
            return false;
        }
        // to check if there is only one 1 bit in the number
        return (num & num - 1) == 0;
    }

    // Complement
    public void complement() {
        System.out.println((byte)(1 << 7));
    }

    // Times 2
    public void times2(int n) {
        for (int i = 1; i < n; i <<= 1) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    // Count one in int 2
    public int countOneInInt2(int n) {
        int count = 0;

        while (n != 0) {
            // always remove the 1 at the rightmost bit
            n &= n - 1;
            count++;
        }

        return count;
    }

    // Count one in int 1
    public int countOneInInt1(int n) {
        // mask: 1000 0000 0000 0000, then right move
        int mask = 1 << 31, count = 0;

        while (mask != 0) {
            int tmp = n & mask;
            if (tmp > 0) count++;
            mask >>>= 1;
        }

        return count;
    }
}

public class BitOperation {
    public static void main(String[] args) {
        Solution s = new Solution();
        int countOneInInt1 = s.countOneInInt2(2147483647);
        System.out.println("countOneInInt1: " + countOneInInt1);

        // Use bit operation to time 2
        System.out.println("times2: ");
        s.times2(20);

        // -2147483648 (1 ... 0000) to (1 ... 1111)
        s.complement();

        // byte 255
        byte b255 = (byte)255;
        System.out.println(b255);

        // left shift 32 bits
        System.out.println("left shift 32 bits: " + (1 << 32));

        int k = 3;
        System.out.println("kth bit set to 1: " + (0 | 1 << k)); // 8
        System.out.println(Integer.MIN_VALUE & Integer.MIN_VALUE - 1); // 0
        System.out.println("How many bits are different: " + Solution.diffBitsOfTwo(8, 10));
        System.out.println("If the letter is unique: " + Solution.isUniqueLetter("studen"));
        System.out.println("Reverse the bit string: " + Solution.bitReverse("10100110"));

        System.out.println("Print hex string for a decimal number: " + Solution.toHexByBit(Integer.MIN_VALUE));
    }
}