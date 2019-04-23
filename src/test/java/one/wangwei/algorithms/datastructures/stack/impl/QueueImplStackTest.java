package one.wangwei.algorithms.datastructures.stack.impl;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class QueueImplStackTest {

    private QueueImplStack<Integer> queueImplStack;

    @BeforeMethod
    public void setUp() {
        queueImplStack = new QueueImplStack<>();
        for (int i = 0; i < 10; i++) {
            queueImplStack.push(i);
        }
    }

    @Test
    public void testPush() {

    }

    @Test
    public void testPop() {
        for (int i = 0; i < 10; i++) {
            System.out.println(queueImplStack.pop());
        }
    }

    @Test
    public void testPeek() {

    }
}