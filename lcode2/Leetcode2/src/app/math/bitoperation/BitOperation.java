package app.math.bitoperation;

class Solution {
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
    }
}