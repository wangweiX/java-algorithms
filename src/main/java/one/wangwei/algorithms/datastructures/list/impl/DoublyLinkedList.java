package one.wangwei.algorithms.datastructures.list.impl;

import one.wangwei.algorithms.datastructures.list.IList;

/**
 * Doubly Linked List
 *
 * @param <T>
 * @author https://wangwei.one
 * @date 2018/04/28
 */
public class DoublyLinkedList<T> implements IList<T> {

    /**
     * size
     */
    private int size = 0;
    /**
     * head element
     */
    private Node<T> head = null;
    /**
     * tail element
     */
    private Node<T> tail = null;

    /**
     * add element
     *
     * @param element
     * @return
     */
    @Override
    public boolean add(T element) {
        return addLast(element);
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
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        if (index == size) {
            return add(element);
        } else {
            return addBefore(element, node(index));
        }
    }

    /**
     * Add Last element
     *
     * @param element
     * @return
     */
    private boolean addLast(T element) {
        final Node<T> last = tail;
        Node<T> newNode = new Node<>(last, element, null);
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
     * add element before certain element
     *
     * @param element
     * @param target
     * @return
     */
    private boolean addBefore(T element, Node<T> target) {
        Node<T> prev = target.prev;
        Node<T> newNode = new Node<>(prev, element, target);
        target.prev = newNode;
        if (prev == null) {
            head = newNode;
        } else {
            prev.next = newNode;
        }
        size++;
        return true;
    }

    /**
     * remove node by element
     *
     * @param element
     * @return
     */
    @Override
    public boolean remove(T element) {
        if (element == null) {
            for (Node<T> x = head; x != null; x = x.next) {
                if (x.element == null) {
                    unlink(x);
                    return true;
                }
            }
        } else {
            for (Node<T> x = head; x != null; x = x.next) {
                if (element.equals(x.element)) {
                    unlink(x);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * remove node by index
     *
     * @param index
     * @return
     */
    @Override
    public T remove(int index) {
        return unlink(node(index));
    }

    /**
     * get element by index
     *
     * @param index
     * @return
     */
    private Node<T> node(int index) {
        checkPositionIndex(index);
        if (index < (size >> 1)) {
            Node<T> x = head;
            for (int i = 0; i < index; i++) {
                x = x.next;
            }
            return x;
        } else {
            Node<T> x = tail;
            for (int i = size - 1; i > index; i--) {
                x = x.prev;
            }
            return x;
        }
    }

    /**
     * unlink node
     *
     * @param node
     */
    private T unlink(Node<T> node) {
        final T element = node.element;
        final Node<T> prev = node.prev;
        final Node<T> next = node.next;

        // if unlink is head
        if (prev == null) {
            head = next;
        } else {
            prev.next = next;
            // clear prev
            node.prev = null;
        }

        // if unlink is tail
        if (next == null) {
            tail = prev;
        } else {
            next.prev = prev;
            node.next = null;
        }

        node.element = null;
        size--;
        return element;
    }

    private void checkPositionIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
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
        Node<T> oldNode = node(index);
        T oldElement = oldNode.element;
        oldNode.element = element;
        return oldElement;
    }

    /**
     * get element by index
     *
     * @param index
     * @return
     */
    @Override
    public T get(int index) {
        Node<T> node = node(index);
        return node == null ? null : node.element;
    }

    /**
     * clear list
     */
    @Override
    public void clear() {
        for (Node<T> x = head; x != null; ) {
            Node<T> next = x.next;
            x.element = null;
            x.next = null;
            x.prev = null;
            x = next;
        }
        head = tail = null;
        size = 0;
    }

    /**
     * contain certain element
     *
     * @param element
     */
    @Override
    public boolean contains(T element) {
        if (element == null) {
            for (Node<T> x = head; x != null; x = x.next) {
                if (x.element == null) {
                    return true;
                }
            }
        } else {
            for (Node<T> x = head; x != null; x = x.next) {
                if (element.equals(x.element)) {
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

    /**
     * node
     *
     * @param <T>
     */
    private class Node<T> {

        private T element;
        private Node<T> prev;
        private Node<T> next;

        private Node(Node<T> prev, T element, Node<T> next) {
            this.element = element;
            this.prev = prev;
            this.next = next;
        }
    }

}
