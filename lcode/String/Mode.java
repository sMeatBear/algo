import java.util.*;

class SolutionMode {
    public List<Integer> topKFrequent(int[] nums, int k) {
        Integer[] count = new Integer[nums.length];

        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums.length; j++) {
                if (nums[i] == nums[j])
                    if (count[i] == null) count[i] = 1;
                    else count[i]++;
            }
        }

        Arrays.sort(count, Collections.reverseOrder());

        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            res.add(nums[i]);
        }

        return res;
    }
}

public class Mode {
    public static void main(String[] args) {
        SolutionMode m = new SolutionMode();
        int[] nums = new int[] { 1, 1, 1, 2, 2, 3 };
        System.out.println(m.topKFrequent(nums, 2));
    }
}