package app.math.dec2bin;

public class Dec2Bin {
    public static String toBinaryString(int i) {
        StringBuilder sbd = new StringBuilder();
        int radix = 2;
        for (; i != 0; i /= radix) {sbd.append(i % 2);}
        return sbd.reverse().toString();
    }
    public static void main(String[] args) {
        int i = 1;
        System.out.println(Integer.toBinaryString(i));
        System.out.println(toBinaryString(i));
    }
}