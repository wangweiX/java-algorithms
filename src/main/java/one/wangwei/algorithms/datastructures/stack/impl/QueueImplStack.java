package one.wangwei.algorithms.datastructures.stack.impl;

import one.wangwei.algorithms.datastructures.queue.impl.ArrayQueue;

/**
 * 队列实现栈
 *
 * @author https://wangwei.one
 * @date 2019/4/17
 */
public class QueueImplStack<T> {

    private ArrayQueue<T> popQueue;
    private ArrayQueue<T> transferQueue;

    public QueueImplStack() {
        popQueue = new ArrayQueue<>();
        transferQueue = new ArrayQueue<>();
    }

    /**
     * Push into Stack
     *
     * @param value
     * @return
     */
    public boolean push(T value) {
        if (!popQueue.isEmpty()) {
            transferQueue.offer(popQueue.poll());
        }
        popQueue.offer(value);
        return true;
    }

    /**
     * Pop from Stack
     *
     * @return
     */
    public T pop() {
        if (popQueue.isEmpty()) {
            return null;
        }
        T element = popQueue.poll();
        if (!transferQueue.isEmpty()) {
            while (transferQueue.size() != 1) {
                popQueue.offer(transferQueue.poll());
            }
            ArrayQueue<T> tmpTransfer = transferQueue;
            transferQueue = popQueue;
            popQueue = tmpTransfer;
        }
        return element;
    }

    /**
     * Peek Top Element
     *
     * @return
     */
    public T peek() {
        if (popQueue.isEmpty()) {
            return null;
        }
        return popQueue.peek();
    }
}
