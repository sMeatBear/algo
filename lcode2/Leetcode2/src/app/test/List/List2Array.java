package app.test.List;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class List2Array {
    public static void main(String[] args) {
        List<Integer> l = new ArrayList<>(Arrays.asList(new Integer[] {1,2,3,4,5,6}));
        // java.lang.ClassCastException: [Ljava.lang.Object; cannot be cast to [Ljava.lang.Integer;
        // Integer[] test = (Integer[]) l.toArray();
        Integer[] test = l.toArray(new Integer[l.size()]);
        System.out.println(Arrays.toString(test));
    }
}
