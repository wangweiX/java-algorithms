package one.wangwei.algorithms.datastructures.queue.impl;

import one.wangwei.algorithms.datastructures.queue.IQueue;

import java.util.NoSuchElementException;

/**
 * 数组队列
 *
 * @param <T>
 * @author https://wangwei.one
 * @date 2019/02/04
 */
public class ArrayQueue<T> implements IQueue<T> {

    /**
     * default array size
     */
    private static final int DEFAULT_SIZE = 1024;
    /**
     * 元素数组
     */
    private T[] array;
    /**
     * 队头指针下标
     */
    private int front = 0;
    /**
     * 队尾指针下标
     */
    private int rear = 0;

    public ArrayQueue() {
        this(DEFAULT_SIZE);
    }

    public ArrayQueue(int capacity) {
        array = (T[]) new Object[capacity];
    }

    /**
     * 添加队尾元素
     *
     * @param value
     * @return
     */
    @Override
    public boolean offer(T value) {
        if (isFull()) {
            grow();
        }
        array[rear % array.length] = value;
        rear++;
        return true;
    }

    /**
     * grow queue size doubly
     */
    private void grow() {
        int growSize = array.length << 1;
        T[] tmpArray = (T[]) new Object[growSize];
        int adjRear = rear % array.length;
        int endIndex = rear > array.length ? array.length : rear;
        if (adjRear < front) {
            System.arraycopy(array, 0, tmpArray, array.length - adjRear, adjRear + 1);
        }
        System.arraycopy(array, front, tmpArray, 0, endIndex - front);
        array = tmpArray;
        rear = (rear - front);
        front = 0;
    }

    /**
     * 移除队头元素
     *
     * @return
     */
    @Override
    public T poll() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue underflow");
        }

        T element = array[front % array.length];
        array[front % array.length] = null;
        front++;
        if (isEmpty()) {
            // remove last element
            front = rear = 0;
        }

        int shrinkSize = array.length >> 1;
        if (shrinkSize >= DEFAULT_SIZE && size() < shrinkSize) {
            shrink();
        }
        return element;
    }

    /**
     * 压缩
     */
    private void shrink() {
        int shrinkSize = array.length >> 1;
        T[] tmpArray = (T[]) new Object[shrinkSize];
        int adjRear = rear % array.length;
        int endIndex = rear > array.length ? array.length : rear;
        if (adjRear <= front) {
            System.arraycopy(array, 0, tmpArray, array.length - front, adjRear);
        }
        System.arraycopy(array, front, tmpArray, 0, endIndex - front);
        array = null;
        array = tmpArray;
        rear = rear - front;
        front = 0;
    }

    /**
     * 查看队头元素
     *
     * @return
     */
    @Override
    public T peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue underflow");
        }
        return array[front % array.length];
    }

    /**
     * 清除队列元素
     */
    @Override
    public void clear() {
        array = null;
        front = rear = 0;
    }

    /**
     * 队列大小
     */
    @Override
    public int size() {
        return rear - front;
    }

    /**
     * 判断队列是否满
     *
     * @return
     */
    private boolean isFull() {
        return !isEmpty() && (front == (rear + 1) % array.length);
    }

    /**
     * 判断队是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return size() <= 0;
    }



}
