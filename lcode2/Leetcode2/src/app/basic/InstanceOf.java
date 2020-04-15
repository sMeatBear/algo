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
        System.out.println("c instanceof f : " + (c instanceof Father));
        System.out.println("c2 instance of f: " + (c2 instanceof Father));
        System.out.println("c2 introduce: " + c2.introduce());
        System.out.println("null instance of f: " + (null instanceof Object)); // false
    }
}