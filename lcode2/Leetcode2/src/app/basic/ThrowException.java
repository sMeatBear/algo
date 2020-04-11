package app.basic;

public class ThrowException {
    public static void main(String[] args) {
        ThrowException te = new ThrowException();
        te.testThrow(true);
    }   
    
    public void testThrow(boolean throwException) {
        if (throwException) {
            throw new NullPointerException("I was deliberate to do this");
        }
        System.out.println("executed");;
    }
}