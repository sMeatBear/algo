package app.basic.generics;

import java.util.ArrayList;
import java.util.List;

public class RawType {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        List rawList = list;
        rawList.add("hello");
        rawList.add(1);
        System.out.println(rawList);
        System.out.println(list);
        // Cannot perform instanceof check against parameterized type List<Integer>. 
        // Use the form List<?> instead 
        // since further generic type information will be erased at runtimeJava(536871458)
        // System.out.println(list instanceof List<Integer>);
    }
}