package app.search.no486_predictwinner;

class Solution {
    // approach 1: ab pruning
    public boolean PredictTheWinner(int[] nums) {
        // sanity check
        if (nums.length < 3) {return true;}
        int a = Integer.MIN_VALUE, b = Integer.MAX_VALUE;
        int res = max(nums, 0, nums.length - 1, a, b);
        return res >= 0;
    }

    private int max(int[] nums, int begin, int end, int a, int b) {
        if (begin >= end) {
            return nums[begin];
        }
        int max = min(nums, begin, end - 1, a, b);
        // ** pruning for previous min layer
        if (max >= b) {return max + nums;}
        // **** update a: the returned mins' restrictions are different (+nums[end] and +nums[begin])
        //      if nums[end] is larger then that means the second min val needs to higher bar to reach
        //      the first max's value level or beyond vice versa. So we add (nums[end] - nums[begin])
        a = Math.max(max + nums[end] - nums[begin], a);
        return Math.max(max + nums[end], min(nums, begin + 1, end, a, b) + nums[begin]);
    }

    private int min(int[] nums, int begin, int end, int a, int b) {
        if (begin >= end) {
            return -nums[begin];
        }
        int min = max(nums, begin, end - 1, a, b);
        // ** pruning for previous max layer
        if (min <= a) {return min - nums[end];}
        // **** because the nums[begin] and nums[end] is differnt, we need to adjust min's val
        b = Math.min(min - (nums[end] - nums[begin]), b);
        return Math.min(min - nums[end], max(nums, begin + 1, end, a, b) - nums[begin]);
    }

}

public class PredictWinner {
    public static void main(String[] args) {
        Solution sol = new Solution();
        // int[] nums = new int[] {877,7113,3270,2243,1902,9268,8784,3837,6582,8751,6928,3108,1120,1872,7762,4220,4692,3409};
        // int[] nums = new int[] {1,5,8,4};
        int[] nums = new int[] {3606449,6,5,9,452429,7,9580316,9857582,8514433,9,6,6614512,753594,5474165,4,2697293,8,7,1};
        System.out.println(nums.length);
        boolean res = sol.PredictTheWinner(nums);
        System.out.println(res);
    }
}