package one.wangwei.algorithms.datastructures.queue.impl;

import one.wangwei.algorithms.datastructures.queue.IQueue;
import one.wangwei.algorithms.datastructures.stack.impl.ArrayStack;

/**
 * 栈实现队列
 *
 * @author https://wangwei.one
 * @date 2019/4/17
 */
public class StackImpleQueue<T> implements IQueue<T> {

    private ArrayStack<T> s1;
    private ArrayStack<T> s2;

    /**
     * 添加队尾元素
     *
     * @param value
     * @return
     */
    @Override
    public boolean offer(T value) {
        return false;
    }

    /**
     * 移除队头元素
     *
     * @return
     */
    @Override
    public T poll() {
        return null;
    }

    /**
     * 查看队头元素
     *
     * @return
     */
    @Override
    public T peek() {
        return null;
    }

    /**
     * 清除队列元素
     */
    @Override
    public void clear() {

    }

    /**
     * 队列大小
     */
    @Override
    public int size() {
        return 0;
    }
}
