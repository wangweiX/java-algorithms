package one.wangwei.algorithms.datastructures.queue.impl;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class StackImplQueueTest {

    private StackImplQueue<Integer> stackImplQueue;

    @BeforeMethod
    public void setUp() {
        stackImplQueue = new StackImplQueue<>();
        for (int i = 0; i < 10; i++) {
            stackImplQueue.offer(i);
        }
    }

    @Test
    public void testOffer() {
        stackImplQueue.offer(11);
    }

    @Test
    public void testPoll() {
        for (int i = 0; i < 10; i++) {
            System.out.println(stackImplQueue.poll());
        }
    }

    @Test
    public void testPeek() {

    }
}