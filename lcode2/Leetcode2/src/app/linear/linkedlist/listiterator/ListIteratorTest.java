package app.linear.linkedlist.listiterator;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class ListIteratorTest {
    public static void iteratorLoop(List<Integer> test) {
        ListIterator<Integer> testItr = test.listIterator();
        while (testItr.hasNext()) {
            int num = testItr.next();
            if (num == 2) {
                testItr.remove();
                testItr.add(2);
            }
            System.out.println(num);
        }
    }
    public static void main(String[] args) {
        /*
        LinkedList<String> test = new LinkedList<>();
        test.add("b");
        test.add("c");
        test.add("d");
        ListIterator<String> testItr = test.listIterator();    
        String next = testItr.next();
        System.out.println(next);   // b
        String previous = testItr.previous();
        System.out.println(previous);   // b
        */
        List<Integer> test = new ArrayList<>();
        test.add(1);
        test.add(2);
        test.add(3);
        test.add(4);
        iteratorLoop(test);
    }
}