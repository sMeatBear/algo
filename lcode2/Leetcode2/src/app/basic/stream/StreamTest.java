package app.basic.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StreamTest {
    public static void main(String[] args) {
        // 1. get min or max
        List<Integer> tList = Arrays.asList(22, 13, 91, 73, 101, 90, 11, 23, 76);
        System.out.println(tList.stream().max((a, b) -> Integer.compare(a, b)).get());

        // 2. filter the even number
        System.out.println(tList.stream().filter(n -> (n & 1) == 0).collect(Collectors.toList()));

        // 3. for each
        tList.forEach(n -> System.out.println(n));

        // 4. map
        List<Integer> res4 = tList.stream().map(n -> n * n).collect(Collectors.toList());
        System.out.println(res4.getClass());
    }
}
