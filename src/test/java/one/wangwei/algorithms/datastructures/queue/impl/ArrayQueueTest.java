package one.wangwei.algorithms.datastructures.queue.impl;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ArrayQueueTest {

    private ArrayQueue<Integer> arrayQueue;
    private static final int INIT_SIZE = 100;

    @BeforeMethod
    public void setUp() {
        arrayQueue = new ArrayQueue<>(10);
        for (int i = 0; i < INIT_SIZE; i++) {
            arrayQueue.offer(i);
        }
    }

    @Test
    public void testOffer() {
        for (int i = 0; i < 109; i++) {
            arrayQueue.offer(100 + i);
        }
        arrayQueue.offer(200);
        arrayQueue.offer(201);

        System.out.println(arrayQueue);
    }

    @Test
    public void testPoll() {
        for (int i = 0; i < 50; i++) {
            arrayQueue.poll();
        }
    }

    @Test
    public void testPeek() {
        System.out.println(arrayQueue.peek());
    }

    @Test
    public void testClear() {
        arrayQueue.clear();
        System.out.println(arrayQueue);
    }

    @Test
    public void testSize() {
        System.out.println(arrayQueue.size());
    }
}