package app.search.binary.binarysearchpractices;


public class BinarySearchPractices {
    /**
     *  determine the target in which rows in a sorted two dimensional array
     */
    public static int searchRow(int[][] nums, int target) {
        // corner case
        if (nums == null || nums.length == 0 || nums[0].length == 0) {
            return -1;
        }

        int rowStart = 0, rowEnd = nums.length - 1, cols = nums[0].length - 1;
        while (rowStart <= rowEnd) {
            int mid = rowStart + (rowEnd - rowStart) / 2;

            // one dimensional
            if (nums[mid][0]  < target) {
                // two dimensional
                if (nums[mid][cols] < target) {
                    rowStart = mid + 1;
                } else {
                    return mid;
                }
            } else if (nums[mid][0] > target) {
                rowEnd = mid - 1;
            } else {
                return mid;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        int[][] nums = new int[][] {
            {1,3,7,9,10},
            {13,14,67,99,100},
            {101,189,200,310,331},
            {340,350,360,370,380}
        };

        System.out.println(searchRow(nums, 100));
    }
}