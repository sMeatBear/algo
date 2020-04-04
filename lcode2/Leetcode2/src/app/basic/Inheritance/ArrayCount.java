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
            // list.add(num); // Change this line to next line
            add(num); // FIXME: ArrayCount addAll method will double the count !!!
        }
    }
}

public class ArrayCount extends Array {
    int count;

    @Override
    public void add(int a) {
        super.add(a);
        count++;
    }

    @Override
    public void addAll(int[] nums) {
        // The add method has been overrided. So when invoke the parent method addAll, it will invoke the overrided add method
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