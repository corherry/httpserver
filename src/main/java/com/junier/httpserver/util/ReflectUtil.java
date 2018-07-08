package com.junier.httpserver.util;

import java.lang.reflect.Field;

public class ReflectUtil {

    public static void setProperty(Object obj, String PropertyName, Object value) throws NoSuchFieldException, IllegalAccessException {

        Class c = obj.getClass();

        Field f = c.getDeclaredField(PropertyName);

        f.setAccessible(true);

        f.set(obj, value);

    }
}
