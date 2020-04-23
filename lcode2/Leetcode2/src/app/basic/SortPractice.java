package app.basic;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class SortPractice {
    public static void quickSort(int[] nums) {
        if (nums == null) {
            return;
        }

        quickSort(nums, 0, nums.length - 1);
    }

    public static void quickSort(int[] nums, int start, int end) {
        if (start < end) {
            int pivot = partition(nums, start, end);
            quickSort(nums, start, pivot);
            quickSort(nums, pivot + 1, end);
        }
    }

    private static int partition(int[] nums, int start, int end) {
        // choose start as the index of pivot
        while (start < end) {
            while (start < end && nums[start] <= nums[end]) {
                end--;
            }

            // when left is larger than right
            if (start < end) {
                int tmp = nums[start];
                nums[start] = nums[end];
                nums[end] = tmp;
                start++;
            } else {
                break;
            }

            while (start < end && nums[start] <= nums[end]) {
                start++;
            }

            if (start < end) {
                int tmp = nums[start];
                nums[start] = nums[end];
                nums[end] = tmp;
                end--;
            } else {
                break;
            }
        }

        return start;
    }

    // use tmp is to reduce the memory cost
    public static void mergeSort(int[] nums) {
        if (nums == null) {
            return;
        }
        int[] tmp = new int[nums.length];
        mergeSort(nums, 0, nums.length - 1, tmp);
    }

    /**
     * divide method
     * 
     * @param nums
     * @param start inclusive
     * @param end   inclusive
     * @param tmp
     */
    public static void mergeSort(int[] nums, int start, int end, int[] tmp) {
        if (start < end) {
            int mid = start + (end - start) / 2;
            // divide to get sorted left part and sorted right part
            mergeSort(nums, start, mid, tmp);
            mergeSort(nums, mid + 1, end, tmp);
            // conquer
            merge(nums, start, mid, end, tmp);
        }
    }

    private static void merge(int[] nums, int start, int mid, int end, int[] tmp) {
        int left = start, right = mid + 1, tmpIdx = start;
        // O(n) merge
        while (left <= mid && right <= end) {
            if (nums[left] < nums[right]) {
                tmp[tmpIdx++] = nums[left++];
            } else {
                tmp[tmpIdx++] = nums[right++];
            }
        }

        // append the rest
        while (left <= mid) {
            tmp[tmpIdx++] = nums[left++];
        }
        while (right <= end) {
            tmp[tmpIdx++] = nums[right++];
        }

        // correct the sequence in nums
        for (int i = start; i <= end; i++) {
            nums[i] = tmp[i];
        }
    }

    public static void bubbleSort(int[] nums) {
        if (nums == null) {
            return;
        }
        int exchange = nums.length - 1;
        while (exchange != 0) {
            int bound = exchange;
            exchange = 0;
            for (int i = 0; i < bound; i++) {
                if (nums[i] > nums[i + 1]) {
                    int tmp = nums[i];
                    nums[i] = nums[i + 1];
                    nums[i + 1] = tmp;
                    // the sequence from i + 1 to end is corrent
                    // so next time we only check until the sequence of i-1 and i
                    exchange = i;
                }
            }
        }
    }

    public static void selectionSort(int[] nums) {
        if (nums == null) {
            return;
        }
        // find global min index and swap the value with current i
        for (int i = 0; i < nums.length - 1; i++) {
            int globalMinIdx = i;
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[globalMinIdx] > nums[j]) {
                    globalMinIdx = j;
                }
            }
            // swap
            int tmp = nums[globalMinIdx];
            nums[globalMinIdx] = nums[i];
            nums[i] = tmp;
        }
    }

    public static void sortingTester(Class<?> sortingClass, String sortingName, int[] original, int[] correct)
            throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException,
            SecurityException {
        // copy a new array to keep the original unsorted one untact
        int[] nums = Arrays.copyOf(original, original.length);
        // get the sorting method obj
        Method sortingMethod = sortingClass.getMethod(sortingName, int[].class);

        // invoke the method and time
        long startTime = System.currentTimeMillis(), endTime = 0;
        sortingMethod.invoke(sortingClass, nums);
        endTime = System.currentTimeMillis();

        // check the correction
        System.out.println("============================= " + sortingName + " =============================");
        System.out.println("Result: " + (Arrays.equals(correct, nums) ? "correct" : "wrong") + "\nTime Cost: "
                + (endTime - startTime) + " ms\n");
    }

    public static void sortingTester(Object obj, String sortingName, int[] original, int[] correct) throws IllegalAccessException,
            IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        Class<?> sortingClass = obj.getClass();
        sortingTester(sortingClass, sortingName, original, correct);
    }

    public static void sortingTester(String sortingName, int[] original, int[] correct) throws IllegalAccessException,
            IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        sortingTester(SortPractice.class, sortingName, original, correct);
    }

    public static void main(String[] args) throws Exception {
        int size = 1000000, bound = 100000;
        int[] original = SortingTestCaseGenerator.SortingCases(size, bound);
        int[] correct = Arrays.copyOf(original, size);
        Arrays.sort(correct);

        /*********** selection ***************/
        // sortingTester("selectionSort", original, correct);
        /*********** bubbleSort ***************/
        // sortingTester("bubbleSort", original, correct);
        /*********** mergeSort ***************/
        sortingTester("mergeSort", original, correct);
        /*********** old mergeSort ***************/
        // sortingTester(app.sorting.basic.CommonSort.class, "mergeSort", original,
        // correct);
        /*********** quickSort ***************/
        sortingTester("quickSort", original, correct);
        /*********** original quickSort ***************/
        sortingTester(Arrays.class, "sort", original, correct);
    }
}