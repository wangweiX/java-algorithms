package one.wangwei.algorithms.datastructures.list.impl;

import one.wangwei.algorithms.datastructures.list.IList;

/**
 * 单向循环链表
 *
 * @param <T>
 * @author https://wangwei.one
 * @date 2018/05/03
 */
public class SinglyCircularLinkedList<T> implements IList<T> {

    /**
     * 集合大小
     */
    private int size = 0;
    /**
     * 头部元素
     */
    private Node<T> head = null;
    /**
     * 尾部元素
     */
    private Node<T> tail = null;

    public SinglyCircularLinkedList() {
    }

    /**
     * 添加元素
     *
     * @param element
     * @return
     */
    @Override
    public boolean add(T element) {
        return addLast(element);
    }

    /**
     * 添加元素
     *
     * @param index
     * @param element
     * @return
     */
    @Override
    public boolean add(int index, T element) {
        checkPositionIndex(index);
        if (index == size) {
            return add(element);
        } else {
            return addBefore(index, element);
        }
    }

    /**
     * 末端元素添加
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
        } else {
            last.next = newElement;
        }
        size++;
        return true;
    }

    /**
     * 插入元素
     *
     * @param element
     * @param element
     * @return
     */
    private boolean addBefore(int index, T element) {
        int prevIndex = index - 1;
        Node<T> prev = prevIndex < 0 ? tail : node(prevIndex);
        Node<T> target = node(index);

        Node<T> newElement = new Node<>(element, target);
        if (index == 0) {
            head = newElement;
        }

        prev.next = newElement;
        size++;
        return true;
    }

    /**
     * 移除元素
     *
     * @param element
     * @return
     */
    @Override
    public boolean remove(T element) {
        if (head == null) {
            return false;
        }
        Node<T> x = head;
        for (int i = 0; i < size; i++) {
            if (element == null && x.element == null) {
                return unlink(x);
            }
            if (element != null && element.equals(x.element)) {
                return unlink(x);
            }
            x = x.next;
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
        checkPositionIndex(index);

        int prevIndex = index - 1;
        Node<T> prev = prevIndex < 0 ? tail : node(prevIndex);
        Node<T> node = node(index);
        Node<T> next = node.next;

        if (index == 0) {
            head = next;
        } else if (index == size - 1) {
            tail = prev;
        } else {
            node.element = null;
        }

        prev.next = next;

        T element = node.element;
        // for GC
        node.element = null;
        node = null;

        size--;
        return element;
    }

    /**
     * 返回 index 位置上的元素
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
     * 卸载元素
     *
     * @param node
     */
    private boolean unlink(Node<T> node) {
        // 找到前驱元素
        Node<T> prev = null;
        Node<T> x = head;

        int prevIndex = 0;
        for (int i = 0; i < size; i++) {
            if (node.element == null && x.element == null) {
                break;
            }
            if (node.element != null && x.element.equals(node.element)) {
                break;
            }
            prev = x;
            x = x.next;
            prevIndex = i;
        }

        final Node<T> next = node.next;

        // 删除head元素
        if (prevIndex == 0) {
            head = next;
            prev = tail;
        }
        // 删除tail元素
        if (prevIndex == size - 1) {
            tail = prev;
        }

        prev.next = next;

        // for GC
        node.element = null;
        node = null;

        size--;
        return true;
    }

    private void checkPositionIndex(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
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
        checkPositionIndex(index);
        Node<T> oldNode = node(index);
        T oldElement = oldNode.element;
        oldNode.element = element;
        return oldElement;
    }

    /**
     * 清空list集合
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
     * 判断是否包含某个元素
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
     * 集合大小
     *
     * @return
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * 节点
     *
     * @param <T>
     */
    private class Node<T> {

        private T element;
        private Node<T> next;

        public Node(T element, Node<T> next) {
            this.element = element;
            this.next = next;
        }
    }
}