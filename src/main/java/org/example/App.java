package org.example;

import org.example.entity.Dog;
import org.example.entity.Employee;
import org.example.factory.BeanProcessFactory;
import org.example.factory.impl.BeanProcessImpl;
import org.reflections.Reflections;

import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class App
{
    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
        Map<String, Object> map = new HashMap<>();

        Dog dog = new Dog();
        Employee employee = new Employee();

        map.put("StringImpl", "gav gav");
        map.put("intImpl",223);
        map.put("Dog", dog);
        map.put("Employee", employee);

        BeanProcessFactory processor = new BeanProcessImpl(map);
        processor.initialize();

        System.out.println(dog.getSay());
        System.out.println(employee.getId());
        System.out.println("работает");

    }
}
