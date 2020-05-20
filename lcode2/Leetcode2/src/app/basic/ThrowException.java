package app.basic;

import java.io.FileNotFoundException;

public class ThrowException {
    public static void main(String[] args) {
        ThrowException te = new ThrowException();
        te.test(true);
    }

    public void testThrow(boolean throwException) throws NullPointerException {
        if (throwException) {
            // this is unchecked exception. No need to handle
            throw new NullPointerException("I was deliberate to do this");
        }
        System.out.println("executed");
    }

    public void test(boolean throwException) {
        testThrow(throwException);
    }
}