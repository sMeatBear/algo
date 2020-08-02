package app.basic.generics;

import java.util.ArrayList;
import java.util.List;

class Fruit {}
class Apple extends Fruit {}
class Fuji extends Apple {}
class Orange extends Fruit {}

public class LowerBoundAndUpperBound {
    public static void main(String[] args) {
        List<? super Apple> testList = new ArrayList<>();        
        testList.add(new Apple());
        // testList.add(new Fruit()); // All the ancestors class of Apple (included) may be replace the wildcard
                                   // if ? == Apple, we cannot add Fruit
        testList.add(new Fuji()); // Fuji can upcast to Apple or its ancestor
        // Apple a = testList.get(0); // cannot determine which type get will return
                                   // it may be Apple and its ancestor (cannot be downcasting automatically)
                                   // can cast mannually: (Apple) testList.get(0)
        Fruit f = (Fruit) testList.get(0);
    }
}