import java.util.Comparator;

interface Barkable {
    void bark(String content);
}

interface Animal {
    final int a = 1;

    void move();
}

public class LambdaTest implements Animal, Barkable{
    public static void main(String[] args) {
        Integer a = 2, b =3;
        Barkable c = (con) -> System.out.println("woooh" + con);
        c.bark("ooo");
    }
    
}