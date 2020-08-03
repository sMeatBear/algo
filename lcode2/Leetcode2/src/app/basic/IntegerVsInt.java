package app.basic;

public class IntegerVsInt {
    // public static void print(int a) {
    //     System.out.println("print int invoked");
    // }

    public static void print(Integer a) {
        System.out.println("print Integer invoked");
    }

    public static void main(String[] args) {
        print(3); // print Integer invoked
        Integer a = 3;
        print(a); // print Integer invoked
        Integer b = new Integer(3);
        print(b); // print Integer invoked
    }
}