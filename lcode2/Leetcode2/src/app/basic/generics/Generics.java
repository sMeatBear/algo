package app.basic.generics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Generics {
    public static void main(String[] args) {
        Object[] objArr = new Integer[] {1,2,3};
        System.out.println(Arrays.toString(objArr));

        List<Object> objList = new ArrayList<>();
        List<Integer> intList = new ArrayList<>();
        // Type mismatch: cannot convert from List<Integer> to List<Object>Java(16777233)
        // objList = intList;
    }
}