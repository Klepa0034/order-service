package org.example.mapper;

import org.example.array.CustomArray;

import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface Mapper<T> {
    CustomArray<T> resultToListMapper(ResultSet resultSet, Class<T> tClass) throws SQLException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException;
}
