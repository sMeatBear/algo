package app.basic.generics;

import java.util.ArrayList;
import java.util.List;

public class RawType {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        List rawList = list;
        rawList.add("hello");
        System.out.println(rawList);
        System.out.println(list);
    }
}