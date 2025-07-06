package org.example.factory;

import java.lang.reflect.InvocationTargetException;

public interface BeanProcessFactory {
    void initialize() throws InvocationTargetException, IllegalAccessException;
}
