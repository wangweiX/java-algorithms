package one.wangwei.algorithms.datastructures.list.impl;

import one.wangwei.algorithms.datastructures.list.IList;

/**
 * Single Linked List
 *
 * @author https://wangwei.one
 * @date 2018/12/25
 */
public class SingleLinkedList<T> implements IList<T> {

    /**
     * size
     */
    private int size = 0;
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
     * Add Last element
     *
     * @param element
     * @return
     */
    private boolean addLast(T element) {
        Node<T> last = tail;
        Node<T> newNode = new Node<>(null, element);
        tail = newNode;
        // if linked list is empty
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
     * @param index
     * @param element
     * @return
     */
    private boolean addBefore(int index, T element) {
        checkPositionIndex(index);
        // prev node
        Node<T> prev = null;
        Node<T> x = head;
        for (int i = 0; i < index; i++) {
            prev = x;
            x = x.next;
        }
        // current node
        Node<T> current = x;
        // new node
        Node<T> newNode = new Node<>(current, element);
        // if current node is head
        if (prev == null) {
            head = newNode;
        } else {
            prev.next = newNode;
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
        Node<T> prev = null;
        Node<T> x = head;
        if (element == null) {
            while (x != null && x.element != null) {
                prev = x;
                x = x.next;
            }
        } else {
            while (x != null && !x.element.equals(element)) {
                prev = x;
                x = x.next;
            }
        }

        // if this linked is empty OR don't find element
        if (x == null) {
            return false;
        }

        Node<T> next = x.next;

        // if delete node is head
        if (prev == null) {
            head = next;
        } else {
            prev.next = next;
        }
        // if delete node is tail
        if (next == null) {
            tail = prev;
        }

        // for GC
        x.element = null;
        x = null;

        size--;
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
        Node<T> prev = null;
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
        if (prev == null) {
            head = next;
        } else {
            prev.next = next;
        }

        // if delete node is tail
        if (next == null) {
            tail = prev;
        }
        size--;
        return x.element;
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
        checkPositionIndex(index);
        Node<T> node = node(index);
        T oldElement = node.element;
        node.element = element;
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
        Node<T> x = head;
        for (int i = 0; i < index; i++) {
            x = x.next;
        }
        return x;
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
        if (element == null) {
            for (Node<T> x = head; x != null; x = x.next) {
                if (x.element == null) {
                    return true;
                }
            }
        } else {
            for (Node<T> x = head; x != null; x = x.next) {
                if (x.element.equals(element)) {
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
     * 反转链表
     */
    public void reverse() {
        reverseIteratively();
//        reverseRecursively(head, null);
    }

    /**
     * 遍历
     */
    public void reverseIteratively() {
        tail = head;

        Node<T> prev = null;
        Node<T> curr = head;
        Node<T> next;

        while (curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        head = prev;
    }

    /**
     * 递归
     */
    public void reverseRecursively(Node<T> curr, Node<T> prev) {
        if (curr == null) {
            return;
        }
        if (curr.next == null) {
            head = curr;
            curr.next = prev;
            return;
        }
        if (prev == null) {
            tail = curr;
        }

        Node<T> next1 = curr.next;
        curr.next = prev;

        reverseRecursively(next1, curr);
    }

    /**
     * Linked List Node
     *
     * @param <T>
     */
    private class Node<T> {
        private Node<T> next;
        private T element;

        public Node(Node<T> next, T element) {
            this.next = next;
            this.element = element;
        }
    }
}
