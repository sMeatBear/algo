package app.basic.Inheritance;

import javax.swing.event.SwingPropertyChangeSupport;

class A {
    static void m1() {
        System.out.println("A's static m1");
    }
}

class B extends A {
    static void m1() {
        System.out.println("B's static m1");
    }
}

public class StaticOverride {
    public static void main(String[] args) {
        B.m1();
    }
}
