package app.basic;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class SortPractice {
    // 6/10/2020
    public static void quickSortV2(int[] nums) {
        quickSortV2(nums, 0, nums.length - 1);
    }
    public static void quickSortV2(int[] nums, int begin, int end) {
        if (begin < end) {
            int pivotIdx = partitionV2(nums, begin, end);
            quickSortV2(nums, begin, pivotIdx - 1);
            quickSortV2(nums, pivotIdx + 1, end);
        }
    }
    private static int partitionV2(int[] nums, int begin, int end) {
        // take begin index as the pivot idx
        int pivotVal = nums[begin];
        while (begin < end) {
            // we use pivot value instead of nums[begin]
            while (begin < end && pivotVal <= nums[end]) {
                end--;
            }
            // out of loop which means we need to swap current element to the front
            // *incomplete swap (save time)
            // **don't change it to nums[begin++] (we need to care about when begin == end !)
            nums[begin] = nums[end];
            // from front to end check
            while (begin < end && nums[begin] <= pivotVal) {
                begin++;
            }
            // swap
            nums[end] = nums[begin];
        }
        // put the pivot value to the final position
        nums[begin] = pivotVal;
        return begin;
    }

    /* ----------------------------------------------------------- */
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

    // 6/9/2020 insert sort
    public static void insertSort(int[] nums) {
        // sanity check
        if (nums == null) {
            return;
        }
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < nums[i - 1]) {
                int insertElem = nums[i];
                // compare forward to find the first element in j-1 smaller than insertElem
                int j = i;
                nums[j] = nums[j - 1];
                for (j = i - 1; j > 0 && nums[j - 1] > insertElem; j--) {
                    nums[j] = nums[j - 1];
                }
                nums[j] = insertElem;
            }
        }
    }

    public static void insertSortWithSentinel(int[] nums) {
        // sanity check
        if (nums == null) {
            return;
        }
        // set newArr's 0 position as the sentinel
        int[] newNums = new int[nums.length + 1];
        for (int i = 1; i < newNums.length; i++) {
            newNums[i] = nums[i - 1];
        }
        // sort, newNums[0] as the sentinel
        for (int i = 2; i < newNums.length; i++) {
            if (newNums[i] < newNums[i - 1]) {
                newNums[0] = newNums[i];
                // compare forward to find the first element in j-1 smaller than insertElem
                int j = i;
                newNums[j] = newNums[j - 1];
                for (j = i - 1; newNums[j - 1] > newNums[0]; j--) {
                    newNums[j] = newNums[j - 1];
                }
                newNums[j] = newNums[0];
            }
        }
        // copy the result
        for (int i = 1; i < newNums.length; i++) {
            nums[i - 1] = newNums[i];
        }
    }

    public static void testSorting(Class<?> sortingClass, String sortingName, int[] original, int[] correct)
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

    public static void testSorting(Object obj, String sortingName, int[] original, int[] correct) throws IllegalAccessException,
            IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        Class<?> sortingClass = obj.getClass();
        testSorting(sortingClass, sortingName, original, correct);
    }

    public static void testSorting(String sortingName, int[] original, int[] correct) throws IllegalAccessException,
            IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        testSorting(SortPractice.class, sortingName, original, correct);
    }

    public static void main(String[] args) throws Exception {
        int size = 1000000, bound = 1000000;
        int[] original = SortingTestCaseGenerator.SortingCases(size, bound);
        int[] correct = Arrays.copyOf(original, size);
        Arrays.sort(correct);

        /*********** selection ***************/
        // testSorting("selectionSort", original, correct);
        /*********** bubbleSort ***************/
        // testSorting("bubbleSort", original, correct);
        /*********** mergeSort ***************/
        // testSorting("mergeSort", original, correct);
        /*********** old mergeSort ***************/
        // testSorting(app.sorting.basic.CommonSort.class, "mergeSort", original,
        // correct);
        /*********** old quickSort ***************/
        testSorting("quickSort", original, correct);
        /*********** quickSort V2 ***************/
        testSorting("quickSortV2", original, correct);
        /*********** insertSort ***************/
        // testSorting("insertSort", original, correct);
        /*********** insertSortWithSentinel ***************/
        // testSorting("insertSortWithSentinel", original, correct);
        /*********** original quickSort ***************/
        testSorting(Arrays.class, "sort", original, correct);
    }
}