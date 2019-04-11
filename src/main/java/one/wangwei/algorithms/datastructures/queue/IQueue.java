package one.wangwei.algorithms.datastructures.queue;

/**
 * 队列接口
 *
 * @param <T>
 * @author wangwei
 * @date 2018/05/03
 */
public interface IQueue<T> {

    /**
     * 添加队尾元素
     *
     * @param value
     * @return
     */
    public boolean offer(T value);

    /**
     * 移除队头元素
     *
     * @return
     */
    public T poll();

    /**
     * 查看队头元素
     *
     * @return
     */
    public T peek();

    /**
     * 清除队列元素
     */
    public void clear();

    /**
     * 队列大小
     */
    public int size();
}
