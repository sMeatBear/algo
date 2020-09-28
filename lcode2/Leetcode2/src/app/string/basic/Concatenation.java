package app.string.basic;

public class Concatenation {
    public static void main(String[] args) {
        String concat = "ab" + "cd";
        String sab = "ab";
        // false
        System.out.println(concat == sab + "cd");
        // true
        System.out.println(concat == "ab" + "cd");
        // true
        System.out.println(concat == "abcd");
    }
}