import java.util.*;

class Solution18 {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        // if (nums.length() < 4) return new List<List<Integer>>;
        Arrays.sort(nums);

        HashSet<List<Integer>> res = new HashSet<List<Integer>>();
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();

        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }

        int i = 0, j, k;
        boolean dontwork = false;

        for (; i < nums.length; i++) {
            dontwork = false;
            j = i + 1;
            for (; j < nums.length; j++) {
                k = j + 1;
                for (; k < nums.length; k++) {
                    int threeSum = nums[i] + nums[j] + nums[k];
                    if (threeSum > target && threeSum >= 0) {
                        dontwork = true;
                        break;
                    }

                    int fourth = target - threeSum;
                    if (fourth >= nums[k] && map.containsKey(fourth) && map.get(fourth) > k) {
                        List<Integer> oneRes = new ArrayList<Integer>();
                        oneRes.add(nums[i]);
                        oneRes.add(nums[j]);
                        oneRes.add(nums[k]);
                        oneRes.add(fourth);
                        res.add(oneRes);
                    }
                }
            }
        }

        return new ArrayList<List<Integer>>(res);
    }
}

public class No18 {
    public static void main(String[] args) {
        Solution18 s = new Solution18();
        int[] nums = {-1,0,-5,-2,-2,-4,0,1,-2};
        System.out.println(s.fourSum(nums, -9));
        
    }
}