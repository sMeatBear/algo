package app.basic.generics;

import java.util.ArrayList;
import java.util.List;

public class HeapPollution {
    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        // unchecked generics type
        List listOfStr = new ArrayList<String>();
        listOfStr.add("hello");
        // pollution and can run
        List<Integer> listOfInt = listOfStr;
        System.out.println(listOfInt);
    }
}