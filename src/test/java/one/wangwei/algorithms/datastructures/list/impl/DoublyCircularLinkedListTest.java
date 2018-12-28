package one.wangwei.algorithms.datastructures.list.impl;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DoublyCircularLinkedListTest {

    private DoublyCircularLinkedList<Integer> doublyCircularLinkedList;

    @BeforeMethod
    public void setUp() {
        doublyCircularLinkedList = new DoublyCircularLinkedList<>();
        for (int i = 0; i < 10; i++) {
            doublyCircularLinkedList.add(i);
        }
        doublyCircularLinkedList.add(null);
    }

    @Test
    public void testAdd() {
        doublyCircularLinkedList.add(100);
        System.out.println(doublyCircularLinkedList);
    }

    @Test
    public void testAdd1() {
        doublyCircularLinkedList.add(0, 99);
        doublyCircularLinkedList.add(10, 88);
        doublyCircularLinkedList.add(6, 77);
        System.out.println(doublyCircularLinkedList);
    }

    @Test
    public void testRemove() {
        doublyCircularLinkedList.remove(null);
        doublyCircularLinkedList.remove(Integer.valueOf(0));
        System.out.println(doublyCircularLinkedList);
    }

    @Test
    public void testRemove1() {
//        doublyCircularLinkedList.remove(0);
        doublyCircularLinkedList.remove(9);
        System.out.println(doublyCircularLinkedList);
    }

    @Test
    public void testSet() {
        doublyCircularLinkedList.set(5, 100);
        System.out.println(doublyCircularLinkedList.get(5));
    }

    @Test
    public void testGet() {
        System.out.println(doublyCircularLinkedList.get(0));
        System.out.println(doublyCircularLinkedList.get(1));
    }

    @Test
    public void testClear() {
        doublyCircularLinkedList.clear();
    }

    @Test
    public void testContains() {
        System.out.println(doublyCircularLinkedList.contains(1));
        System.out.println(doublyCircularLinkedList.contains(null));
    }

    @Test
    public void testSize() {
        System.out.println(doublyCircularLinkedList.size());
    }
}