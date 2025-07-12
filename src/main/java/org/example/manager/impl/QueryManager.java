package org.example.manager.impl;

import org.example.annotation.Column;
import org.example.annotation.Component;
import org.example.annotation.GeneratedValue;
import org.example.annotation.Table;

import java.lang.reflect.Field;

public class QueryManager {
    public <T> String getSelectAllQuery(Class<T> tClass) {
        Table tableAnnotation = tClass.getAnnotation(Table.class);
        String tableName = tableAnnotation != null ? tableAnnotation.name() : tClass.getSimpleName().toLowerCase();

        return "SELECT * FROM %s".formatted(tableName);
    }

    public <T> String updateQuery(Class<T> tClass,String condition) {
        Table tableAnnotation = tClass.getAnnotation(Table.class);
        String tableName = tableAnnotation != null ? tableAnnotation.name() : tClass.getSimpleName().toLowerCase();
        String columnName = null;
        Field[] declaredFields = tClass.getDeclaredFields();
        for (int i = 0; i < declaredFields.length; i++) {
            Field declaredField = declaredFields[i];
            if (declaredField.isAnnotationPresent(Column.class)) {
                Column columnAnnotation = declaredField.getAnnotation(Column.class);
                columnName = columnAnnotation.name().isEmpty() ? declaredField.getName() : columnAnnotation.name();
                break;
            }
        }
        return "UPDATE %s SET %s WHERE %s;".formatted(tableName, columnName,condition);
    }

    public <T> String deleteQuery(Class<T> tClass) {
        Table tableAnnotation = tClass.getAnnotation(Table.class);
        String tableName = tableAnnotation != null ? tableAnnotation.name() : tClass.getSimpleName().toLowerCase();
        return "DELETE FROM %s WHERE %s;".formatted(tableName);
    }

    public <T> String insertQuery(Class<T> tClass) {
        Table tableAnnotation = tClass.getAnnotation(Table.class);
        String tableName = tableAnnotation != null ? tableAnnotation.name() : tClass.getSimpleName().toLowerCase();
        Component columnAnnotation = tClass.getAnnotation(Component.class);
        String columnName = columnAnnotation != null ? tableAnnotation.name() : tClass.getSimpleName();
        GeneratedValue generatedValueAnnotation = tClass.getAnnotation(GeneratedValue.class);
        String generatedValueName = generatedValueAnnotation != null ? tableAnnotation.name() : tClass.getSimpleName();
        return "INSERT INTO %s  %s VALUES %s;".formatted(tableName, columnName, generatedValueName);
    }
}
//todo сделать универсальным(Добавили новые анотации), сделать так, чтобы он работал, только с классами, помеченными @Entity
//todo Сделать так чтоб он брал название таблицы из аннотации @Table
//todo Сделать так чтоб он брал название столбцов из аннотации @Column
//todo SELECT * FROM (Название возьму из @Table);
//todo UPDATE (Название возьму из @Table) SET (Название возьмем из @Column)
//todo DELETE FROM (Название возьму из @Table)
//todo  INSERT INTO (Название возьму из @Table) (Название возьму из @Column, а исключу ненужные колонки с помощью @GeneratedValue