package app.string;

import java.util.Arrays;

public class SplitTest {
    public static void main(String[] args) {
        String str = "the quick fox  jumpes over  the lazy dog    ";
        System.out.println(Arrays.toString(str.split(" +")));
    }
}