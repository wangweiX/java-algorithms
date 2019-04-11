package one.wangwei.algorithms.datastructures.list.impl;

import one.wangwei.algorithms.datastructures.list.IList;

/**
 * Singly Circular Linked List
 *
 * @param <T>
 * @author https://wangwei.one
 * @date 2018/05/03
 */
public class SinglyCircularLinkedList<T> implements IList<T> {

    /**
     * size
     */
    private int size = 0;
    /**
     * head node
     */
    private Node<T> head = null;
    /**
     * tail node
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
            return addBefore(index, element);
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
        Node<T> newElement = new Node<>(element, head);
        tail = newElement;
        if (last == null) {
            head = newElement;
            // we need linked itself when add an element first
            tail.next = head;
        } else {
            last.next = newElement;
        }
        size++;
        return true;
    }

    /**
     * add element before certain element
     *
     * @param element
     * @param element
     * @return
     */
    private boolean addBefore(int index, T element) {
        checkPositionIndex(index);
        // prev node, start with tail
        Node<T> prev = tail;
        Node<T> x = head;
        for (int i = 0; i < index; i++) {
            prev = x;
            x = x.next;
        }
        // current node
        Node<T> current = x;
        // new node
        Node<T> newNode = new Node<>(element, current);
        if (index == 0) {
            head = newNode;
        }
        prev.next = newNode;
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
        // start with tail
        Node<T> prev = tail;
        // start with head
        Node<T> x = head;
        // start with index -1
        int prevIndex = -1;

        for (int i = 0; i < size; i++) {
            if (element == null && x.element == null) {
                break;
            }
            if (element != null && element.equals(x.element)) {
                break;
            }
            prev = x;
            x = x.next;
            prevIndex = i;
        }

        // if this linked list is empty
        if (x == null) {
            return false;
        }

        // if don't match element
        if (prevIndex == size - 1) {
            return false;
        }

        Node<T> next = x.next;

        // if delete node is head
        if (prevIndex == -1) {
            head = next;
        }

        // if delete node is tail
        if (prevIndex == size - 2) {
            tail = prev;
        }

        prev.next = next;

        size--;

        if (size == 0) {
            head = tail = null;
        }

        // for GC
        x = null;

        return true;
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
        Node<T> prev = tail;
        Node<T> x = head;
        for (int i = 0; i < index; i++) {
            prev = x;
            x = x.next;
        }

        // if linked is empty
        if (x == null) {
            return null;
        }

        Node<T> next = x.next;

        // if delete node is head
        if (index == 0) {
            head = next;
        }

        // if delete node is tail
        if (index == size - 1) {
            tail = prev;
        }

        prev.next = next;

        size--;

        if (size == 0) {
            head = tail = null;
        }

        return x.element;
    }

    /**
     * get element by index
     *
     * @param index
     * @return
     */
    private Node<T> node(int index) {
        checkPositionIndex(index);
        Node<T> x = head;
        for (int i = 0; i < index; i++) {
            x = x.next;
        }
        return x;
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
        return node(index).element;
    }

    /**
     * clear list element
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
     * contain certain element
     *
     * @param element
     */
    @Override
    public boolean contains(T element) {
        if (head == null) {
            return false;
        }
        Node<T> x = head;
        for (int i = 0; i < size; i++) {
            if (element == null && x.element == null) {
                return true;
            }
            if (element != null && element.equals(x.element)) {
                return true;
            }
            x = x.next;
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
     * Node
     *
     * @param <T>
     */
    private class Node<T> {

        private T element;
        private Node<T> next;

        private Node(T element, Node<T> next) {
            this.element = element;
            this.next = next;
        }
    }
}
