package one.wangwei.algorithms.datastructures.list.impl;

import one.wangwei.algorithms.datastructures.list.IList;

/**
 * 双向循环链表
 *
 * @author https://wangwei.one
 * @date 2018/12/21
 */
public class DoublyCircularLinkedList<T> implements IList<T> {

    /**
     * 添加元素
     *
     * @param element
     * @return
     */
    @Override
    public boolean add(T element) {
        return false;
    }

    /**
     * 在index处添加元素
     *
     * @param index
     * @param element
     * @return
     */
    @Override
    public boolean add(int index, T element) {
        return false;
    }

    /**
     * 移除元素
     *
     * @param element
     * @return
     */
    @Override
    public boolean remove(T element) {
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
        return null;
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
        return null;
    }

    /**
     * 清空list集合
     */
    @Override
    public void clear() {

    }

    /**
     * 判断是否包含某个元素
     *
     * @param element
     */
    @Override
    public boolean contains(T element) {
        return false;
    }

    /**
     * 集合大小
     *
     * @return
     */
    @Override
    public int size() {
        return 0;
    }
}
