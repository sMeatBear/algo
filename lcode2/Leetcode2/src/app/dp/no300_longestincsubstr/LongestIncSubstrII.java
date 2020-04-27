package app.dp.no300_longestincsubstr;

class SolutionII {
    public static int longestIncSubstr(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        // store the least value for each length
        int[] dp = new int[nums.length];
        int maxLen = 0;

        // traverse to update dp
        for (int num : nums) {
            // use binary search to find the first larger index
            // maxLen is (current most valid index + 1 ) in dp array
            int i = findFirstLargerOrEqual(dp, maxLen, num);
            // update smaller value to this length
            dp[i] = num;
            // update max length
            maxLen = Math.max(maxLen, i + 1);
        }

        // output result
        for (int num : dp) {
            if (num == 0) {
                break;
            }
            System.out.print(num + " ");
        }
        System.out.println();

        return maxLen;
    }

    public static int findFirstLargerOrEqual(int[] nums, int right, int target) {
        int left = 0;

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        // don't need to conside cannot find, the nums array is the same size with the original one
        // each element is initialized with the max value. It has to be a index to return.
        return left;
    }
}

public class LongestIncSubstrII {
    public static void main(String[] args) {
        int[] nums = new int[] {10,9,2,5,3,7,101,18};
        System.out.println(SolutionII.longestIncSubstr(nums));
    }
}