package app.basic.generics;

public class Instanceof {
    public static void main(String[] args) {
        // compile error
        // System.out.println(List<Integer> instanceof List<? super Integer>);
    }
}
