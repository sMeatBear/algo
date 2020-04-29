package app.basic;


public class InstanceOf {
    public static void main(String[] args) {
        class Father {
            public String introduce() {
                return "Father obj";
            }
        }
        
        class Child extends Father {
            @Override
            public String introduce() {
                return "Child obj";
            }
        }
        Child c = new Child();
        Father c2 = new Child();
        Object c3 = new Child();
        System.out.println("c instanceof f : " + (c instanceof Father)); // true
        System.out.println("c2 instance of f: " + (c2 instanceof Father)); // true
        System.out.println("c2 introduce: " + c2.introduce()); // "Child obj"
        System.out.println("null instance of f: " + (null instanceof Object)); // false
        System.out.println("Is c2 instance of Child: " + (c2 instanceof Child)); // true
        System.out.println("Is c3 instance of Child: " + (c3 instanceof Child)); // true
    }
}