package app.basic;

public class IntegerCache {
    public static void main(String[] args) {
        Integer a = -128, b = -128;
        System.out.println(a == b); // true
        a = -129; b = -129;
        System.out.println(a == b); // false
    }
}