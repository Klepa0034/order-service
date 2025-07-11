package org.example.factory;

import java.lang.reflect.InvocationTargetException;

public interface BeanFactory {
    public <T> T createObject(Class<T> tClass) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException;

}
