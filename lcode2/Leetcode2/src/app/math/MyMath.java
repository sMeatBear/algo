package app.math;

public class MyMath {
    /**
     * Compute the greatest common divisor
     */
    public static int gcd(int x, int y) {
        for (int r; (r = x % y) != 0; x = y, y = r) {}
        return y;
    }

    public static void main(String[] args) {
        int gcd = gcd(10, 124);
        System.out.println(gcd);
    }
}