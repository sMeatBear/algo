package app;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Timer {
    public static Object execute(final Object obj, final String methodName, final Object[] args, final Class<?>... parameterTypes) {
        Class<?> cl = null;
        if (obj instanceof Class<?>) {
            // if pass a Class object
            cl = (Class<?>) obj;
        } else {
            cl = obj.getClass();
        }
        try {
            final Method method = cl.getMethod(methodName, parameterTypes);
            // for default class
            method.setAccessible(true);

            final long start = System.currentTimeMillis();
            Object res = method.invoke(obj, args);
            System.out.println("\nTime of " + methodName + ": " +
            (System.currentTimeMillis() - start) + " ms");
            return res;
		} catch (NoSuchMethodException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}
        
        return null;
    }
}