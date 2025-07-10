package org.example.array;

public interface CustomArray<T> {
    void addElement(T value);

    void deleteElement(int index);

    void print();

    T getElement(int index);

    void updateElement(int index, T value);
}
