package org.example.factory.impl;

import org.example.annotation.Component;
import org.example.factory.BeanFactory;

import java.lang.reflect.InvocationTargetException;

public class BeanFactoryImpl implements BeanFactory {
    @Override
    public <T> T createObject(Class<T> tClass) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
            boolean annotationPresent = tClass.isAnnotationPresent(Component.class);
            if (annotationPresent) {
                return tClass.getDeclaredConstructor().newInstance();
            }
            return null;
        }
    }
}
