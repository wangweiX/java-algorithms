package one.wangwei.algorithms.datastructures.list.impl;

import one.wangwei.algorithms.datastructures.list.IList;

import java.util.Arrays;

/**
 * Array List
 *
 * @param <T>
 * @author https://wangwei.one
 * @date 2018/04/27
 */
public class MyArrayList<T> implements IList<T> {

    /**
     * default array size
     */
    private final int DEFAULT_SIZE = 10;

    /**
     * array size
     */
    private int size = 0;

    /**
     * array
     */
    private T[] array = (T[]) new Object[DEFAULT_SIZE];

    /**
     * add element
     *
     * @param element
     * @return
     */
    @Override
    public boolean add(T element) {
        return add(size, element);
    }

    /**
     * add element at index
     *
     * @param index
     * @param element
     * @return
     */
    @Override
    public boolean add(int index, T element) {
        if (index < 0 || index > size) {
            throw new ArrayIndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        // need grow
        if (size >= array.length) {
            grow();
        }
        // copy array element
        if (index < size) {
            System.arraycopy(array, index, array, index + 1, size - index);
        }
        array[index] = element;
        size++;
        return true;
    }

    /**
     * grow 50%
     */
    private void grow() {
        int growSize = size + (size >> 1);
        array = Arrays.copyOf(array, growSize);
    }

    /**
     * remove element
     *
     * @param element
     * @return
     */
    @Override
    public boolean remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element == null) {
                if (array[i] == null) {
                    remove(i);
                    return true;
                }
            } else {
                if (array[i].equals(element)) {
                    remove(i);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * remove element by index
     *
     * @param index
     * @return
     */
    @Override
    public T remove(int index) {
        checkPositionIndex(index);
        T oldElement = array[index];
        // need copy element
        if (index != (size - 1)) {
            System.arraycopy(array, index + 1, array, index, size - index - 1);
        }
        --size;
        array[size] = null;
        // shrink 25%
        int shrinkSize = size - (size >> 2);
        if (shrinkSize >= DEFAULT_SIZE && shrinkSize > size) {
            shrink();
        }
        return oldElement;
    }

    /**
     * shrink 25%
     */
    private void shrink() {
        int shrinkSize = size - (size >> 2);
        array = Arrays.copyOf(array, shrinkSize);
    }

    /**
     * set element by index
     *
     * @param index
     * @param element
     * @return
     */
    @Override
    public T set(int index, T element) {
        checkPositionIndex(index);
        T oldElement = array[index];
        array[index] = element;
        return oldElement;
    }

    /**
     * check index
     *
     * @param index
     */
    private void checkPositionIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    /**
     * clear list
     */
    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            array[i] = null;
        }
        size = 0;
    }

    /**
     * contain certain element
     *
     * @param element
     */
    @Override
    public boolean contains(T element) {
        for (int i = 0; i < size; i++) {
            if (element == null) {
                if (array[i] == null) {
                    return true;
                }
            } else {
                if (array[i].equals(element)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * get list size
     *
     * @return
     */
    @Override
    public int size() {
        return size;
    }
}
