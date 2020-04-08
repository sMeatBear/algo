package app.basic.array;

public class ObjArr {
    public static void main(String[] args) {
        // Test if we can save primitive type into an obj array
        Object[] arr = new Object[5];
        // Auto wrap
        arr[0] = 1;
        System.out.println(arr[0]); // Print out 1
    }
}