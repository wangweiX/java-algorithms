package one.wangwei.algorithms.datastructures.queue.impl;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LinkedQueueTest {

    private LinkedQueue<Integer> linkedQueue;

    @BeforeMethod
    public void setUp() {
        linkedQueue = new LinkedQueue<>();
        for (int i = 0; i < 10; i++) {
            linkedQueue.offer(i);
        }
    }

    @Test
    public void testOffer() {
        linkedQueue.offer(11);
    }

    @Test
    public void testPoll() {
        for (int i = 0; i < 10; i++) {
            System.out.println(linkedQueue.poll());
        }
    }

    @Test
    public void testPeek() {
        for (int i = 0; i < 10; i++) {
            System.out.println(linkedQueue.peek());
        }
    }

    @Test
    public void testClear() {
        linkedQueue.clear();
        System.out.println(linkedQueue);
    }

    @Test
    public void testSize() {
        System.out.println(linkedQueue.size());
    }
}