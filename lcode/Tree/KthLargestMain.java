class KthLargest {
    private class TreeNode {
        private int val;
        private TreeNode left;
        private TreeNode right;
        // greater than current node
        private int cnt;
        private TreeNode(int _val) {val = _val; cnt = 1;}
    }
    
    private TreeNode root;
    private final int k;
    
    public KthLargest(int _k, int[] nums) {
        k = _k;
        if (nums.length != 0 ) {
            root = new TreeNode(nums[0]);
            for (int i = 0; i < nums.length; i++) {
                simpleAdd(nums[i]);
            }
        }
    }
    
    public void simpleAdd(int _val) {
        TreeNode p = root;
        
        TreeNode newNode = new TreeNode(_val);
        
        while (p != null) {
            if (_val < p.val) {
                if (p.left == null) {p.left = newNode; p = null;}
                else p = p.left;
            } else {
                p.cnt++;
                if (p.right == null) {p.right = newNode; p = null;}
                else {p = p.right;}
            }
        }
    }

    public int add(int _val) {
        if (root == null) {
            root = new TreeNode(_val);
            return _val;
        } 
        
        
        TreeNode p = root;
        
        TreeNode newNode = new TreeNode(_val);
        int kthNum = _val;
        
        while (p != null) {
            if (_val < p.val) {
                if (p.left == null) {p.left = newNode; p = null;}
                else p = p.left;
            } else {
                p.cnt++;
                if (p.right == null) {p.right = newNode; p = null;}
                else {p = p.right;}
            }
        }
        
        // search 
        p = root;
        int remain = k;
        while (p != null && remain != 0) {
            if (remain > p.cnt) {
                remain -= p.cnt;
                p = p.left;
            } else if (remain < p.cnt) {
                p = p.right;
            } else {
                remain -= p.cnt;
            }
        }
        
        kthNum = p.val;
        
        return kthNum;
    }
}

public class KthLargestMain {
    public static void main(String[] args) {
        int[] nums = new int[] {4, 5, 8, 2};
        KthLargest k = new KthLargest(3, nums);
        System.out.println(k.add(3));
        System.out.println(k.add(6));
        System.out.println(k.add(10));
    }
}
