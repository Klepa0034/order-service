package org.example.array.impl;

import org.example.array.CustomArray;

public class CustomArrayImpl<T> implements CustomArray<T> {
    private int currentSize;
    private Object[] array;

    @Override
    public T getElement(int index) {
        checkIndex(index);
        return (T) array[index];
    }

    public CustomArrayImpl() {
        this.currentSize = 0;
        this.array = new Object[10];
    }

    @Override
    public void addElement(T value) {
        if (currentSize == array.length) resize();
        array[currentSize++] = value;
    }

    public void resize() {
        Object[] tempArray = new Object[array.length * 2];
        for (int i = 0; i < array.length; i++) {
            tempArray[i] = array[i];
        }
        array = tempArray;
    }

    @Override
    public void deleteElement(int index) {
        checkIndex(index);
        array[index] = null;
        reBuild(index);
        currentSize--;
    }

    @Override
    public void updateElement(int index, T value) {
        checkIndex(index);
        array[index] = value;
    }

    public void reBuild(int index) {
        for (int i = index; i < currentSize; i++) {
            array[i] = array[i + 1];
            array[i + 1] = null;
        }
    }

    @Override
    public void print() {
        for (int i = 0; i < currentSize; i++) {
            System.out.print(array[i] + " ");
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= currentSize) {
            throw new IndexOutOfBoundsException(
                    "Index: " + index + ", Size: " + currentSize
            );
        }
    }

}

