package org.example.utils;


import org.reflections.Reflections;

import java.util.Collection;

public class ReflectionUtils {
    private ReflectionUtils() {

    }

    public static <T> Collection<Class<? extends T>> getSubclasses(String prefix, Class<T> superClass) {
        var reflections = new Reflections(prefix);
        return reflections.getSubTypesOf(superClass);
    }
}
