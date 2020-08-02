package app.test;

import java.lang.reflect.InvocationTargetException;

import app.tree.TreeNode;

public class ReflectionTest {
    public static void main(String[] args) throws InstantiationException, IllegalAccessException,
            IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        TreeNode a = TreeNode.class.getConstructor(int.class).newInstance(13);
    }
}