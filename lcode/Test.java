import java.util.*;

public class Test {

    public static <t> void hello(t test) {
        System.out.println("hello: " + test.getClass());
    }
    public static void main(String[] args) {
        Long.parseLong("1534236469");

        for (int i = 0; i < 3; i++ ) {
            System.out.println(i);
        }

        ArrayList<Integer> testList = new ArrayList<Integer>();
        Integer[] a = {1, 2, 3, 4, 5};
        // List<Integer> test = Arrays.asList(a);
        Collections.addAll(testList, a);
        System.out.println(testList);
        hello(a);
    }
}