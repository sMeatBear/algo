package app.basic.recursion;

public class StackOverFlowTest {
    public static void stackOverFlow(int layer) {
        try {
            stackOverFlow(layer + 1);
        } catch (StackOverflowError e) {
            System.out.println("Stop at layer: " + layer);
        }
    }
    public static void main(String[] args) {
        stackOverFlow(0);
    }
}