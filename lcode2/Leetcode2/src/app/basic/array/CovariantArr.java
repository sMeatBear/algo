package app.basic.array;

import java.util.Arrays;

public class CovariantArr {
    public static void main(String[] args) {
        // Type mismatch: cannot convert from int[] to Object[]Java(16777233)
        // Object[] o = new int[5];
        Object[] o = new Integer[5];
        o[0] = 1;
        o[1] = "hello";  //Exception in thread "main" java.lang.ArrayStoreException: java.lang.String
                         //   at app.basic.array.CovariantArr.main(CovariantArr.java:11)
        System.out.println(Arrays.toString(o));
    }
}
