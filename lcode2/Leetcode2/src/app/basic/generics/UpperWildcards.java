package app.basic.generics;

import java.util.ArrayList;
import java.util.List;

public class UpperWildcards {
    public static void main(String[] args) {
        List<Integer> intList = new ArrayList<>();

        intList.add(1);
        intList.add(20);
        intList.add(30);
        // compile time error
        // printList(intList); 
        printListWild(intList);
    }

    public static void printList(List<Object> objList) {
        for(Object obj : objList) {
            System.out.println(obj);
        }
        System.out.println("++++++++++++List<Object>++++++++++++");
    }

    public static void printListWild(List<?> wildList) {
        for (Object wild : wildList) {
            System.out.println(wild);
        }
        System.out.println("++++++++++++List<?>++++++++++++");
    }
}