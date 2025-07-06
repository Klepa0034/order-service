package org.example.factory;

import org.example.annotation.Component;

import java.lang.reflect.InvocationTargetException;

public interface BeanFactory {
    public <T> T createObject(Class<T> tClass) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException;

}
