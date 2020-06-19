package app.test;

public class TestSamePackage {
    public static void main(String[] args) {
        Test t = new Test();
        int[] nums = new int[] {0, 1, 2};
        t.quicksort(nums);
    }
}