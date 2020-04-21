package app.basic.generics;

public class GenericArr {
    public static <T> int createEmptyArr(T[] template) {
        int len = template.length;
        // cannot create a generic array
        // return new T[len]; 
        return len;
    }
}