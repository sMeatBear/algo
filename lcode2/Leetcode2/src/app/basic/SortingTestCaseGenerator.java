package app.basic;

import java.util.Arrays;
import java.util.Random;

public class SortingTestCaseGenerator {
    /**
     * Given a size, generate a random duplicated-existed int array
     * @param size result size
     * @param bound [0, bound)
     * @return random int array
     */
    public static int[] SortingCases(int size, int bound) {
        Random r = new Random();
        int[] res = new int[size];
        for (int i = 0; i < size; i++) {
            res[i] = r.nextInt(bound);
        }

        return res;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(SortingTestCaseGenerator.SortingCases(10, 9)));
    }
}