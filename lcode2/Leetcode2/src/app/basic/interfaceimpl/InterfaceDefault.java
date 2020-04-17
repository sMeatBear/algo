package app.basic.interfaceimpl;

/**
 * test two interface with same method but with different implementation
 * 
 */
interface CanBark {
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

    }

}