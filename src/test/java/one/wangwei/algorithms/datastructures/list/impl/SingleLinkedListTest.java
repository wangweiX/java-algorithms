package one.wangwei.algorithms.datastructures.list.impl;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SingleLinkedListTest {

    private SingleLinkedList<Integer> singlyLinkedList;

    @BeforeMethod
    public void setUp() {
        singlyLinkedList = new SingleLinkedList<>();
//        for (int i = 0; i < 5; i++) {
//            singlyLinkedList.add(i);
//        }
    }

    @Test
    public void testAdd() {
        singlyLinkedList.add(1);
        singlyLinkedList.add(null);
    }

    @Test
    public void testAdd1() {
        singlyLinkedList.add(0, 1000);
        singlyLinkedList.add(5, null);
        singlyLinkedList.add(12, null);
        singlyLinkedList.add(14, null);
    }

    @Test
    public void testRemove() {
        singlyLinkedList.remove(0);
        System.out.println(singlyLinkedList);
    }

    @Test
    public void testRemove1() {
        singlyLinkedList.remove(null);
        singlyLinkedList.remove(Integer.valueOf(4));
    }

    @Test
    public void testSet() {
        singlyLinkedList.set(1, 87);
    }

    @Test
    public void testClear() {
        singlyLinkedList.clear();
        System.out.println();
    }

    @Test
    public void testContains() {
        System.out.println(singlyLinkedList.contains(3));
        System.out.println(singlyLinkedList.contains(null));
    }

    @Test
    public void testSize() {
        System.out.println(singlyLinkedList.size());
    }

    @Test
    public void testReverse() {
        singlyLinkedList.reverse();
        System.out.println(singlyLinkedList);
    }

}