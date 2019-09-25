class SolutionB {
    public int search(int[] nums, int target) {
        if (nums.length == 0) return -1;
        int l = 0, r = nums.length - 1;
        int pivot = -1;
        int res;
        
        // find 2 section first, find pivot
        while (l <= r) {
            int mid = l + (r - l) / 2;
            int left = mid == 0 ? 0 : mid - 1;
            int right = mid == nums.length - 1 ? mid : mid + 1;
            
            if (nums[mid] >= nums[left] && nums[mid] >= nums[right]) {
                pivot = mid;
                break;
            } else if (nums[mid] < nums[0]) {
                // pivot at left section
                r = mid - 1;
            } else {
                // pivot at right section
                l = mid + 1;
            }
        }
        
        if (nums[pivot] == target) {
            return pivot;
        } else if (nums[0] < target) {
            l = 1; r = pivot - 1;
        } else if (nums[0] > target){
            l = pivot + 1; r = nums.length - 1;
        } else if (nums[0] == target) {
            return 0;
        } else {
            return -1;
        }
        
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] == target) return mid;
            else if (nums[mid] < target) {
                l = mid + 1;
            } else if (nums[mid] > target) {
                r = mid - 1;
            }
        }
        
        return -1;
    }
}

public class BinarySearch1 {
    public static void main(String[] args) {
        int[] nums = {4,5,6,7,0,1,2};
        SolutionB s = new SolutionB();
        int res = s.search(nums, 6);
        System.out.println(res);
    }
}