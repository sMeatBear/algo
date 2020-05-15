package app.linear.linkedlist.listiterator;

import java.util.LinkedList;
import java.util.ListIterator;

public class ListIteratorTest {
    public static void main(String[] args) {
        LinkedList<String> test = new LinkedList<>();
        test.add("b");
        test.add("c");
        test.add("d");
        ListIterator<String> testItr = test.listIterator();    
        String next = testItr.next();
        System.out.println(next);   // b
        String previous = testItr.previous();
        System.out.println(previous);   // b
    }
}