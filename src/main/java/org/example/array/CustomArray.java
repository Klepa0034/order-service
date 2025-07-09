package org.example.array;

public interface CustomArray<T> {
    void addElement(T value);

    void deleteElement(int index);
    void print();

    void updateElement(int i, T c);
}
