package app.basic.generics;

class A {
    public void sayHi() {
        System.out.println("hi");
    }
}
interface B {
    void sayGoodBye();
}

class C extends A implements B {
    @Override
    public void sayGoodBye() {
        System.out.println("good bye");
    }
}

public class MultiBound {
    public static <T extends A & B> void test(T test) {
        test.sayHi();
        test.sayGoodBye();
    }

    public static void main(String[] args) {
        C test = new C();
        MultiBound.test(test);
    }
}
