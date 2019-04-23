package one.wangwei.algorithms.datastructures.queue.impl;

import one.wangwei.algorithms.datastructures.stack.impl.ArrayStack;

/**
 * 栈实现队列
 *
 * @author https://wangwei.one
 * @date 2019/4/17
 */
public class StackImplQueue<T> {

    private ArrayStack<T> offerStack;
    private ArrayStack<T> pollStack;

    public StackImplQueue() {
        offerStack = new ArrayStack<>();
        pollStack = new ArrayStack<>();
    }

    /**
     * 添加队尾元素
     *
     * @param value
     * @return
     */
    public boolean offer(T value) {
        offerStack.push(value);
        pollStack.push(offerStack.pop());
        return true;
    }

    /**
     * 移除队头元素
     *
     * @return
     */
    public T poll() {
        if (pollStack.empty() && !offerStack.empty()) {
            pollStack.push(offerStack.pop());
        }
        return pollStack.pop();
    }

    /**
     * 查看队头元素
     *
     * @return
     */
    public T peek() {
        if (pollStack.empty() && !offerStack.empty()) {
            pollStack.push(offerStack.pop());
        }
        return pollStack.peek();
    }

}
