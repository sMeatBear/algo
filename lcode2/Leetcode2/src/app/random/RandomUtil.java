package app.random;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class RandomUtil {
    /**
     * Get specific number of random number (pseudo random number).
     * Not even distributed in multiple invoking.
     * @param begin the begin number (inclusive)
     * @param end   the end number (exclusive)
     * @param num   the required random number 
     * @return      the list of generated random number
     * @throws IllegalArgumentException if the given range is smaller than required number
     */
    private static Random randomNumbersGenerator = new Random();
    public static List<Integer> getRandomNumbers(int begin, int end, int num) {
        Random rand = randomNumbersGenerator;
        Set<Integer> result = new HashSet<>();
        int range = end - begin;
        if (range < num) {
            throw new IllegalArgumentException("The range is smaller than required random number");
        }

        while (result.size() < num) {
            result.add(rand.nextInt(range) + begin); // the range is from [begin, end - 1]
        }

        return new ArrayList<>(result);
    }

    /**
     * Use an algorithm like insertion sort to swap the elements randomly
     * @param <T> generic type
     * @param arr given array which needs to be shuffled
     */
    public static <T> void shuffleArray(T[] arr) {
        if (arr == null) {
            return;
        }

        Random rand = new Random();
        for (int i = 0; i < arr.length - 1; i++) {
            // swap ith element with any index behind i
            int swapIdx = rand.nextInt(arr.length - i) + i;
            swap(arr, i, swapIdx); 
        }
    }

    /**
     * Use an algorithm like insertion sort to swap the elements randomly
     * @param arr given array which needs to be shuffled
     */
    public static void shuffleArray(int[] arr) {
        if (arr == null) {
            return;
        }

        Random rand = new Random();
        for (int i = 0; i < arr.length - 1; i++) {
            // swap ith element with any index behind i
            int swapIdx = rand.nextInt(arr.length - i) + i;
            int tmp = arr[i];
            arr[i] = arr[swapIdx];
            arr[swapIdx] = tmp;
        }
    }

    /**
     * Use an algorithm like insertion sort to swap the elements randomly
     * @param arr given array which needs to be shuffled
     */
    public static void shuffleArray(char[] arr) {
        if (arr == null) {
            return;
        }

        Random rand = new Random();
        for (int i = 0; i < arr.length - 1; i++) {
            // swap ith element with any index behind i
            int swapIdx = rand.nextInt(arr.length - i) + i;
            char tmp = arr[i];
            arr[i] = arr[swapIdx];
            arr[swapIdx] = tmp;
        }
    }

    private static <T> void swap(T[] arr, int i, int j) {
        T tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void main(String[] args) {
        int[] arr = new int[] {1, 2, 3, 4, 5, 6};
        shuffleArray(arr);
        System.out.println(Arrays.toString(arr));
        System.out.println(getRandomNumbers(1, 33, 5));
    }
}