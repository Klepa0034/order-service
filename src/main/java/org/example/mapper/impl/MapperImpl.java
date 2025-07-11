package org.example.mapper.impl;

import org.example.annatation.Component;
import org.example.array.CustomArray;
import org.example.array.impl.CustomArrayImpl;
import org.example.mapper.Mapper;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class MapperImpl<T> implements Mapper<T> {
    @Override
    public CustomArray<T> resultToListMapper(ResultSet resultSet, Class<T> tClass) throws SQLException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        CustomArray<T> customArray = new CustomArrayImpl<>();
        Field[] declaredFields = tClass.getDeclaredFields();
        Method[] declaredMethods = tClass.getDeclaredMethods();
        Constructor<T> declaredConstructor = tClass.getDeclaredConstructor();
        while (resultSet.next()) {
            T t = declaredConstructor.newInstance();
            for (int i = 0; i < declaredFields.length; i++) {
                Field declaredField = declaredFields[i];
                String name = declaredField.getName();
                Object object = resultSet.getObject(name);
                for (int j = 0; j < declaredMethods.length; j++) {
                    Method declaredMethod = declaredMethods[j];
                    if (!declaredMethod.getName().equals("set" + name.substring(0, 1).toUpperCase() + name.substring(1))) {
                        continue;
                    }
                    declaredMethod.invoke(t, object);
                }
            }
            customArray.addElement(t);
        }
        return customArray;
    }
}
