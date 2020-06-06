package app.basic.abstracttest;

abstract class Animal {
    private double weight = 0;
    protected String kind = "None";
    public Animal() {}
    public Animal(double weight, String kind) {
        this.weight = weight;
        this.kind = kind;
    }
    abstract protected void info();
}

class Cat extends Animal {
    public Cat() {
        System.out.println(kind);
    }
    public Cat(double weight, String kind) {
        super(weight, kind);
    }
    @Override
    protected void info() {
        System.out.println("Kind: " + kind);
    }
}

public class AbstractConstructor {
    public static void main(String[] args) {
        Animal a = new Cat(100, "Feline");
        a.info();
    }
}