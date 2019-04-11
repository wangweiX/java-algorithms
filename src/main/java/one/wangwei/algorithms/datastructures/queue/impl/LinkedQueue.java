package one.wangwei.algorithms.datastructures.queue.impl;

import one.wangwei.algorithms.datastructures.queue.IQueue;

import java.util.NoSuchElementException;

/**
 * 链表队列
 *
 * @param <T>
 * @author https://wangwei.one
 * @date 2019/03/27
 */
public class LinkedQueue<T> implements IQueue<T> {

    private int size = 0;
    private Node<T> head;
    private Node<T> tail;

    public LinkedQueue() {
    }

    /**
     * 添加元素到队列头部
     *
     * @param value
     * @return
     */
    @Override
    public boolean offer(T value) {
        Node<T> last = tail;
        Node<T> newNode = new Node<>(value, null);
        tail = newNode;
        if (last == null) {
            head = newNode;
        } else {
            last.next = newNode;
        }
        size++;
        return true;
    }

    /**
     * 移除队列尾部元素
     *
     * @return
     */
    @Override
    public T poll() {
        if (head == null) {
            throw new NoSuchElementException("Queue underflow");
        }
        Node<T> tmpHead = head;
        head = head.next;
        tmpHead.next = null;
        size--;
        return tmpHead.element;
    }

    /**
     * 查看队列尾部元素值
     *
     * @return
     */
    @Override
    public T peek() {
        if (head == null) {
            throw new NoSuchElementException("Queue underflow");
        }
        return head.element;
    }

    /**
     * 清除队列元素
     */
    @Override
    public void clear() {
        for (Node<T> x = head; x != null; ) {
            Node<T> next = x.next;
            x.element = null;
            x.next = null;
            x = next;
        }
        head = tail = null;
        size = 0;
    }

    /**
     * 队列大小
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Node
     *
     * @param <T>
     */
    private static class Node<T> {
        private T element;
        private Node<T> next;

        private Node(T element) {
            this.element = element;
        }

        private Node(T element, Node<T> next) {
            this.element = element;
            this.next = next;
        }
    }
}
