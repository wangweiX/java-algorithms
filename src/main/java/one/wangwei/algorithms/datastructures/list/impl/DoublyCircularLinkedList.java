package one.wangwei.algorithms.datastructures.list.impl;

import one.wangwei.algorithms.datastructures.list.IList;

/**
 * Doubly circular linked list
 *
 * @author https://wangwei.one
 * @date 2018/12/21
 */
public class DoublyCircularLinkedList<T> implements IList<T> {

    /**
     * size
     */
    private int size;
    /**
     * head node
     */
    private Node<T> head;
    /**
     * tail node
     */
    private Node<T> tail;

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
     * Add last element
     *
     * @param element
     * @return
     */
    private boolean addLast(T element) {
        Node<T> last = tail;
        Node<T> newNode = new Node<>(element, last, head);
        tail = newNode;
        // add element at first
        if (last == null) {
            head = newNode;
            tail.next = head;
        } else {
            last.next = newNode;
        }
        head.prev = tail;
        size++;
        return true;
    }

    /**
     * add element before certain element
     *
     * @param index
     * @param element
     * @return
     */
    private boolean addBefore(int index, T element) {
        Node<T> target = node(index);
        Node<T> prev = target.prev;
        Node<T> newNode = new Node<>(element, prev, target);

        prev.next = newNode;
        target.prev = newNode;

        if (index == 0) {
            head = newNode;
        }

        size++;
        return true;
    }

    /**
     * remove element
     *
     * @param element
     * @return
     */
    @Override
    public boolean remove(T element) {
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

        Node<T> prev = x.prev;
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
        next.prev = prev;

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
        Node<T> x = head;
        for (int i = 0; i < index; i++) {
            x = x.next;
        }

        // if linked is empty
        if (x == null) {
            return null;
        }

        Node<T> prev = x.prev;
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
        next.prev = prev;

        size--;

        if (size == 0) {
            head = tail = null;
        }

        return x.element;
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
     * @return old element
     */
    @Override
    public T set(int index, T element) {
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
     * clear list
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
     * @return
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
        private Node<T> prev;
        private Node<T> next;

        public Node(T element, Node<T> prev, Node<T> next) {
            this.element = element;
            this.prev = prev;
            this.next = next;
        }
    }

}
