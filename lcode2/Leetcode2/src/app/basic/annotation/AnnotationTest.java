package app.basic.annotation;

import java.lang.reflect.Method;
import java.util.Arrays;

public class AnnotationTest {
  @Description({21, 32, 12})
  public static void testAnn() {

  }

  public static void main(String[] args) {
    Method[] ms = AnnotationTest.class.getMethods();
    for (Method m : ms) {
        boolean isAnnExist = m.isAnnotationPresent(Description.class);
        if (isAnnExist) {
            Description d = m.getAnnotation(Description.class);
            System.out.println(Arrays.toString(d.value()));
        }
    }   
  }
}