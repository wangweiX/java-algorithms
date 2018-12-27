package one.wangwei.algorithms.datastructures.list.impl;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class MyArrayListTest {

    private MyArrayList<Integer> myArrayList;

    @BeforeMethod
    public void setUp() {
        myArrayList = new MyArrayList<>();
        for (int i = 0; i < 10; i++) {
            myArrayList.add(i);
        }
    }

    @Test
    public void testAdd() {
        myArrayList.add(1);
        myArrayList.add(null);

    }

    @Test
    public void testAdd1() {
        myArrayList.add(10, 1);
        myArrayList.add(1, null);
    }

    @Test
    public void testRemove() {
        for (int i = 0; i < 10; i++) {
            myArrayList.remove(i);
        }
    }

    @Test
    public void testRemove1() {
        myArrayList.remove(null);
        myArrayList.remove(Integer.valueOf(4));
    }

    @Test
    public void testSet() {
        myArrayList.set(1, 87);
    }

    @Test
    public void testClear() {
        myArrayList.clear();
    }

    @Test
    public void testContains() {
        System.out.println(myArrayList.contains(3));
        System.out.println(myArrayList.contains(null));
    }

    @Test
    public void testSize() {
        System.out.println(myArrayList.size());
    }

    public static void main(String[] args) {
//        int[] array = new int[10];
//
//        int growSize = array.length + (array.length >> 1);
//        array = Arrays.copyOf(array, growSize);
//
//        System.out.println(array);

        System.out.println(4 >> 1 >> 1);
    }
}