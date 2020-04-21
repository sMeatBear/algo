package app.basic;

import app.basic.nestedclass.InnerClassTest;
import app.basic.nestedclass.InnerClassTest.Coordinate;

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
        final Test a = new Test();

        System.out.println(a.x + ", " + a.y);
    }

    public static void main(final String[] args) {
        final Coordinate c = new InnerClassTest().new Coordinate(1, 2);
        System.out.println(c.getX() + c.getY());
    }
}