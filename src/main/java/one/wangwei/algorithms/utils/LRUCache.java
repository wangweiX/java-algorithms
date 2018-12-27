package one.wangwei.algorithms.utils;

import one.wangwei.algorithms.datastructures.list.impl.SingleLinkedList;

/**
 * LRU 缓存算法
 *
 * @author https://wangwei.one
 * @date 2018/12/24
 */
public class LRUCache<T> {

    // 缓存链表
    private SingleLinkedList<T> cacheList;

    public LRUCache() {
        if (cacheList != null) {
            cacheList = new SingleLinkedList<T>();
        }
    }

    public T get(Object key) {

        return null;
    }

    public void set() {

    }
}
