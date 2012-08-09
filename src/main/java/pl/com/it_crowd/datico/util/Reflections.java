package pl.com.it_crowd.datico.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public final class Reflections {
// -------------------------- STATIC METHODS --------------------------

    public static Object invokeAndWrap(Method method, Object target, Object... args)
    {
        try {
            return method.invoke(target, args);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

// --------------------------- CONSTRUCTORS ---------------------------

    private Reflections()
    {
    }
}
