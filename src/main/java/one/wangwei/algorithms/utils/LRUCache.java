package one.wangwei.algorithms.utils;


import java.util.HashMap;
import java.util.Map;

/**
 * LRU Cache
 *
 * @author https://wangwei.one
 * @date 2019/01/29
 */
public class LRUCache<K, V> {

    private int capacity;
    private Node head;
    private Node tail;
    private Map<K, Node> nodeMap;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.nodeMap = new HashMap<>(capacity);
    }

    /**
     * Get Key
     *
     * @param key
     * @return
     */
    public V get(K key) {
        Node existNode = nodeMap.get(key);
        if (existNode == null) {
            return null;
        }
        remove(existNode);
        addFirst(existNode);
        return existNode.value;
    }

    /**
     * Add Key-Value
     *
     * @param key
     * @param value
     */
    public void put(K key, V value) {
        Node existNode = nodeMap.get(key);
        if (existNode == null) {
            Node newNode = new Node(key, value);
            if (nodeMap.size() >= capacity) {
                removeLast();
            }
            addFirst(newNode);
        }
        else {
            // update the value
            existNode.value = value;
            remove(existNode);
            addFirst(existNode);
        }
    }

    /**
     * remove node
     *
     * @param node
     */
    private void remove(Node node) {
        Node prev = node.prev;
        Node next = node.next;

        if (prev == null) {
            head = next;
        } else {
            prev.next = next;
        }
        if (next == null) {
            tail = prev;
        } else {
            next.prev = prev;
        }
        nodeMap.remove(node.key);
    }

    /**
     * add first node
     *
     * @param node
     */
    private void addFirst(Node node) {
        node.prev = null;
        if (head == null) {
            head = tail = node;
        } else {
            node.next = head;
            head.prev = node;
            head = node;
        }
        nodeMap.put(node.key, node);
    }

    /**
     * remove last
     */
    private void removeLast() {
        if (tail == null) {
            return;
        }
        // remove key from map
        nodeMap.remove(tail.key);
        // remove node from linked list
        Node prev = tail.prev;
        if (prev != null) {
            prev.next = null;
            tail = prev;
        } else {
            head = tail = null;
        }
    }

    private class Node {

        private K key;
        private V value;
        private Node prev;
        private Node next;

        private Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    public static void main(String[] args) {

        LRUCache cache = new LRUCache(10);

        cache.put(10, 13);
        cache.put(3, 17);
        cache.put(6, 11);
        cache.put(10, 5);
        cache.put(9, 10);

        // return 1
        System.out.println(cache.get(13));

        cache.put(2, 19);
        System.out.println(cache.get(2));
        System.out.println(cache.get(3));

        cache.put(5, 25);
        System.out.println(cache.get(8));

        cache.put(9, 22);
        cache.put(5, 5);
        cache.put(1, 30);
        System.out.println(cache.get(11));

        cache.put(9, 12);
        System.out.println(cache.get(7));
        System.out.println(cache.get(5));
        System.out.println(cache.get(8));
        System.out.println(cache.get(9));

        cache.put(4, 30);
        cache.put(9, 3);
        System.out.println(cache.get(9));
        System.out.println(cache.get(10));
        System.out.println(cache.get(10));

        cache.put(6, 14);
        cache.put(3, 1);
        System.out.println(cache.get(3));

        cache.put(10, 11);
        System.out.println(cache.get(8));

        cache.put(2, 14);
        System.out.println(cache.get(1));
        System.out.println(cache.get(5));
        System.out.println(cache.get(4));

        cache.put(11, 4);
        cache.put(12, 24);
        cache.put(5, 18);
        System.out.println(cache.get(13));

        cache.put(7, 23);
        System.out.println(cache.get(8));
        System.out.println(cache.get(12));

        cache.put(3, 27);
        cache.put(2, 12);
        System.out.println(cache.get(5));

        cache.put(2, 9);
        cache.put(13, 4);
        cache.put(8, 18);
        cache.put(1, 7);
        System.out.println(cache.get(6));

    }

}
