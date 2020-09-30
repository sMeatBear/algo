package app.basic.interfaceimpl;

import java.lang.instrument.UnmodifiableClassException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * test two interface with same method but with different implementation
 * 
 */
interface CanBark {
    // default modifier: public static final
    String breed = "doggy";
    default void bark() {
        System.out.println("bark");
    }
}

interface CanMakeNoise {
    public void makeNoise();
    // default void bark() {
    //     System.out.println("wang");
    // }
}

public class InterfaceDefault implements CanBark, CanMakeNoise {

    @Override
    public void makeNoise() {
        // TODO Auto-generated method stub
        List<Integer> list = new ArrayList<>();
        list.add(1);
        List<Integer> test = Collections.unmodifiableList(list);
        test.add(2);
    }

    public static void main(String[] args) {
        System.out.println(InterfaceDefault.breed);
        CanBark cb = new InterfaceDefault();
        cb.bark();
    }
}