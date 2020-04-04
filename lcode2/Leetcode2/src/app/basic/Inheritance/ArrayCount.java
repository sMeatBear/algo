package app.basic.Inheritance;

import java.util.ArrayList;
import java.util.List;

class Array {
    private List<Integer> list = new ArrayList<>();

    public void add(int a) {
        list.add(a);
    }

    public void addAll(int[] nums) {
        for (int num : nums) {
            list.add(num);
        }
    }
}

public class ArrayCount extends Array{
    int count;

    @Override
    public void add(int a) {
        super.add(a);
        count++;
    }

    @Override
    public void addAll(int[] nums) {
        super.addAll(nums);
        count += nums.length;
    }

    public static void main(String[] args) {
        ArrayCount ac = new ArrayCount();
        int[] nums = new int[] {1,23,2,1};
        ac.addAll(nums);
        System.out.println("Count: " + ac.count);
    }
}