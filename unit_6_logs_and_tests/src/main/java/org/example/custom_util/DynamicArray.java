package org.example.custom_util;

public class DynamicArray<T> {
    private final int INIT_SIZE = 16;
    private final int CUT_RATE = 4;
    private Object[] array = new Object[INIT_SIZE];
    private int pointer = 0;

    public boolean add(T item) {
        if (pointer == array.length - 1)
            resize(array.length * 2);
        array[pointer++] = item;
        return true;
    }

    public T get(int index) {
        return (T) array[index];
    }

    public boolean isEmpty() {
        if (pointer == 0) {
            return true;
        } else {
            return false;
        }
    }

    public int getSize() {
        return pointer;
    }

    private void resize(int newLength) {
        Object[] newArray = new Object[newLength];
        System.arraycopy(array, 0, newArray, 0, pointer);
        array = newArray;
    }
}