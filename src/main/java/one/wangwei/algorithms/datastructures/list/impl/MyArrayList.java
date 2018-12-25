package one.wangwei.algorithms.datastructures.list.impl;

import one.wangwei.algorithms.datastructures.list.IList;

import java.util.Arrays;

/**
 * 顺序表
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
     * 添加元素
     *
     * @param element
     * @return
     */
    @Override
    public boolean add(T element) {
        return add(size, element);
    }

    /**
     * 在index处添加元素
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
        // 先进行扩容判断
        if (size >= array.length) {
            grow();
        }
        // 插入操作，复制array
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
     * 移除元素
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
     * 删除 index 位置上的元素
     *
     * @param index
     * @return
     */
    @Override
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        T oldElement = array[index];
        // 判断是否需要进行数组复制
        if (index != (size - 1)) {
            System.arraycopy(array, index + 1, array, index, size - index - 1);
        }
        // size减1
        --size;
        array[size] = null;
        // shrink 25%
        int shrinkSize = size - (size >> 1 >> 1);
        if (shrinkSize >= DEFAULT_SIZE && shrinkSize > size) {
            shrink();
        }
        return oldElement;
    }

    /**
     * 压缩25%
     */
    private void shrink() {
        int shrinkSize = size - (size >> 1 >> 1);
        array = Arrays.copyOf(array, shrinkSize);
    }

    /**
     * 设置index上的元素
     *
     * @param index
     * @param element
     * @return
     */
    @Override
    public T set(int index, T element) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        T oldElement = array[index];
        array[index] = element;
        return oldElement;
    }

    /**
     * 清空list集合
     */
    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            array[i] = null;
        }
        size = 0;
    }

    /**
     * 判断是否包含某个元素
     *
     * @param element
     */
    @Override
    public boolean contains(T element) {
        for (int i = 0; i < size; i++) {
            return element == null ? array[i] == null : array[i].equals(element);
        }
        return false;
    }

    /**
     * 集合大小
     *
     * @return
     */
    @Override
    public int size() {
        return size;
    }
}
