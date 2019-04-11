package one.wangwei.algorithms.datastructures.stack.impl;

import one.wangwei.algorithms.datastructures.stack.IStack;

/**
 * Linked Stack
 *
 * @param <T>
 * @author https://wangwei.one
 * @date 2019/01/10
 */
public class LinkedStack<T> implements IStack<T> {

    private Node<T> top;
    private int size;

    public LinkedStack() {
        this.top = null;
        this.size = 0;
    }

    /**
     * Push into Stack
     *
     * @param value
     * @return
     */
    @Override
    public boolean push(T value) {
        Node<T> newTop = new Node<>(value);
        if (top == null) {
            top = newTop;
        } else {
            Node<T> oldTop = top;
            top = newTop;
            oldTop.above = top;
            top.below = oldTop;
        }
        size++;
        return true;
    }

    /**
     * Pop from Stack
     *
     * @return
     */
    @Override
    public T pop() {
        if (top == null) {
            return null;
        }

        Node<T> needTop = top;
        top = needTop.below;
        if (top != null) {
            top.above = null;
        }

        T needValue = needTop.element;
        needTop.element = null;
        needTop = null;

        size--;
        return needValue;
    }

    /**
     * Peek Top Element
     *
     * @return
     */
    @Override
    public T peek() {
        return top == null ? null : top.element;
    }

    /**
     * remove element
     *
     * @param value
     * @return
     */
    @Override
    public boolean remove(T value) {
        if (top == null) {
            return false;
        }
        Node<T> x = top;
        if (value == null) {
            while (x != null && x.element != null) {
                x = x.below;
            }
        } else {
            while (x != null && !value.equals(x.element)) {
                x = x.below;
            }
        }
        return remove(x);
    }

    /**
     * 删除一个节点
     *
     * @param node
     * @return
     */
    private boolean remove(Node<T> node) {
        if (node == null) {
            return false;
        }
        Node<T> above = node.above;
        Node<T> below = node.below;

        // 删除中间元素
        if (above != null && below != null) {
            above.below = below;
            below.above = above;
        }
        // 删除top元素
        else if (above == null && below != null) {
            top = below;
            top.above = null;
        }
        // 删除尾部元素
        else if (above != null && below == null) {
            above.below = null;
            below = null;
        } else {
            top = null;
        }
        node = null;
        size--;
        return true;
    }

    /**
     * clear stack
     */
    @Override
    public void clear() {
        if (top == null) {
            return;
        }
        for (Node<T> x = top; x != null; ) {
            Node<T> below = x.below;
            x.element = null;
            x.above = null;
            x.below = null;
            x = below;
        }
        top = null;
        size = 0;
    }

    /**
     * Contain certain element
     *
     * @param value
     * @return
     */
    @Override
    public boolean contains(T value) {
        if (value == null) {
            for (Node<T> x = top; x != null; x = x.below) {
                if (x.element == null) {
                    return true;
                }
            }
        } else {
            for (Node<T> x = top; x != null; x = x.below) {
                if (x.element.equals(value)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Get Stack size
     *
     * @return
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Is empty
     *
     * @return
     */
    @Override
    public boolean empty() {
        return size == 0;
    }

    /**
     * Node
     *
     * @param <T>
     */
    private static class Node<T> {
        private T element;
        private Node<T> above;
        private Node<T> below;

        private Node(T element) {
            this.element = element;
        }
    }


}
