package app.basic;

public class InnerClass {
    class coordinate {
        int x;
        int y;
    }

    public void testMethodClass() {
        class Test {
            int x;
            int y;
        }
        Test a = new Test();

        System.out.println(a.x + ", " + a.y);
    }
}